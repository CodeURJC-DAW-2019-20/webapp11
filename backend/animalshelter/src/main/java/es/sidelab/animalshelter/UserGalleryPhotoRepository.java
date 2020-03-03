package es.sidelab.animalshelter;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserGalleryPhotoRepository extends JpaRepository<UserGalleryPhoto, Long> {
	Page<UserGalleryPhoto> findByIdPhoto(int idPhoto, Pageable page);

	Page<UserGalleryPhoto> findByPhoto(String photo, Pageable page);
}
