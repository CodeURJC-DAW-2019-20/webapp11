package es.sidelab.animalshelter.api;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import javax.mail.MessagingException;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;

import es.sidelab.animalshelter.WebUser;
import es.sidelab.animalshelter.Adoption;
import es.sidelab.animalshelter.AdoptionRepository;
import es.sidelab.animalshelter.Animal;
import es.sidelab.animalshelter.AnimalRepository;
import es.sidelab.animalshelter.SmtpMailSender;
import es.sidelab.animalshelter.UserGalleryPhoto;
import es.sidelab.animalshelter.UserGalleryPhotoRepository;
import es.sidelab.animalshelter.UserShelterComponent;
import es.sidelab.animalshelter.WebUserRepository;
import es.sidelab.animalshelter.controllers.ImageService;
import es.sidelab.animalshelter.services.UserGalleryService;
import es.sidelab.animalshelter.services.WebUserService;

@RestController
@RequestMapping("/api/users")
public class APIWebUserController {

	ObjectMapper objectMapper = new ObjectMapper();
	
	@Autowired
	private WebUserService service;
	
	@Autowired
	private ImageService imageService;
	
	@Autowired
	private UserGalleryPhotoRepository ugpr;
	
	@Autowired
	private UserShelterComponent loggeduser;

	@Autowired
	private WebUserRepository ur;
	
	@Autowired
	private UserGalleryService servicegallery;
	
	@Autowired
	SmtpMailSender smtpMailSender;

	@Autowired
	AnimalRepository animalRepository;

	@Autowired
	AdoptionRepository adoptionRepository;

	@GetMapping("/")
	public Collection<WebUser> webusers() {
		return service.findAll();
	}

	@PostMapping("/")
	public ResponseEntity<WebUser> newWebUser(@RequestParam(required=true, value="jsondata")String jsondata, @RequestParam(value="userPhoto", required=true) MultipartFile userPhoto) throws IOException{
		WebUser webUser = objectMapper.readValue(jsondata, WebUser.class);
		if (service.save(webUser)) {
			webUser.encryptPassword();
			imageService.saveImage("users", webUser.getIdUser(), userPhoto);
			webUser.setUserphoto("image-" + webUser.getIdUser() + ".jpg");
			service.update(webUser);
			return new ResponseEntity<>(webUser, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(HttpStatus.ALREADY_REPORTED);
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<WebUser> updateWebUser(@PathVariable long id, @RequestBody WebUser updatedWebUser) {

		WebUser webuser = (WebUser) loggeduser.getLoggedUser();

		if (webuser.getIdUser() == id) {

			updatedWebUser.setIdUser(id);
			service.update(updatedWebUser);
			return new ResponseEntity<>(updatedWebUser, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<WebUser> getWebUser(@PathVariable long id) {

		WebUser webuser = service.findByUserId(id);

		if (webuser .getIdUser() == id) {
			return new ResponseEntity<>(webuser, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<WebUser> deleteWebUser(@PathVariable long id, HttpSession session) {
		WebUser webuser = (WebUser) loggeduser.getLoggedUser();
		if (webuser.getIdUser() == id) {
			service.delete(id);
			session.invalidate();
			return new ResponseEntity<>(webuser, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}
	
	@PostMapping("/gallery")//to post UserPhoto by postman
	@ResponseStatus(HttpStatus.CREATED)
	public List<String> setUserGallery(@RequestParam(value="files", required=false) MultipartFile userGallery)  throws IOException {
		
		WebUser webuser= (WebUser) loggeduser.getLoggedUser();
		service.save(webuser);
		imageService.saveUserGalleryImage("users", userGallery);
		String photo = "/images/users/" + userGallery.getOriginalFilename();
		UserGalleryPhoto gp = new UserGalleryPhoto(photo);
		servicegallery.save(gp);
		gp.setGalleryOwner(webuser);
		ur.save(webuser);
		ugpr.save(gp);
		List<String> gallery = new ArrayList<>();
		gallery = ur.getUserGalleryPhotos(webuser);

		
		return gallery;
	}
	
	@GetMapping("/gallery")
	public ResponseEntity<List<String>> getuserGallery() {

		WebUser webuser= (WebUser) loggeduser.getLoggedUser();
		service.save(webuser);

		List<String> gallery = new ArrayList<>();
		gallery = ur.getUserGalleryPhotos(webuser);
		
		if (webuser != null) {
			return new ResponseEntity<>(gallery, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/adopt/{id}")
	public ResponseEntity<Adoption> requestAdoption(@PathVariable long id) throws MessagingException {
		
		WebUser w = loggeduser.getUser();
		Optional<Animal> optional = animalRepository.findByIdAnimal(id);
		if(optional.isPresent()) {
			Animal animal = optional.get();
			String shelterEmail = animal.getShelterOwner().getShelterEmail();

			Adoption adoption = new Adoption(true);
			adoption.setAnimal(animal);
			adoption.setUser(w);
			adoptionRepository.save(adoption);
			
			animal.setAnimalAdopted(true);
			animalRepository.save(animal);

			smtpMailSender.sendAutoMail(w.getUserName(), animal.getAnimalName(), w.getUserEmail(), "Adoption Request",
					shelterEmail);
			
			return new ResponseEntity<>(adoption, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
	}
	
}
