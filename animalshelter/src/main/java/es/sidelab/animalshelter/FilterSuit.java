package es.sidelab.animalshelter;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class FilterSuit {

	@Autowired
	private UserRepository repoUser;
	@Autowired
	private AnimalRepository repoAnimal;

	private Authentication userActive;

	public List<Animal> Animalsuit() {

		List<Animal> animals = new ArrayList<>();

		User user = repoUser.findOneByUserName(userActive.getName());
		for (Animal a : repoAnimal.findAll()) {
			if (a.getAnimalDimensions() <= user.getUserCapacity()) {
				animals.add(a);
			}
		}
		return animals;
	}

}
