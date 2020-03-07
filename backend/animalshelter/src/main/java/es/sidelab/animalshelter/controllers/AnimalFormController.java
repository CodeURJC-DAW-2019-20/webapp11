package es.sidelab.animalshelter.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import es.sidelab.animalshelter.Animal;
import es.sidelab.animalshelter.UserShelterComponent;
import es.sidelab.animalshelter.WebUser;
import es.sidelab.animalshelter.services.AnimalService;

@Controller
public class AnimalFormController extends ModelAttributeController{

	@Autowired
	private AnimalService service;

	@Autowired
	private UserShelterComponent userShelterComponent;

	@Autowired
	private ImageService imgService;

	@RequestMapping("/animals")
	public String addAnimal(Model model, HttpServletRequest request) {// in animal html it will include information

		List<Animal> animal = service.findAll();

		model.addAttribute("animal", animal);
		return "animals";
	}
	
	@RequestMapping("/animalform")
	public String animalformView(Model model, HttpServletRequest request) {
		return "animalform";
	}

	@PostMapping("/createAnimal")
	public String createAnimal(Model model, HttpServletRequest request, @RequestParam MultipartFile imagenFile,
			@RequestParam String animalName, @RequestParam String animalType, @RequestParam int animalAge,
			@RequestParam String animalDescription, @RequestParam String animalSize) throws IOException {

		Animal animal = new Animal(animalName, animalAge, animalType, animalSize, animalDescription);
		service.save(animal); // It's saved to get the id
		imgService.saveImage("animal", animal.getIdAnimal(), imagenFile);
		animal.setAnimalPhoto("image-" + animal.getIdAnimal() + ".jpg");
		animal.setShelterOwner(userShelterComponent.getShelter());
		service.save(animal);
		return "animalform";
	}

	@RequestMapping(value = "/animal") //Returns the type filter search
	public String animalFilterProcess(Model model, @ModelAttribute("filter") String filter, HttpServletRequest request) {
		List<Animal> animal = service.findAll();
		List<Animal> animalFilter = new ArrayList<Animal>(animal);
		for (Animal mem : animal) {
			if (filter.matches("others")) {
				if (mem.getAnimalType().matches("dog|reptile|cat|bird")) {
					animalFilter.remove(mem);
				}

			} else {
				if (!filter.matches("all")) {
					if (!mem.getAnimalType().matches(filter)) {
						animalFilter.remove(mem);
					}
				}
			}
		}
		model.addAttribute("animal", animalFilter);
		
		return "animals";
	}

	@RequestMapping(value = "/animalname") //Returns the animalName search
	public String animalSearchByName(Model model, @ModelAttribute("hola") String filter, HttpServletRequest request) {
		List<Animal> animal = (List<Animal>) service.findAll();
		List<Animal> animalFilter = new ArrayList<Animal>(animal);
		for (Animal mem : animal) {

			if (!mem.getAnimalName().matches(filter)) {
				animalFilter.remove(mem);
			}
		}

		model.addAttribute("animal", animalFilter);
		return "animals";
	}
	
	@RequestMapping(value = "/animalspecial") //Returns the best animals for your own space capacitys
	public String userSuitedanimal(Model model, @ModelAttribute("hola") String filter, HttpServletRequest request) {
		
		List<Animal> animalFilter = new ArrayList<Animal>();
		WebUser userActive=  userShelterComponent.getUser();
		for (Animal mem : service.findAll()) {

			if (mem.getAnimalDimensions()<= userActive.getUserCapacity()) {
				animalFilter.add(mem);
			}
		}
		model.addAttribute("animal", animalFilter);
		
		return "animals";
	}

	@GetMapping("/animal/{idAnimal}")
	public String showAnimalInfo(Model model, @PathVariable long idAnimal, HttpServletRequest request) {

		Optional<Animal> animal = Optional.of(service.findByAnimalId(idAnimal));
		if (animal.isPresent()) {
			model.addAttribute("animal", animal.get());
		}
		return "animalview";
	}
}
