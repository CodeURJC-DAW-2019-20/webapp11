package es.sidelab.animalshelter.api;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
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
import es.sidelab.animalshelter.Shelter;

@RestController
@RequestMapping("/api/shelters")
public class APIshelterController {

	private Map<Long, Shelter> shelters = new ConcurrentHashMap<>();

	@GetMapping("/")
	public Collection<Shelter> shelter() {
		return shelters.values();
	}

	@PostMapping("/addShelter")
	@ResponseStatus(HttpStatus.CREATED)
	public Shelter newShelter(@RequestBody Shelter shelter) {
		shelters.put(shelter.getIdShelter(), shelter);
		return shelter;
	}

	@PutMapping("/shelter={id}")
	public ResponseEntity<Shelter> updateShelter(@PathVariable long id, @RequestBody Shelter updatedShelter) {

		Shelter shelter = shelters.get(id);

		if (shelter != null) {

			updatedShelter.setIdShelter(id);
			shelters.put(id, updatedShelter);

			return new ResponseEntity<>(updatedShelter, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/shelter={id}")
	public ResponseEntity<Shelter> getShelter(@PathVariable long id) {

		Shelter shelter = shelters.get(id);

		if (shelter != null) {
			return new ResponseEntity<>(shelter, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/shelter={id}")
	public ResponseEntity<Shelter> deleteShelter(@PathVariable long id) {

		Shelter shelter = shelters.remove(id);

		if (shelter != null) {
			return new ResponseEntity<>(shelter, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

}
