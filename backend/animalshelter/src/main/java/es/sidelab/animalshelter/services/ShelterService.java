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

	public Shelter findByShelterEmail(String email) {
		return repository.findByShelterEmail(email);
	}

	public List<Shelter> findAll() {
		return repository.findAll();
	}

	public void save(Shelter shelter) {
		repository.save(shelter);
	}

	public void delete(long id) {
		repository.deleteById(id);
	}
}

