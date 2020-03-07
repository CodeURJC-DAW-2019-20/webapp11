package es.sidelab.animalshelter.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.sidelab.animalshelter.WebUser;
import es.sidelab.animalshelter.WebUserRepository;

@Service
public class WebUserService {

	@Autowired
	private WebUserRepository repository;

	@Autowired
	private ShelterService shelterService;


	public WebUser findByUserId(long id) {
		return repository.findByidUser(id);
	}
	
	public WebUser findByUserEmail(String email) {
		return repository.findByUserEmail(email);
	}

	public List<WebUser> findAll() {
		return repository.findAll();
	}

	public boolean save(WebUser user) {
		if (repository.findByUserEmail(user.getUserEmail()) != null
				|| shelterService.findByShelterEmail(user.getUserEmail()) != null) {

			return false;
		} else {
			repository.save(user);
			return true;
		}
	}
	
	public void update(WebUser user) {
		repository.save(user);
	}

	public void delete(long id) {
		repository.deleteById(id);
	}
}

