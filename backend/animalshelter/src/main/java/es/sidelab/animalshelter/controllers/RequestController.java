package es.sidelab.animalshelter.controllers;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import es.sidelab.animalshelter.Adoption;
import es.sidelab.animalshelter.AdoptionRepository;
import es.sidelab.animalshelter.Animal;
import es.sidelab.animalshelter.AnimalRepository;
import es.sidelab.animalshelter.UserShelterComponent;
import es.sidelab.animalshelter.services.AdoptionService;
import es.sidelab.animalshelter.services.AnimalService;

@Controller
public class RequestController {

	@Autowired
	private AnimalService animalService;
	
	@Autowired
	private UserShelterComponent userShelterComponent;
	
	@Autowired
	private AdoptionService adoptionService;

	@RequestMapping("/request")
	public String requestView(Model model, HttpServletRequest request) {

		List<Animal> adoptedanimals = animalService.findAll();
		List<Animal> shelterCorrespondingAnimals = new ArrayList<>();
		List<Adoption> adoptions = adoptionService.findAll();
		
		for (Animal a : adoptedanimals) {
			if (a.getShelterOwner().getShelterEmail().equals(userShelterComponent.getShelter().getShelterEmail())) {
				for (Adoption adopt : adoptions) {
					if (adopt.isInCourse() && adopt.getAnimal().getAnimalName().equals(a.getAnimalName()))
						shelterCorrespondingAnimals.add(a);
				}
			}
		}

		model.addAttribute("animal", shelterCorrespondingAnimals);
		model.addAttribute("logged", userShelterComponent.isLoggedUser());
		model.addAttribute("isShelter", request.isUserInRole("SHELTER"));

		return "request";
	}

}