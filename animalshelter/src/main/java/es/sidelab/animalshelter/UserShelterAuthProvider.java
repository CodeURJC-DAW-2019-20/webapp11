package es.sidelab.animalshelter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserShelterAuthProvider implements AuthenticationProvider {

		@Autowired
		private WebUserRepository userRepository;
		
		@Autowired
		private ShelterRepository shelterRepository;

		@Autowired
		private UserShelterComponent userShelterComponent;

		/*
		 * This method checks for a user or a shelter by email entered in login form, if none is found
		 * it throws an error, but if one of them is found it checks which type is and gives authorization
		 */
		@Override
		public Authentication authenticate(Authentication authentication) throws AuthenticationException {

			String email = authentication.getName();
			String password = (String) authentication.getCredentials();
	
			WebUser user = userRepository.findByUserEmail(email);
			Shelter shelter = shelterRepository.findByShelterEmail(email);

			if (user == null && shelter == null) {
				throw new BadCredentialsException("User not found");
			}
			
			if(user != null) {
				
				if (!new BCryptPasswordEncoder().matches(password, user.getUserPassword())) {
					throw new BadCredentialsException("Wrong password");
				} else {

					userShelterComponent.setLoggedUser(user);
					List<GrantedAuthority> roles = new ArrayList<>();
					roles.add(new SimpleGrantedAuthority("ROLE_USER"));
					return new UsernamePasswordAuthenticationToken(email, password, roles);
				}
			} else {
				 
				if (!new BCryptPasswordEncoder().matches(password, shelter.getShelterPassword())) {
					throw new BadCredentialsException("Wrong password");
				} else {

					userShelterComponent.setLoggedUser(shelter);
					List<GrantedAuthority> roles = new ArrayList<>();
					roles.add(new SimpleGrantedAuthority("ROLE_SHELTER"));
					return new UsernamePasswordAuthenticationToken(email, password, roles);
				}
			}

			
		}

		@Override
		public boolean supports(Class<?> authenticationObject) {
			return true;
		}
	}
