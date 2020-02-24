package es.sidelab.animalshelter;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
@SessionScope
public class UserShelterComponent {

	private WebUser user;
	private Shelter shelter;

	public Object getLoggedUser() {
		if(user != null) {
			return user;			
		} else {
			return shelter;
		}
	}
	
	public WebUser getUser() {
		return this.user;
	}
	
	public Shelter getShelter() {
		return this.shelter;
	}

	public void setLoggedUser(WebUser user) {
		this.user = user;
	}
	
	public void setLoggedUser(Shelter shelter) {
		this.shelter = shelter;
	}

	public boolean isLoggedUser() {
		if(user == null && shelter == null) {
			return false;
		} else {
			return true;
		}
	}

}
