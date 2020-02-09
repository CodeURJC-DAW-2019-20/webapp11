package es.sidelab.animalshelter;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private AnimalRepository animalRepository;
	@Autowired
	private ShelterRepository shelterRepository;
	@Autowired
	private AdoptionRepository adoptionRepository;
	
	
	@PostConstruct
	public void init() {
		userRepository.save(new User());
		animalRepository.save(new Animal());
		shelterRepository.save(new Shelter());
		adoptionRepository.save(new Adoption());
	}
	
	
	@RequestMapping("/index")
	public String greeting(Model model) {
		//model.addAttribute("name", "World");
		return "index";
	}
	
	
}


