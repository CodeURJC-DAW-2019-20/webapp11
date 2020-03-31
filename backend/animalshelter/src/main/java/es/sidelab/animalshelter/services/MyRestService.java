package es.sidelab.animalshelter.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.sidelab.animalshelter.Animal;
import es.sidelab.animalshelter.AnimalRepository;
import es.sidelab.animalshelter.UserShelterComponent;
import es.sidelab.animalshelter.WebUser;
import es.sidelab.animalshelter.WebUserRepository;

@Service
public class MyRestService {
	@Autowired
	private AnimalRepository animalRepository;

	@Autowired
	private UserShelterComponent loggeduser;

	@Autowired
	private WebUserRepository ur;

	public List<Animal> searchByType(String filter, int count) {
		int counts = count  + 3;
		List<Animal> result = new ArrayList<>();
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

		for (int i = count; i < animalFilter.size() && i < counts; i++) {
			result.add(animalFilter.get(i));
		}
		return result;
	}

	public List<Animal> searchByName(String names, int count) {
		int counts = count  + 3;
		System.out.println(counts);
		List<Animal> result = new ArrayList<>();
		List<Animal> animal = (List<Animal>) animalRepository.findAll();
		List<Animal> animalSearch = new ArrayList<Animal>(animal);
		for (Animal mem : animal) {

			if (!mem.getAnimalName().matches(names)) {
				animalSearch.remove(mem);
			}
		}

		for (int i = count; i < animalSearch.size() && i < counts; i++) {
			result.add(animalSearch.get(i));
		}
		
		return result;
	}

	public List<Animal> suitAnimal(int count) {
		int counts = count  + 3;
		List<Animal> result = new ArrayList<>();
		List<Animal> animalSuit = (List<Animal>) animalRepository.findAll();
		List<Animal> suited = new ArrayList<Animal>();
		if(loggeduser.getRole() == "USER") {
			WebUser userActive = loggeduser.getUser();
			for (Animal mem : animalSuit) {
	
				if (mem.getAnimalDimensions() <= userActive.getUserCapacity()) {
					suited.add(mem);
				}
			}
			for (int i = count; i < suited.size() && i < counts; i++) {
				result.add(suited.get(i));
			}
			return result;
		}
		return null;

	}

	public List<String> profileView(int count) {
		int counts = count + 3;
		List<String> result = new ArrayList<>();

		WebUser lu = (WebUser) loggeduser.getLoggedUser();

		List<String> gallery = new ArrayList<>();
		gallery = ur.getUserGalleryPhotos(lu);
		for (int i = count; i < gallery.size() && i < counts; i++) {
			result.add(gallery.get(i));
		}

		return result;
	}
}
