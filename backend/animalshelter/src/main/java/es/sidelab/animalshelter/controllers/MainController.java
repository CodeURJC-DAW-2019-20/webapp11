package es.sidelab.animalshelter.controllers;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import es.sidelab.animalshelter.Animal;
import es.sidelab.animalshelter.AnimalRepository;
import es.sidelab.animalshelter.Graph;
import es.sidelab.animalshelter.Shelter;
import es.sidelab.animalshelter.ShelterRepository;
import es.sidelab.animalshelter.WebUser;
import es.sidelab.animalshelter.UserGalleryPhoto;
import es.sidelab.animalshelter.UserGalleryPhotoRepository;
import es.sidelab.animalshelter.WebUserRepository;
import es.sidelab.animalshelter.Adoption;
import es.sidelab.animalshelter.AdoptionRepository;

@Controller
public class MainController extends ModelAttributeController {

	@Autowired
	private WebUserRepository userRepository;

	@Autowired
	private UserGalleryPhotoRepository userGalleryPhotoRepository;

	@Autowired
	private AnimalRepository animalRepository;

	@Autowired
	private ShelterRepository shelterRepository;

	@Autowired
	private AdoptionRepository adoptionRepository;

	@PostConstruct
	public void init() {

		WebUser user1 = new WebUser("Peter", "48755465Q", 22, "C/Manuela Malasaña,15", "l", "m", 2, 5,
				"saralorensana@gmail.com", "123");
		WebUser user2 = new WebUser("Mary", "no", 22, "C/Lambrusco,10", "s", "no", 1, 3,
				"iho.ladamadeltiempo@gmail.com", "123");
                user1.setUserphoto("image-1.jpg");
		user2.setUserphoto("image-2.jpg");
		userRepository.save(user1);
		userRepository.save(user2);

		Shelter shelter1 = new Shelter("Build Animal Future", "1123123123", "arshiasaleem98@gmail.com", "123", "NO",
				"C/Montana,1");
		Shelter shelter2 = new Shelter("Animal Rescue", "4325151451", "mavra.1bachillerato@gmail.com", "123", "NO",
				"C/Signium,7");
		shelterRepository.save(shelter1);
		shelterRepository.save(shelter2);

		String photo = "images/user/a.jpg";
		UserGalleryPhoto galleryPhoto = new UserGalleryPhoto(photo);
		galleryPhoto.setGalleryOwner(user2);
		userGalleryPhotoRepository.save(galleryPhoto);

		Animal animal1 = new Animal("Pipo", 1, "dog", "xl",
				"This animal was abbandoned in the river, please give it a second opportunity.");
		animal1.setAnimalPhoto("image-6.jpg");
		animal1.setShelterOwner(shelter1);
		Animal animal2 = new Animal("Jen", 3, "cat", "s", "It owner died recently, please help it overcome.");
		animal2.setShelterOwner(shelter2);
		animal2.setAnimalPhoto("image-7.jpg");
		animalRepository.save(animal1);
		animalRepository.save(animal2);
		Animal animal3 = new Animal("Peter", 2, "reptile", "m", "Very good behaoviur of this animal");
		animal3.setShelterOwner(shelter1);
		animal3.setAnimalPhoto("image-8.jpg"); // Cambiar foto
		animalRepository.save(animal3);
		animalRepository.save(animal3);

		// Extra
		Animal animal4 = new Animal("bal", 2, "dog", "m", "Very intelligent");
		animal4.setShelterOwner(shelter1);
		animal4.setAnimalPhoto("image-9.jpg"); // Cambiar foto
		animalRepository.save(animal4);
		animalRepository.save(animal4);

		Adoption adoption1 = new Adoption(false);
		adoption1.setAnimal(animal1);
		adoption1.setUser(user1);
		adoptionRepository.save(adoption1);

		Animal animal5 = new Animal("sweety", 2, "cat", "m", "Very good behaoviur of this animal");
		animal5.setShelterOwner(shelter1);
		animal5.setAnimalPhoto("image-11.jpg"); // Cambiar foto
		animalRepository.save(animal5);
		animalRepository.save(animal5);
	}
	
	//Connect to Angular
	@RequestMapping("/new")
	public String AngularConnection() {
		return "new/index.html";
	}
	@RequestMapping("/new/")
	public String AngularConnection2() {
		return "new/index.html";
	}

	@RequestMapping("/")
	public String homeView(Model model, HttpServletRequest request) {
		List<String> carusel = animalRepository.getAllAnimalPhotos();
		model.addAttribute("carusel", carusel);
		// this.adoptedAnimals(model);
		return "index";
	}

	@RequestMapping("/stat")
	public String homeView2(Model model, HttpServletRequest request) {

		this.adoptedAnimals(model);
		return "statistics";
	}

	public void adoptedAnimals(Model model) {
		Map<String, Integer> map = new LinkedHashMap<String, Integer>();
		List<Adoption> adoptionList = adoptionRepository.findAll();
		Adoption lastAdoption = adoptionList.get(adoptionList.size() - 1);
		// Actual Month
		LocalDate date = lastAdoption.getAdoptionDate().toLocalDate();
		// -1
		LocalDate date1 = date.minusMonths(1);
		// -2
		LocalDate date2 = date1.minusMonths(1);
		// -3
		LocalDate date3 = date2.minusMonths(1);
		// -4
		LocalDate date4 = date3.minusMonths(1);
		// -5
		LocalDate date5 = date4.minusMonths(1);
		map.put(date.getMonth().toString(), 0);
		map.put(date1.getMonth().toString(), 0);
		map.put(date2.getMonth().toString(), 0);
		map.put(date3.getMonth().toString(), 0);
		map.put(date4.getMonth().toString(), 0);
		map.put(date5.getMonth().toString(), 0);
		

		for (Adoption adoption : adoptionList) {
			String curr_month = adoption.getAdoptionDate().toLocalDate().getMonth().toString();
			if (map.containsKey(curr_month)) {
				Integer value = map.get(curr_month);
				value++;
				map.replace(curr_month, map.get(curr_month), value);
			}
		}
		int number = 1;
		for (Map.Entry<String, Integer> myMap : map.entrySet()) {
			Graph myGraph = new Graph(myMap.getKey(), myMap.getValue());
			model.addAttribute(String.valueOf(number), myGraph);
			number++;
		}

	}

}
