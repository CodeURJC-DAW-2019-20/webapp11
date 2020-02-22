package es.sidelab.animalshelter.controllers;

import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import es.sidelab.animalshelter.Adoption;
import es.sidelab.animalshelter.AdoptionRepository;
import es.sidelab.animalshelter.Animal;
import es.sidelab.animalshelter.AnimalRepository;
//import es.sidelab.animalshelter.FilterSuit;
import es.sidelab.animalshelter.Shelter;
import es.sidelab.animalshelter.ShelterRepository;
import es.sidelab.animalshelter.User;
import es.sidelab.animalshelter.UserGalleryPhoto;
import es.sidelab.animalshelter.UserGalleryPhotoRepository;
import es.sidelab.animalshelter.UserRepository;

@Controller
public class MainController {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserGalleryPhotoRepository userGalleryPhotoRepository;

	@Autowired
	private AnimalRepository animalRepository;

	@Autowired
	private ShelterRepository shelterRepository;

	// private FilterSuit filtersuit;
	private Authentication user;

	@Autowired
	private AdoptionRepository adoptionRepository;

	@PostConstruct
	public void init() {
		User user1 = new User("foto1", "Peter", "48755465Q", 22, "C/Manuela Malasaña,15", "Big", "Medium", 2, 5,
				"peter@gmail.com", "123");
		User user2 = new User("foto2", "Mary", "NO", 22, "C/Lambrusco,10", "Small", "NO", 1, 3, "mary@gmail.com",
				"123");

		userRepository.save(user1);
		userRepository.save(user2);
		Shelter shelter1 = new Shelter("Build Animal Future", "1123123123", "baf@shelter.com", "123", "NO",
				"C/Montana,1");
		Shelter shelter2 = new Shelter("Animal Rescue", "4325151451", "ar@shelter.com", "123", "NO", "C/Signium,7");

		shelterRepository.save(shelter1);
		shelterRepository.save(shelter2);
		
		Animal animal1 = new Animal("/animalshelter/src/main/resources/static/img/dg4.jpg", "Pipo", 1, "dog", "xl",
				"This animal was abbandoned in the river, please give it a second opportunity.", shelter1);
		Animal animal2 = new Animal("/animalshelter/src/main/resources/static/img/ct3.jpg", "Jen", 3, "cat", "s",
				"It owner died recently, please help it overcome.", shelter2);

		animalRepository.save(animal1);
		animalRepository.save(animal2);

		Adoption adoption = new Adoption(animal2, user1);
		adoptionRepository.save(adoption);

		String photo = "/animalshelter/src/main/resources/static/img/elements/a2.jpg";
		UserGalleryPhoto gallery = new UserGalleryPhoto(photo, user2);
		userGalleryPhotoRepository.save(gallery);

	}

	@RequestMapping("/")
	public String homeView(Model model) {

		return "index";
	}

	@RequestMapping("/animals")
	public String animalView(Model model) {
		return "animals";
	}

	@RequestMapping("/request")
	public String requestView(Model model) {
		return "request";
	}

	@RequestMapping("/profile")
	public String profileView(Model model) {
		return "profile";
	}

	@RequestMapping("/statistics")
	public String statisticsView(Model model) {
		return "statistics";
	}

	@RequestMapping("/contact")
	public String contactView(Model model) {
		return "contact";
	}

	@RequestMapping("/animalview")
	public String animalviewView(Model model) {
		return "animalview";
	}

}
