package es.sidelab.animalshelter.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.sidelab.animalshelter.UserGalleryPhoto;
import es.sidelab.animalshelter.UserGalleryPhotoRepository;

@Service
public class UserGalleryService {
	@Autowired
	private UserGalleryPhotoRepository repository;

	public UserGalleryPhoto findByGalleryId(long id) {
		return repository.getOne(id);
	}

	public List<UserGalleryPhoto> findAll() {
		return repository.findAll();
	}

	public void save(UserGalleryPhoto userGallery) {
		repository.save(userGallery);
	}

	public void update(UserGalleryPhoto userGallery) {
		repository.save(userGallery);
	}

	public void delete(long id) {
		repository.deleteById(id);
	}

}
