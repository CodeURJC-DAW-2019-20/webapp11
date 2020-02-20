package es.sidelab.animalshelter;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
	List<User> findByUserPhoto(String userPhoto);
	List<User> findByUserName(String userName);
	List<User> findByUserDni(String userDni);
	List<User> findByUserAge(int userAge);
	List<User> findByUserAdress(String userAdress);
	List<User> findByUserHouseSize(String userHouseSize);
	List<User> findByUserGarden(String userGarden);
	List<User> findByUserNumChildren(int userNumChildren);
	List<User> findByUserNumPeopleInHouse(int userNumPeopleInHouse);
	User findByUserEmail(String userEmail);
	List<User> findByUserPassword(String userPassword);
}
