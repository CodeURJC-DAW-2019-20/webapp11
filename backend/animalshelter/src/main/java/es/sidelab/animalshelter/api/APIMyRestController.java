package es.sidelab.animalshelter.api;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import es.sidelab.animalshelter.Animal;
import es.sidelab.animalshelter.services.MyRestService;

@RestController
@RequestMapping("/api/searches")
public class APIMyRestController {
	
	@Autowired
	MyRestService service;
	
	
	@GetMapping("") //Returns the list of type selected animals
	public ResponseEntity<List<Animal>> searchByType(@RequestParam(value = "animal") Optional<String> animal,
			@RequestParam(value = "name") Optional<String> name,
			@RequestParam(value = "page") Optional<Integer> page) {
		List<Animal> result;
		if(animal.isPresent()) {			
			result = service.searchByType(animal.get(), page.orElse(0));
		} else if(name.isPresent()) {
			result = service.searchByName(name.get(), page.orElse(0));
		} else {
			result = service.suitAnimal(page.orElse(0));
		}
		
		if(result != null) {
			if(result.size() == 0) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			} else {
				return new ResponseEntity<>(result, HttpStatus.OK);
			}
		} else {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}
	
	@GetMapping("/galleries")//this will return list of user's gallery
	public List<String> profileView(@RequestParam(value = "page") Optional<Integer> page) {
		
		return service.profileView(page.orElse(0));
	}	
}
