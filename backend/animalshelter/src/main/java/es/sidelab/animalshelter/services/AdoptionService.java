package es.sidelab.animalshelter.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import es.sidelab.animalshelter.Adoption;
import es.sidelab.animalshelter.AdoptionRepository;
import es.sidelab.animalshelter.Animal;

@Service
public class AdoptionService {

	@Autowired
	private AdoptionRepository repository;

	public Adoption findByAdoptionId(long id) {
		return repository.getOne(id);
	}

	public Adoption findByAnimal(Animal animal) {
		return repository.findByAnimal(animal);
	}

	public List<Adoption> findAll() {
		return repository.findAll();
	}

	public void save(Adoption adoption) {
		repository.save(adoption);
	}

	public void update(Adoption adoption) {
		repository.save(adoption);
	}

	public void delete(long id) {
		repository.deleteById(id);
	}

}
