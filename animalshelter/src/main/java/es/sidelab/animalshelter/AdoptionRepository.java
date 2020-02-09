package es.sidelab.animalshelter;

import java.sql.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdoptionRepository extends JpaRepository<Adoption, Integer> {
	List<Animal> findByAdoptionDate(Date adoptionDate);
	List<Animal> findByAdoptionAnimalID(String adoptionAnimalID);
	List<Animal> findByAdoptionUserID(String aoptionUserID);
}
