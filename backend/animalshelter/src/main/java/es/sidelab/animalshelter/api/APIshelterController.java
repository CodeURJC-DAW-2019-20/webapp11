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
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import es.sidelab.animalshelter.Adoption;
import es.sidelab.animalshelter.AdoptionRepository;
import es.sidelab.animalshelter.Animal;
import es.sidelab.animalshelter.AnimalRepository;
import es.sidelab.animalshelter.Shelter;
import es.sidelab.animalshelter.SmtpMailSender;
import es.sidelab.animalshelter.UserShelterComponent;
import es.sidelab.animalshelter.services.AdoptionService;
import es.sidelab.animalshelter.services.AnimalService;
import es.sidelab.animalshelter.services.ShelterService;

@RestController
@RequestMapping("/api/shelters")
public class APIshelterController {
	
	ObjectMapper objectMapper = new ObjectMapper();
	
	@Autowired
	SmtpMailSender smtpMailSender;

	@Autowired
	private ShelterService service;
	
	@Autowired
	private UserShelterComponent loggeduser;
	
	@Autowired
	private AnimalService animalRepository;
	
	@Autowired
	private AdoptionService adoptionRepository;

	@GetMapping("/")
	public Collection<Shelter> shelter() {
		return service.findAll();
	}
	
	@PostMapping("/")
	public ResponseEntity<Shelter> newShelter(@RequestParam(required=true, value="jsondata") String jsondata,
			@RequestParam(required=true, value="password")String password) throws IOException{
		Shelter shelter = objectMapper.readValue(jsondata, Shelter.class);
		if (service.save(shelter)) {
			shelter.setPassword(password);
			service.update(shelter);
			return new ResponseEntity<>(shelter, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(HttpStatus.ALREADY_REPORTED);
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<Shelter> updateShelter(@PathVariable long id, @RequestBody Shelter updatedShelter) {

		Shelter shelter = (Shelter) loggeduser.getLoggedUser();

		if (shelter.getIdShelter() == id) {

			updatedShelter.setIdShelter(id);
			service.update(updatedShelter);
			return new ResponseEntity<>(updatedShelter, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<Shelter> getShelter(@PathVariable long id) {

		Shelter shelter = service.findByShelterId(id);

		if (shelter != null) {
			return new ResponseEntity<>(shelter, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Shelter> deleteShelter(@PathVariable long id, HttpSession session) {

		Shelter shelter = (Shelter) loggeduser.getLoggedUser();

		if (shelter.getIdShelter() == id) {
			service.delete(id);
			session.invalidate();
			return new ResponseEntity<>(shelter, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}
	
	
	@GetMapping("/petitions")
	public List<Animal> requestView() {
		Shelter shelter = (Shelter) loggeduser.getLoggedUser();
		System.out.print("petitions");

		List<Animal> adoptedanimals = animalRepository.findAll();
		List<Animal> shelterCorrespondingAnimals = new ArrayList<>();
		List<Adoption> adoptions = adoptionRepository.findAll();
		
		for (Animal a : adoptedanimals) {
			if (a.getShelterOwner().getShelterEmail().equals(shelter.getShelterEmail())) {
				for (Adoption adopt : adoptions) {
					if (adopt.isInCourse() && adopt.getAnimal().getAnimalName().equals(a.getAnimalName()))
						shelterCorrespondingAnimals.add(a);
				}
			}
		}
				
		return  shelterCorrespondingAnimals;

	}
	
	@GetMapping("/petitions/{id}")
	public ResponseEntity<Adoption> getSpecificAdoption(@PathVariable long id) {
		Adoption adoption = adoptionRepository.findByAdoptionId(id);
		if(adoption!=null) {
			return new ResponseEntity<>(adoption, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping("/petitions/{id}")
	public ResponseEntity<Adoption> respondRequest(@PathVariable long id, 
			@RequestParam String response) throws MessagingException {
		Animal animal=animalRepository.findByAnimalId(id);
		Adoption adoption;
		Adoption adoptionOptional = adoptionRepository.findByAnimal(animal);
		
		if(adoptionOptional!=null && adoptionOptional.isInCourse()) {
			adoption = adoptionOptional;
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		if(response.equals("accept")) {
			adoption.confirmAdoption();
			adoptionRepository.save(adoption);
			smtpMailSender.sendAccept("Adoption accepted", adoption.getUser().getUserEmail());
		} else if (response.equals("deny")) {
			Animal a = adoption.getAnimal();
			a.setAnimalAdopted(false);
			animalRepository.save(a);
			adoptionRepository.delete(adoptionOptional.getIdAdoption());
			smtpMailSender.sendDeny("Adoption rejected", adoption.getUser().getUserEmail());
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<>(adoption, HttpStatus.OK);
	}
}
