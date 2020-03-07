package es.sidelab.animalshelter.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import es.sidelab.animalshelter.Animal;
import es.sidelab.animalshelter.AnimalRepository;

@Service
public class AnimalService {

	@Autowired
	private AnimalRepository repository;

	public Animal findByAnimalId(long id) {
		return repository.getOne(id);
	}

	public List<Animal> findAll() {
		return repository.findAll();
	}

	public void save(Animal animal) {
		repository.save(animal);
	}

	public void delete(long id) {
		repository.deleteById(id);
	}

}
