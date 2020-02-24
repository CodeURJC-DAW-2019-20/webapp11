package es.sidelab.animalshelter;

import java.sql.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdoptionRepository extends JpaRepository<Adoption, Long> {
	List<Adoption> findByAdoptionDate(Date adoptionDate);

	List<Adoption> findByInCourse(boolean inCourse);
}
