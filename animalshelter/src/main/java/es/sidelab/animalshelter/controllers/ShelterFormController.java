package es.sidelab.animalshelter.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import es.sidelab.animalshelter.Shelter;
import es.sidelab.animalshelter.ShelterRepository;
import es.sidelab.animalshelter.WebUserRepository;

@Controller
public class ShelterFormController {
	
	@Autowired
	private WebUserRepository userRepository;
	
	@Autowired
	private ShelterRepository shelterRepository;

	@RequestMapping("/signshelter")
	public String signshelterView(Model model) {
		return "shelterform";
	}

	@RequestMapping("/createShelter")
	public String createShelter(Model model, Shelter shelter) {
		if (userRepository.findByUserEmail(shelter.getShelterEmail()) != null
				|| shelterRepository.findByShelterEmail(shelter.getShelterEmail()) != null) {

			return "shelterform";
		} else {
			shelterRepository.save(shelter);
			return "index";
		}
	}
}
