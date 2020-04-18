package es.sidelab.animalshelter.api;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import es.sidelab.animalshelter.UserShelterAuthProvider;

@Configuration
@Order(1)
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class RestSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	public UserShelterAuthProvider userShelterAuthProvider;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.antMatcher("/api/**");
		
		http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/logIn").authenticated();
		
		// URLs that need authentication to access to it 
	        http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/users/galleries").hasAnyRole("USER", "SHELTER");
		http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/users/").hasRole("SHELTER");
		http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/users/{id}").hasRole("SHELTER");
		http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/users/adoptions/**").hasRole("USER");
		http.authorizeRequests().antMatchers(HttpMethod.PUT, "/api/users/**").hasRole("USER");
		http.authorizeRequests().antMatchers(HttpMethod.POST, "/api/users/adoptions/{id}").hasRole("USER");
		http.authorizeRequests().antMatchers(HttpMethod.DELETE, "/api/users/**").hasRole("USER");
		http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/shelters/**").hasRole("SHELTER");
		http.authorizeRequests().antMatchers(HttpMethod.PUT, "/api/shelters/**").hasRole("SHELTER");
		http.authorizeRequests().antMatchers(HttpMethod.DELETE, "/api/shelters/**").hasRole("SHELTER");
		http.authorizeRequests().antMatchers(HttpMethod.POST, "/api/animals/**").hasRole("SHELTER");
		http.authorizeRequests().antMatchers(HttpMethod.PUT, "/api/animals/**").hasRole("SHELTER");
		http.authorizeRequests().antMatchers(HttpMethod.DELETE, "/api/animals/**").hasRole("SHELTER");
		http.authorizeRequests().antMatchers(HttpMethod.PUT, "/api/adoptions/**").hasRole("SHELTER");
		http.authorizeRequests().antMatchers(HttpMethod.POST, "/api/adoptions/**").hasRole("USER");
		http.authorizeRequests().antMatchers(HttpMethod.DELETE, "/api/adoptions/**").hasRole("SHELTER");
		http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/searches/galleries").hasAnyRole("USER", "SHELTER");
		http.authorizeRequests().antMatchers(HttpMethod.POST, "/api/shelters/petitions/{id}").hasRole("SHELTER");


		
		// Other URLs can be accessed without authentication
		http.authorizeRequests().anyRequest().permitAll();

		// Disable CSRF protection (it is difficult to implement in REST APIs)
		http.csrf().disable();

		// Use Http Basic Authentication
		http.httpBasic();

		// Do not redirect when logout
		http.logout().logoutSuccessHandler((rq, rs, a) -> {	});
	}
	
	@Bean
    CorsConfigurationSource corsConfigurationSource() 
    {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("https://example.com"));
        configuration.setAllowedMethods(Arrays.asList("GET","POST"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		// Database authentication provider
		auth.authenticationProvider(userShelterAuthProvider);
	}
}