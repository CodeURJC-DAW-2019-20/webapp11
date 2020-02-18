package es.sidelab.animalshelter.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import es.sidelab.animalshelter.Shelter;
import es.sidelab.animalshelter.ShelterRepository;
import es.sidelab.animalshelter.UserRepository;

@Controller
public class ShelterFormController {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private ShelterRepository shelterRepository;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@RequestMapping("/signshelter")
	public String signshelterView(Model model) {
		return "shelterform";
	}
	
	@RequestMapping("/createShelter")
	public String createShelter(Model model, Shelter shelter) {
		if(userRepository.findByUserEmail(shelter.getShelterEmail()).size() > 0 || 
			shelterRepository.findByShelterEmail(shelter.getShelterEmail()).size() > 0) {
			
			return "shelterform";
		} else {
			shelter.setShelterPassword(bCryptPasswordEncoder.encode(shelter.getShelterPassword()));
			shelterRepository.save(shelter);
			return "index";
		}
	}
}
