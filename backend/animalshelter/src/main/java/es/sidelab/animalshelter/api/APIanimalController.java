package es.sidelab.animalshelter.api;

import java.io.IOException;
import java.util.List;
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

import com.fasterxml.jackson.databind.ObjectMapper;

import es.sidelab.animalshelter.Animal;
import es.sidelab.animalshelter.AnimalRepository;
import es.sidelab.animalshelter.UserShelterComponent;
import es.sidelab.animalshelter.controllers.ImageService;
import es.sidelab.animalshelter.services.AnimalService;

@RestController
@RequestMapping("/api/animals")
public class APIanimalController {

	ObjectMapper objectMapper = new ObjectMapper();
	
	@Autowired
	private ImageService imageService;
	
	@Autowired
	private AnimalRepository animalRepository;
	
	@Autowired
	private AnimalService animalService;
	
	@Autowired
	private UserShelterComponent loggeduser;

	@GetMapping("") // this method will show all animals by paging
	public ResponseEntity<List<Animal>> findalls(@RequestParam(value = "page") Optional<Integer> page) {

		Page<Animal> animals = animalRepository.findAll(PageRequest.of(page.orElse(0), 3));
		
		if(animals.hasContent()) {
			return new ResponseEntity<>(animals.getContent(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/")
	@ResponseStatus(HttpStatus.CREATED)
	public Animal newAnimal(@RequestParam(required=true, value="jsondata")String jsondata, @RequestParam(value="animalPhoto", required=true) MultipartFile animalPhoto) throws IOException{
		Animal animal = objectMapper.readValue(jsondata, Animal.class);
		animal.setShelterOwner(loggeduser.getShelter());
		animalService.save(animal);
		imageService.saveImage("animal", animal.getIdAnimal(), animalPhoto);
		animal.setAnimalPhoto("image-" + animal.getIdAnimal() + ".jpg");
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

	@GetMapping("/{id}/image")
	public ResponseEntity<String> getAnimalimage(@PathVariable long id) {

		Animal animal = animalService.findByAnimalId(id);

		if (animal != null) {
			return new ResponseEntity<>(animal.getAnimalPhoto(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

}
