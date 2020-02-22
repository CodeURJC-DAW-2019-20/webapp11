package es.sidelab.animalshelter;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserGalleryPhotoRepository extends JpaRepository<UserGalleryPhoto, Integer> {

	Page<UserGalleryPhoto> findAll(Pageable pageable);

	List<UserGalleryPhoto> findAll(Sort sort);

	List<UserGalleryPhoto> findAllById(Iterable<Integer> ids);

}
