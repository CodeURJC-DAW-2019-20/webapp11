package es.sidelab.animalshelter.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;

import es.sidelab.animalshelter.UserShelterComponent;

@Controller
public class ModelAttributeController {
	
	@Autowired
	private UserShelterComponent userShelterComponent;
	
	@ModelAttribute
	public void addAttributes(Model model, HttpServletRequest request) {
		model.addAttribute("logged", userShelterComponent.isLoggedUser());
		model.addAttribute("isShelter", request.isUserInRole("SHELTER"));
	}

}
