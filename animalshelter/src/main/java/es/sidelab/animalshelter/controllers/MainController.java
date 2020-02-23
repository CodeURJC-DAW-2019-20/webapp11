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
import es.sidelab.animalshelter.WebUser;
import es.sidelab.animalshelter.UserGalleryPhoto;
import es.sidelab.animalshelter.UserGalleryPhotoRepository;
import es.sidelab.animalshelter.WebUserRepository;

@Controller
public class MainController {

	@Autowired
	private WebUserRepository userRepository;

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

		WebUser user1 = new WebUser("Peter", "48755465Q", 22, "C/Manuela Malasaña,15", "Big", "Medium", 2, 5,
				"peter@gmail.com", "123");
		WebUser user2 = new WebUser("Mary", "NO", 22, "C/Lambrusco,10", "Small", "NO", 1, 3, "mary@gmail.com",
				"123");
		userRepository.save(user1);
		userRepository.save(user2);

		Shelter shelter1 = new Shelter("Build Animal Future", "1123123123", "baf@shelter.com", "123", "NO",
				"C/Montana,1");
		Shelter shelter2 = new Shelter("Animal Rescue", "4325151451", "ar@shelter.com", "123", "NO", "C/Signium,7");
		shelterRepository.save(shelter1);
		shelterRepository.save(shelter2);

		String photo = "/animalshelter/src/main/resources/static/img/elements/a2.jpg";
		UserGalleryPhoto galleryPhoto = new UserGalleryPhoto(photo);
		galleryPhoto.setGalleryOwner(user2);
		userGalleryPhotoRepository.save(galleryPhoto);

		Animal animal1 = new Animal("Pipo", 1, "dog", "xl",
				"This animal was abbandoned in the river, please give it a second opportunity.");
		animal1.setShelterOwner(shelter1);
		Animal animal2 = new Animal("Jen", 3, "cat", "s",
				"It owner died recently, please help it overcome.");
		animal2.setShelterOwner(shelter2);
		animalRepository.save(animal1);
		animalRepository.save(animal2);

		Adoption adoption1 = new Adoption(true);
		adoption1.setAnimal(animal1);
		adoption1.setUser(user1);
		adoptionRepository.save(adoption1);

	}

	@RequestMapping("/")
	public String homeView(Model model) {

		return "index";
	}

	@RequestMapping("/request")
	public String requestView(Model model) {
		return "request";
	}

	@RequestMapping("/profile")
	public String profileView(Model model) {
		return "profile";
	}

	@RequestMapping("/contact")
	public String contactView(Model model) {
		return "contact";
	}

}
