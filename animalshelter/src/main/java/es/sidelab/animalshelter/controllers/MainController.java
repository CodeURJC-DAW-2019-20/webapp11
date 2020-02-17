package es.sidelab.animalshelter.controllers;

import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import es.sidelab.animalshelter.Animal;
import es.sidelab.animalshelter.AnimalRepository;
import es.sidelab.animalshelter.Shelter;
import es.sidelab.animalshelter.ShelterRepository;
import es.sidelab.animalshelter.User;
import es.sidelab.animalshelter.UserRepository;

@Controller
public class MainController {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private AnimalRepository animalRepository;
	@Autowired
	private ShelterRepository shelterRepository;
	//@Autowired
	//private AdoptionRepository adoptionRepository;
	
	
	@PostConstruct
	public void init() {
		userRepository.save(new User("foto1","Peter","48755465Q",22,"C/Manuela Malasaña,15","Big","Medium",2,5,"peter@gmail.com","123"));
		userRepository.save(new User("foto2","Mary","NO",22,"C/Lambrusco,10","Small","NO",1,3,"mary@gmail.com","123"));
		animalRepository.save(new Animal("foto1","Pipo",1,"Husky","XL","White","NO",false));
		animalRepository.save(new Animal("foto2","Jen",3,"Mastiff","Small","Brown","NO",false));
		shelterRepository.save(new Shelter("Build Animal Future","1123123123","baf@shelter.com","123",5,"NO","C/Montana,1"));
		shelterRepository.save(new Shelter("Animal Rescue","4325151451","ar@shelter.com","123",3,"NO","C/Signium,7"));
		//adoptionRepository.save(new Adoption());
	}
	
	
	@RequestMapping("/")
	public String home(Model model) {
		return "index";
	}

}


