package es.sidelab.animalshelter.controllers;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import es.sidelab.animalshelter.Animal;
import es.sidelab.animalshelter.services.MyRestService;

@RestController
public class MyRestcontroller extends ModelAttributeController{

	@Autowired
	MyRestService service;
	
	
	@RequestMapping("/{count}/animals") //Returns the list of type selected animals
	
	public List<Animal> searchByType(@RequestParam String type,@PathVariable int count) {
		
		return service.searchByType(type,count);
	}
	
	@RequestMapping("/{count}/animal") //Returns the list of animals search by their names
	public List<Animal> searchByName(@RequestParam String name, @PathVariable int count ) {
		
		return service.searchByName(name, count);
	}
	
	
	@RequestMapping("/suitedAnimal/{count}") //Returns the list of best suited animals for users space capacity
	public List<Animal> suitAnimal(@PathVariable int count) {
		
		return service.suitAnimal(count);
		
	}
	
	
	@RequestMapping("/usergallerys/{count}")//this will return list of user's gallery
	public List<String> profileView(@PathVariable int count) {
		
		return service.profileView(count);
	}
	
}
