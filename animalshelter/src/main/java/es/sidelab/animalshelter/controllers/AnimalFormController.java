package es.sidelab.animalshelter.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
import es.sidelab.animalshelter.AnimalRepository;

@Controller
public class AnimalFormController {

	@Autowired
	private AnimalRepository animalRepository;

	@Autowired
	private ImageService imgService;

	@GetMapping("/animals")
	public String addAnimal(Model model) {// in animal html it will include information

		List<Animal> animal = (List<Animal>) animalRepository.findAll();

		model.addAttribute("animal", animal);

		return "animals";
	}

	@RequestMapping("/animalform")
	public String animalformView(Model model) {
		return "animalform";
	}

	@PostMapping("/createAnimal")
	public String createAnimal(Model model, @RequestParam MultipartFile imagenFile,
			@RequestParam String animalName, @RequestParam String animalType,
			@RequestParam  int animalAge, @RequestParam String animalDescription,
			@RequestParam String animalSize) throws IOException {
		
		Animal animal = new Animal(animalName, animalAge, animalType, animalSize,
				animalDescription);
		animalRepository.save(animal); // It's saved to get the id
		imgService.saveImage("animal", animal.getIdAnimal(), imagenFile);
		animal.setAnimalPhoto("image-" + animal.getIdAnimal() + ".jpg");
		animalRepository.save(animal);
		return "animalform";
	}

	@RequestMapping(value = "/animal")
	public String messageCenterHome(Model model, @ModelAttribute("filter") String filter) {
		List<Animal> animal = (List<Animal>) animalRepository.findAll();
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

	@RequestMapping(value = "/animalname")
	public String messageCenterHome2(Model model, @ModelAttribute("hola") String filter) {
		List<Animal> animal = (List<Animal>) animalRepository.findAll();
		List<Animal> animalFilter = new ArrayList<Animal>(animal);
		for (Animal mem : animal) {

			if (!mem.getAnimalName().matches(filter)) {
				animalFilter.remove(mem);
			}
		}

		model.addAttribute("animal", animalFilter);

		return "animals";
	}

	@GetMapping("/animal/{id}")
	public String showAnimalInfo(Model model, @PathVariable long id) {

		Optional<Animal> animal = animalRepository.findByIdAnimal(id);
		if (animal.isPresent()) {
			model.addAttribute("animal", animal.get());
		}

		return "animalview";
	}
}
