package es.sidelab.animalshelter.controllers;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import es.sidelab.animalshelter.Animal;
import es.sidelab.animalshelter.AnimalRepository;
import es.sidelab.animalshelter.UserShelterComponent;
import es.sidelab.animalshelter.WebUser;

@RestController
public class myRestcontroller extends ModelAttributeController{

	@Autowired
	private AnimalRepository animalRepository;
	
	@Autowired
	private UserShelterComponent loggeduser;
	
	@RequestMapping("/animalfil/{filter}/{count}") //Returns the list of type selected animals
	public List<Animal> searchByType(@PathVariable String filter, @PathVariable int count) {
		int counts = count + 3;
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
		
		for (int i = count; i < animalFilter.size() && i < counts ; i++) {
			result.add(animalFilter.get(i));
		}
		
		return result;
	}
	
	
	
	@RequestMapping("/animalsearch/{names}/{count}") //Returns the list of animals search by their names
	public List<Animal> searchByName(@PathVariable String names, @PathVariable int count ) {
		int counts = count + 3;
		System.out.println(counts);
		List<Animal> result = new ArrayList<>();
		List<Animal> animal = (List<Animal>) animalRepository.findAll();
		List<Animal> animalSearch = new ArrayList<Animal>(animal);
		for (Animal mem : animal) {

			if (!mem.getAnimalName().matches(names)) {
				animalSearch.remove(mem);
			}
		}

		for (int i = count; i < animalSearch.size() && i < counts ; i++) {
			result.add(animalSearch.get(i));
		}
		
		return result;
	}
	
	
	@RequestMapping("/suitedAnimal/{count}") //Returns the list of best suited animals for users space capacity
	public List<Animal> suitAnimal(@PathVariable int count) {
		int counts = count + 3;
		List<Animal> result = new ArrayList<>();
		List<Animal> animalSuit = (List<Animal>) animalRepository.findAll();
		List<Animal> suited = new ArrayList<Animal>();
		WebUser userActive=  loggeduser.getUser();
		for (Animal mem : animalSuit) {

			if (mem.getAnimalDimensions()<= userActive.getUserCapacity()) {
				suited.add(mem);
			}
		}
		for (int i = count; i < suited.size() && i < counts ; i++) {
			result.add(suited.get(i));
		}
		
		return result;
		
	}
	
}
