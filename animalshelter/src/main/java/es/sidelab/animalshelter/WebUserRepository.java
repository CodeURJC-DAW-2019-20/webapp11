package es.sidelab.animalshelter;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WebUserRepository extends JpaRepository<WebUser, Long> {
	List<WebUser> findByUserPhoto(String userPhoto);

	// List<User> findByUserName(String userName);

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
