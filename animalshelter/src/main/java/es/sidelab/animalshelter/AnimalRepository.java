package es.sidelab.animalshelter;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnimalRepository extends JpaRepository<Animal, Long> {
	List<Animal> findByAnimalPhoto(String animalPhoto);

	List<Animal> findByAnimalName(String animalName);

	List<Animal> findByAnimalAge(int animalAge);

	List<Animal> findByAnimalType(String animalType);

	List<Animal> findByAnimalSize(String animalSize);

	List<Animal> findByAnimalDescription(String animalDescription);

	List<Animal> findByAnimalAdopted(boolean animalAdopted);

	List<Animal> findByAnimalDimensions(int animalDimensions);
}
