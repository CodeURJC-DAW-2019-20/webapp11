package es.sidelab.animalshelter;

import java.util.List;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

public class FilterAdoption {
	public User usuario;
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
