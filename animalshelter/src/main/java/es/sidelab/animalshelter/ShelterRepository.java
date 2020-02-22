package es.sidelab.animalshelter;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShelterRepository extends JpaRepository<Shelter, Long> {
	List<Shelter> findByShelterName(String shelterName);

	List<Shelter> findByShelterNif(String shelterNif);

	Shelter findByShelterEmail(String shelterEmail);

	List<Shelter> findByShelterPassword(String shelterPassword);

	List<Shelter> findByShelterAverageRating(int shelterAverageRating);

	List<Shelter> findByShelterDescription(String shelterDescription);

	List<Shelter> findByShelterAdress(String shelterAdress);

	List<Shelter> findByNumVotes(int numVotes);
}
