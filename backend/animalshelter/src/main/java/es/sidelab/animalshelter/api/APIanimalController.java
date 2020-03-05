package es.sidelab.animalshelter.api;

import java.io.IOException;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

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

import es.sidelab.animalshelter.Animal;
import es.sidelab.animalshelter.controllers.ImageService;

@RestController
@RequestMapping("/api/animals")
public class APIanimalController {

	private Map<Long, Animal> animals = new ConcurrentHashMap<>();
	@Autowired
	private ImageService imageService;


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
	@PostMapping("/{id}/image")//to post animalPhoto by postman
	@ResponseStatus(HttpStatus.CREATED)
	public Animal setAnimalImage(@RequestParam(value="files", required=false) MultipartFile file, @PathVariable long id)  throws IOException {
		
		Animal animal = animals.get(id);
		imageService.saveImage("animals", animal.getIdAnimal(), file);
		animal.setAnimalPhoto("image-" + animal.getIdAnimal() + ".jpg");
		
		return animal;
	}
	
	@GetMapping("/{id}/image")
	public ResponseEntity<String> getAnimalimage(@PathVariable long id) {

		Animal animal = animals.get(id);
		
		if (animal != null) {
			return new ResponseEntity<>(animal.getAnimalPhoto(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

}
