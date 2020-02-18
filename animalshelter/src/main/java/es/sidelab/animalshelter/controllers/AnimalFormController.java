package es.sidelab.animalshelter.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import es.sidelab.animalshelter.Animal;
import es.sidelab.animalshelter.AnimalRepository;

@Controller
public class AnimalFormController {
	@Autowired
	private AnimalRepository animalRepository;
	
	@RequestMapping("/animalform")
	public String animalformView(Model model) {
		return "animalform";
	}
	
	@RequestMapping("/createAnimal")
	public String createAnimal(Model model, Animal animal) {
		animalRepository.save(animal);
		return "animalform";
	}
}
