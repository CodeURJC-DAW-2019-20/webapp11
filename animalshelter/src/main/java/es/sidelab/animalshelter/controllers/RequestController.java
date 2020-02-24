package es.sidelab.animalshelter.controllers;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import es.sidelab.animalshelter.Animal;
import es.sidelab.animalshelter.AnimalRepository;
import es.sidelab.animalshelter.UserShelterComponent;

@Controller
public class RequestController {

	@Autowired
	private AnimalRepository animalRepository;
	
	@Autowired
	private UserShelterComponent userShelterComponent;

	@RequestMapping("/request")
	public String requestView(Model model, HttpServletRequest request) {

		List<Animal> adoptedanimals = animalRepository.getAllAnimalAdopted(true);
		List<Animal> shelterCorrespondingAnimals = new ArrayList<>();
		for (Animal a : adoptedanimals) {
			if (a.getShelterOwner().getShelterEmail().equals(userShelterComponent.getShelter().getShelterEmail())) {
				shelterCorrespondingAnimals.add(a);
			}
		}

		for (Animal a : adoptedanimals)
			System.err.println(a.getAnimalName());
		
		model.addAttribute("animal", shelterCorrespondingAnimals);
		model.addAttribute("logged", userShelterComponent.isLoggedUser());
		model.addAttribute("isShelter", request.isUserInRole("SHELTER"));

		return "request";
	}

}