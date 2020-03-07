package es.sidelab.animalshelter.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import es.sidelab.animalshelter.Shelter;
import es.sidelab.animalshelter.services.ShelterService;

@Controller
public class ShelterFormController extends ModelAttributeController {
	
	@Autowired
	private ShelterService service;

	@RequestMapping("/signshelter")
	public String signshelterView(Model model) {
		return "shelterform";
	}

	@PostMapping("/createShelter")
	public String createShelter(Model model, HttpServletRequest request, @RequestParam String shelterName, @RequestParam String shelterNif, 
			@RequestParam String shelterEmail, @RequestParam String shelterPassword,
			@RequestParam String shelterDescription, @RequestParam String shelterAdress) {
		Shelter shelter = new Shelter(shelterName, shelterNif, shelterEmail, shelterPassword,
				shelterDescription, shelterAdress);
		
		if (service.save(shelter)) {
			return "index";
		} else {
			return "shelterform";
		}
	}
}
