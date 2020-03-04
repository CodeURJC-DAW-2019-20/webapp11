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
import es.sidelab.animalshelter.Animal;

@RestController
@RequestMapping("/api/animals")
public class APIanimalController {

	private Map<Long, Animal> animals = new ConcurrentHashMap<>();

	@GetMapping("/")
	public Collection<Animal> animals() {
		return animals.values();
	}

	@PostMapping("/addAnimal")
	@ResponseStatus(HttpStatus.CREATED)
	public Animal newAnimal(@RequestBody Animal animal) {
		animals.put(animal.getIdAnimal(), animal);
		return animal;
	}

	@PutMapping("/animal={id}")
	public ResponseEntity<Animal> updateAnimal(@PathVariable long id, @RequestBody Animal updatedAnimal) {

		Animal animal = animals.get(id);

		if (animal != null) {

			updatedAnimal.setIdAnimal(id);
			animals.put(id, updatedAnimal);

			return new ResponseEntity<>(updatedAnimal, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/animal={id}")
	public ResponseEntity<Animal> getAnimal(@PathVariable long id) {

		Animal animal = animals.get(id);

		if (animal != null) {
			return new ResponseEntity<>(animal, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/animal={id}")
	public ResponseEntity<Animal> deleteAnimal(@PathVariable long id) {

		Animal animal = animals.remove(id);

		if (animal != null) {
			return new ResponseEntity<>(animal, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

}
