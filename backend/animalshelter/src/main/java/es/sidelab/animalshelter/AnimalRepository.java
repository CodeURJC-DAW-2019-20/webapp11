package es.sidelab.animalshelter;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AnimalRepository extends JpaRepository<Animal, Long> {

	@Query("SELECT a.animalPhoto FROM Animal a")
	public List<String> getAllAnimalPhotos();
	
	@Query("SELECT a FROM Animal a WHERE a.animalAdopted = :b")
	public List<Animal> getAllAnimalAdopted(boolean b);

	Optional<Animal> findByIdAnimal(long idAnimal);

	List<Animal> findByAnimalPhoto(String animalPhoto);
	
	Animal findByAnimalName(String animalName);

	List<Animal> findByAnimalAge(int animalAge);

	List<Animal> findByAnimalType(String animalType);

	List<Animal> findByAnimalSize(String animalSize);

	List<Animal> findByAnimalDescription(String animalDescription);

	List<Animal> findByAnimalAdopted(boolean animalAdopted);

	List<Animal> findByAnimalDimensions(int animalDimensions);
}
