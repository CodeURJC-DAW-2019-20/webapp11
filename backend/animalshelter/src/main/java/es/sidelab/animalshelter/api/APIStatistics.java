package es.sidelab.animalshelter.api;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.sidelab.animalshelter.Adoption;
import es.sidelab.animalshelter.AdoptionRepository;

@RestController
@RequestMapping("/api/statistics")
public class APIStatistics {
	
	@Autowired
	AdoptionRepository adoptionRepository;
	
	@GetMapping("/{monthNum}")
	public ResponseEntity<Integer> adoptedAnimals(@PathVariable int monthNum) {
		String month = getMonth(monthNum);
		
		if(month != "NO") {
			Map<String, Integer> map = new LinkedHashMap<String, Integer>();
			List<Adoption> adoptionList = adoptionRepository.findAll();
			Adoption lastAdoption = adoptionList.get(adoptionList.size() - 1);
			// Actual Month
			LocalDate date = lastAdoption.getAdoptionDate().toLocalDate();
			// -1
			LocalDate date1 = date.minusMonths(1);
			// -2
			LocalDate date2 = date1.minusMonths(1);
			// -3
			LocalDate date3 = date2.minusMonths(1);
			// -4
			LocalDate date4 = date3.minusMonths(1);
			// -5
			LocalDate date5 = date4.minusMonths(1);
			map.put(date.getMonth().toString(), 0);
			map.put(date1.getMonth().toString(), 0);
			map.put(date2.getMonth().toString(), 0);
			map.put(date3.getMonth().toString(), 0);
			map.put(date4.getMonth().toString(), 0);
			map.put(date5.getMonth().toString(), 0);

			for (Adoption adoption : adoptionList) {
				String curr_month = adoption.getAdoptionDate().toLocalDate().getMonth().toString();
				if (map.containsKey(curr_month)) {
					Integer value = map.get(curr_month);
					value++;
					map.replace(curr_month, map.get(curr_month), value);
				}
			}
			if(map.get(month) != null) {
				return new ResponseEntity<>(map.get(month), HttpStatus.OK);				
			}
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
		

	}
	
	public String getMonth(int monthNum) {
		switch(monthNum) {
		case 1: return "JANUARY";
		case 2: return "FEBRUARY";
		case 3: return "MARCH";
		case 4: return "APRIL";
		case 5: return "MAY";
		case 6: return "JUNE";
		case 7: return "JULY";
		case 8: return "AUGUST";
		case 9: return "SEPTEMBER";
		case 10: return "OCTOBER";
		case 11: return "NOVEMBER";
		case 12: return "DECEMBER";
		default: return "NO";
		}
	}
}


