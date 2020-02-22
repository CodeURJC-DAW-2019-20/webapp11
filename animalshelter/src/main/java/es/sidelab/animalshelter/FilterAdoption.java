package es.sidelab.animalshelter;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

public class FilterAdoption {
	public WebUser usuario;
	public String type;
	public List<Animal> animalesFiltro;
	@Autowired
	public AnimalRepository repoAnimal;
	
	
	public List<Animal>doFilter(String type) {
		for(Animal e: repoAnimal.findAll()) {
			if (e.getAnimalType().equalsIgnoreCase(type)) {
				animalesFiltro.add(e);
			}
		}
		return animalesFiltro;
		
	}
	
}
