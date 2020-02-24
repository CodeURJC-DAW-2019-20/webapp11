package es.sidelab.animalshelter;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface WebUserRepository extends JpaRepository<WebUser, Long> {
	@Query("SELECT f.photo FROM UserGalleryPhoto f WHERE f.galleryOwner = :u")
	public List<String> getUserGalleryPhotos(WebUser u);

	List<WebUser> findByUserDni(String userDni);

	List<WebUser> findByUserAge(int userAge);

	List<WebUser> findByUserAdress(String userAdress);

	List<WebUser> findByUserHouseSize(String userHouseSize);

	List<WebUser> findByUserGarden(String userGarden);

	List<WebUser> findByUserNumChildren(int userNumChildren);

	List<WebUser> findByUserNumPeopleInHouse(int userNumPeopleInHouse);

	WebUser findByUserName(String userName);

	WebUser findByUserEmail(String userEmail);

	List<WebUser> findByUserCapacity(String userCapacity);
}
