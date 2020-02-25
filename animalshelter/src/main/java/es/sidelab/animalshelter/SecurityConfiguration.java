package es.sidelab.animalshelter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration 
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	

    String[] resources = new String[]{
            "/fonts/**","/css/**","/img/**","/js/**","/scss/**"
    };
    
    @Autowired
	public UserShelterAuthProvider userShelterAuthProvider;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		// Database authentication provider
		auth.authenticationProvider(userShelterAuthProvider);
	}
	public BCryptPasswordEncoder passwordEncoder() {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		return bCryptPasswordEncoder;
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		// URLs that need authentication to access to it
		http.authorizeRequests().antMatchers("/profile").hasRole("USER");
		http.authorizeRequests().antMatchers("/animalform").hasRole("SHELTER");
		http.authorizeRequests().antMatchers("/createAnimal").hasRole("SHELTER");
		http.authorizeRequests().antMatchers("/request").hasRole("SHELTER");
		
		// Login form
	    http.formLogin().loginPage("/");
		http.formLogin().loginProcessingUrl("/login");
	    http.formLogin().usernameParameter("username");
	    http.formLogin().passwordParameter("password");
	    http.formLogin().defaultSuccessUrl("/");
	    http.formLogin().failureUrl("/error");
	    
	 // Logout
        http.logout().logoutUrl("/logout");
        http.logout().logoutSuccessUrl("/");
	
	}
 
}
