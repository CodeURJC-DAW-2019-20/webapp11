package es.sidelab.animalshelter.controllers;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import es.sidelab.animalshelter.Animal;
import es.sidelab.animalshelter.AnimalRepository;

@Controller
public class AnimalFormController {
	@Autowired
	private AnimalRepository animalRepository;
	@Autowired
	private ImageService imgService;
	
	@GetMapping("/animals")
	public String addAnimal(Model model) {//in animal html it will include information

		List<Animal> animal = (List<Animal>) animalRepository.findAll();
		
		model.addAttribute("animal", animal);

		return "animals";
	}
	@RequestMapping("/animalform")
	public String animalformView(Model model) {
		return "animalform";
	}
	
	@RequestMapping("/createAnimal")
	public String createAnimal(Model model, Animal animal, @RequestParam MultipartFile imagenFile) throws IOException {
        animalRepository.save(animal);	
		imgService.saveImage("animal", animal.getIdAnimal(), imagenFile);
		return "animalform";
	}
	@GetMapping("/animal/{id}")
	public String showAnimalInfo(Model model, @PathVariable int id) {
       
		Optional<Animal> animal = animalRepository.findById(id);
		if(animal.isPresent()) {
			model.addAttribute("animal", animal.get());
		}
		
		return "animalview";
	}
}
