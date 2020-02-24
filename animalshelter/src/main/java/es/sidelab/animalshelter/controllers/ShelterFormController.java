package es.sidelab.animalshelter.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import es.sidelab.animalshelter.Shelter;
import es.sidelab.animalshelter.ShelterRepository;
import es.sidelab.animalshelter.UserShelterComponent;
import es.sidelab.animalshelter.WebUserRepository;

@Controller
public class ShelterFormController extends ModelAttributeController{
	
	@Autowired
	private WebUserRepository userRepository;
	
	@Autowired
	private ShelterRepository shelterRepository;
	
	@Autowired
	private UserShelterComponent userShelterComponent;

	@RequestMapping("/signshelter")
	public String signshelterView(Model model, HttpServletRequest request) {
		model.addAttribute("logged", userShelterComponent.isLoggedUser());
		model.addAttribute("isShelter", request.isUserInRole("SHELTER"));
		return "shelterform";
	}

	@PostMapping("/createShelter")
	public String createShelter(Model model, HttpServletRequest request, @RequestParam String shelterName, @RequestParam String shelterNif, 
			@RequestParam String shelterEmail, @RequestParam String shelterPassword,
			@RequestParam String shelterDescription, @RequestParam String shelterAdress) {
		if (userRepository.findByUserEmail(shelterEmail) != null
				|| shelterRepository.findByShelterEmail(shelterEmail) != null) {
			
			return "shelterform";
		} else {
			Shelter shelter = new Shelter(shelterName, shelterNif, shelterEmail, shelterPassword,
					shelterDescription, shelterAdress);
			shelterRepository.save(shelter);
			
			return "index";
		}
	}
}
