package es.sidelab.animalshelter.api;

import java.util.Collection;

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
import org.springframework.web.bind.annotation.RestController;
import es.sidelab.animalshelter.Shelter;
import es.sidelab.animalshelter.UserShelterComponent;
import es.sidelab.animalshelter.services.ShelterService;

@RestController
@RequestMapping("/api/shelters")
public class APIshelterController {

	@Autowired
	private ShelterService service;
	
	@Autowired
	private UserShelterComponent loggeduser;

	@GetMapping("/")
	public Collection<Shelter> shelter() {
		return service.findAll();
	}
	
	@PostMapping("/")
	public ResponseEntity<Shelter> newShelter(@RequestBody Shelter shelter) {
		if (service.save(shelter)) {
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

}
