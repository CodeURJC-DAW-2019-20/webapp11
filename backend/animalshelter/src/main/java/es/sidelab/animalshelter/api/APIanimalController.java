package es.sidelab.animalshelter.api;

import java.io.IOException;
import java.util.Collection;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
import es.sidelab.animalshelter.AnimalRepository;
import es.sidelab.animalshelter.UserShelterComponent;
import es.sidelab.animalshelter.controllers.ImageService;
import es.sidelab.animalshelter.services.AnimalService;

@RestController
@RequestMapping("/api/animals")
public class APIanimalController {

	@Autowired
	private ImageService imageService;
	
	@Autowired
	private AnimalRepository animalRepository;
	
	@Autowired
	private AnimalService animalService;
	
	@Autowired
	private UserShelterComponent loggeduser;

	@GetMapping("/")
	public Collection<Animal> animals() {
		return animalService.findAll();
	}

	@PostMapping("/")
	@ResponseStatus(HttpStatus.CREATED)
	public Animal newAnimal(@RequestBody Animal animal) {
		animal.setShelterOwner(loggeduser.getShelter());
		animalService.save(animal);
		return animal;
	}

	@PutMapping("/{id}")
	public ResponseEntity<Animal> updateAnimal(@PathVariable long id, @RequestBody Animal updatedAnimal) {

		Animal animal = animalService.findByAnimalId(id);

		if (animal != null) {

			updatedAnimal.setIdAnimal(id);
			animalService.save(updatedAnimal);

			return new ResponseEntity<>(updatedAnimal, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<Animal> getAnimal(@PathVariable long id) {

		Animal animal = animalService.findByAnimalId(id);

		if (animal != null) {
			return new ResponseEntity<>(animal, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Animal> deleteAnimal(@PathVariable long id) {

		Animal animal = animalService.findByAnimalId(id);

		if (animal != null) {
			return new ResponseEntity<>(animal, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/{id}/image") // to post animalPhoto by postman
	@ResponseStatus(HttpStatus.CREATED)
	public Animal setAnimalImage(@RequestParam(value = "files", required = false) MultipartFile file,
			@PathVariable long id) throws IOException {

		Animal animal = animalService.findByAnimalId(id);
		imageService.saveImage("animals", animal.getIdAnimal(), file);
		animal.setAnimalPhoto("image-" + animal.getIdAnimal() + ".jpg");
		animalService.save(animal);
		
		return animal;
	}

	@GetMapping("/{id}/image")
	public ResponseEntity<String> getAnimalimage(@PathVariable long id) {

		Animal animal = animalService.findByAnimalId(id);

		if (animal != null) {
			return new ResponseEntity<>(animal.getAnimalPhoto(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/animalPagination") // this method will show all animals by paging
	public Page<Animal> findalls(@RequestParam Optional<Integer> page) {

		Page<Animal> animals = animalRepository.findAll(PageRequest.of(page.orElse(0), 3));

		return animals;
	}

}
