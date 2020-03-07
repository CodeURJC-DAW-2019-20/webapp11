package es.sidelab.animalshelter.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.sidelab.animalshelter.Shelter;
import es.sidelab.animalshelter.ShelterRepository;

@Service
public class ShelterService {

	@Autowired
	private ShelterRepository repository;
	
	@Autowired
	private WebUserService webUserService;
	
	public Shelter findByShelterId(long id) {
		return repository.getOne(id);
	}

	public Shelter findByShelterEmail(String email) {
		return repository.findByShelterEmail(email);
	}

	public List<Shelter> findAll() {
		return repository.findAll();
	}

	public boolean save(Shelter shelter) {
		if (repository.findByShelterEmail(shelter.getShelterEmail()) != null
				|| webUserService.findByUserEmail(shelter.getShelterEmail()) != null) {
			return false;
		} else {
			repository.save(shelter);
			return true;
		}
	}
	
	public void update(Shelter shelter) {
		repository.save(shelter);
	}

	public void delete(long id) {
		repository.deleteById(id);
	}
}

