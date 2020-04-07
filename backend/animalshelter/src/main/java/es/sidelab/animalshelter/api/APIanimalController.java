package es.sidelab.animalshelter.api;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;

import java.util.List;
import java.util.Optional;
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

import es.sidelab.animalshelter.Animal;
import es.sidelab.animalshelter.AnimalRepository;
import es.sidelab.animalshelter.UserShelterComponent;
import es.sidelab.animalshelter.WebUser;
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

		int count = page.orElse(0);
		int counts = count * 3 + 3;
		List<Animal> result = new ArrayList<>();
		List<Animal> animal = animalRepository.findByAnimalAdopted(false);

		for (int i = count*3; i < animal.size() && i < counts; i++) {
			result.add(animal.get(i));
		}
		
		if(result.size() == 0) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(result, HttpStatus.OK);
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
	@GetMapping("/animalType/{animalType}")//get animal by type
	public ResponseEntity<Object> findByType(@PathVariable String animalType,@RequestParam(defaultValue="0") int page) {
	
		List<Animal> result = new ArrayList<>();
		List<Animal> animal;
	
		if (animalType.equals("all") ) {
			int count = page;
			int counts = count * 3 + 3;
			 animal = animalService.findAll();
			 for (int i = count*3; i < animal.size() && i < counts; i++) {
					result.add(animal.get(i));
			 }
		}
		else {
			animal = animalService.findByAnimalType(animalType);
			int count = page;
			System.out.print("hola"+page);
			int counts = count * 3 + 3;
			 for (int i = count*3; i < animal.size() && i < counts; i++) {
					result.add(animal.get(i));
			 }			
		}	
		if(result != null) {
			return new ResponseEntity<>(result, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	@GetMapping("/animalName/{animalName}") //get animal by  name
	public ResponseEntity<Object> findByName(@PathVariable String animalName){
		Animal animal = animalService.findByAnimalName(animalName);
		
		if(animal != null) {
			return new ResponseEntity<>(animal, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	@GetMapping("/suitedAnimal")//get suited Animal for user
	public ResponseEntity<Object> suitAnimal() {
		
		List<Animal> animalSuit = (List<Animal>) animalService.findAll();
		List<Animal> suited = new ArrayList<Animal>();
		if(loggeduser.getRole() == "USER") {
			WebUser userActive = loggeduser.getUser();
			for (Animal mem : animalSuit) {
	
				if (mem.getAnimalDimensions() <= userActive.getUserCapacity()) {
					suited.add(mem);
				}
			}
			
			
		}
		if(suited != null) {
			return new ResponseEntity<>(suited, HttpStatus.OK);
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


	@GetMapping("/{id}/images")
	public ResponseEntity<Object> getAnimalimage(@PathVariable long id) throws MalformedURLException {

		Animal animal = animalService.findByAnimalId(id);

		if (animal != null) {
			 return this.imageService.createResponseFromImage("animal", id);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}		
	}
}
