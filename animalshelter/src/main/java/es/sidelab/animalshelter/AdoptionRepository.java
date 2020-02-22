package es.sidelab.animalshelter;

import java.sql.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdoptionRepository extends JpaRepository<Adoption, Long> {
	List<Animal> findByAdoptionDate(Date adoptionDate);

	List<Animal> findByInCourse(boolean inCourse);
}
