package es.sidelab.animalshelter;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShelterRepository extends JpaRepository<Shelter, Integer> {
	List<Shelter> findByShelterName(String shelterName);
	List<Shelter> findByShelterNif(String shelterNif);
	List<Shelter> findByShelterEmail(String shelterEmail);
	List<Shelter> findByShelterPassword(String shelterPassword);
	List<Shelter> findByShelterAverageRating(int shelterAverageRating);
	List<Shelter> findByShelterDescription(String shelterDescription);
	List<Shelter> findByShelterAdress(String shelterAdress);
	//List<Shelter> findByShelterAnimalList(ArrayList<Animal> shelterAnimalList);
	//List<Shelter> findByShelterAdoptionRequests(ArrayList<Adoption> shelterAdoptionRequests);
}
