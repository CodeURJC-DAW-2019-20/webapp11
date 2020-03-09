package es.sidelab.animalshelter.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.sidelab.animalshelter.Animal;
import es.sidelab.animalshelter.services.MyRestService;

@RestController
@RequestMapping("/api/")
public class APIMyRestController {
	
	@Autowired
	MyRestService service;
	
	
	@RequestMapping("/animalsfil/{filter}/{count}") //Returns the list of type selected animals
	public List<Animal> searchByType(@PathVariable String filter, @PathVariable int count) {
		
		return service.searchByType(filter, count);
	}
	
	@RequestMapping("/animalsearch/{names}/{count}") //Returns the list of animals search by their names
	public List<Animal> searchByName(@PathVariable String names, @PathVariable int count ) {
		
		return service.searchByName(names, count);
	}
	
	@RequestMapping("/suitedAnimals/{count}") //Returns the list of best suited animals for users space capacity
	public List<Animal> suitAnimal(@PathVariable int count) {
		
		return service.suitAnimal(count);
		
	}
	
	@GetMapping("/usergallerys/{count}")//this will return list of user's gallery
	public List<String> profileView(@PathVariable int count) {
		
		return service.profileView(count);
	}	
}
