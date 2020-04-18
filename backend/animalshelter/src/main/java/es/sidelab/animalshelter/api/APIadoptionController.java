package es.sidelab.animalshelter.api;

import java.util.List;

import javax.mail.MessagingException;

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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import es.sidelab.animalshelter.Adoption;
import es.sidelab.animalshelter.Animal;
import es.sidelab.animalshelter.SmtpMailSender;
import es.sidelab.animalshelter.UserShelterComponent;
import es.sidelab.animalshelter.WebUser;
import es.sidelab.animalshelter.services.AdoptionService;
import es.sidelab.animalshelter.services.AnimalService;

@RestController
@RequestMapping("/api/adoptions")
public class APIadoptionController {

	@Autowired
	AdoptionService service;
	@Autowired
	SmtpMailSender smtpMailSender;
	@Autowired
	AnimalService animalService;
	@Autowired
	private UserShelterComponent loggeduser;

	@GetMapping("/")
	public List<Adoption> adoptions() {
		return service.findAll();
	}

	@PostMapping("/")
	@ResponseStatus(HttpStatus.CREATED)
	public Adoption newAdoption(@RequestBody Adoption adoption) throws MessagingException {
		
		Animal animal = animalService.findByAnimalName(adoption.getAnimal().getAnimalName());
		System.out.print(animal);
		String shelterEmail = animal.getShelterOwner().getShelterEmail();
		adoption.setAnimal(animal);
		service.save(adoption);
		smtpMailSender.sendAutoMail(adoption.getUser().getUserName(), adoption.getAnimal().getAnimalName(),adoption.getUser().getUserEmail(), "Adoption Request",
				shelterEmail);
		
		return adoption;
	}

	@PutMapping("/{id}")
	public ResponseEntity<Adoption> updateAdoption(@PathVariable long id, @RequestBody Adoption updatedAdoption) {

		Adoption adoption = service.findByAdoptionId(id);

		if (adoption != null) {

			updatedAdoption.setIdAdoption(id);
			service.save(updatedAdoption);

			return new ResponseEntity<>(updatedAdoption, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<Adoption> getAdoption(@PathVariable long id) {

		Adoption adoption = service.findByAdoptionId(id);

		if (adoption != null) {
			return new ResponseEntity<>(adoption, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Adoption> deleteAdoption(@PathVariable long id) {

		Adoption adoption = service.findByAdoptionId(id);

		if (adoption != null) {
			service.delete(id);
			return new ResponseEntity<>(adoption, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	

}
