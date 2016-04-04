package com.festivities_register.controller;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.festivities_register.domain.Festivity;
import com.festivities_register.service.FestivityService;



@RestController
public class FestivityController {
	
	@Autowired
	private FestivityService festivityService;

	@RequestMapping(path = "/festivities/", method = RequestMethod.POST)
	public ResponseEntity<Festivity> createFestivity(@RequestBody Festivity festivity){
		if(isValidFestivity(festivity)){
			Festivity result = festivityService.create(festivity);
			return new ResponseEntity<Festivity>(result, HttpStatus.OK);
		}else{ 
			return new ResponseEntity<Festivity>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(path = "/festivities/", method = RequestMethod.GET)
	public ResponseEntity<List<Festivity>>  getAllFestivities(){
		List<Festivity> result = festivityService.findAll();
		return validateListResult(result);
	}

	@RequestMapping(path = "/festivities/{id}", method = RequestMethod.POST)
	public ResponseEntity<Festivity> updateFestivity(@PathVariable("id") long id, @RequestBody Festivity festivity){
		Festivity result = festivityService.update(id, festivity);
		return validateResult(result);
	}

	@RequestMapping(path = "/festivities", method = RequestMethod.GET)
	public ResponseEntity<List<Festivity>> queryByName(@RequestParam(value="name", required = false) String name,
									   @RequestParam(value="place", required = false) String place,
									   @RequestParam(value = "startDate1", required = false)
									   @DateTimeFormat(pattern="dd MMM yyyy zzz") Calendar dateRangeStart,
									   @RequestParam(value = "startDate2", required = false)
	   								   @DateTimeFormat(pattern="dd MMM yyyy zzz") Calendar dateRangeEnd){
		 List<Festivity> result = festivityService.customQuery(name, place, dateRangeStart, dateRangeEnd);
		 return validateListResult(result);
	}
	
	private ResponseEntity<List<Festivity>>  validateListResult(List<Festivity> result) {
		if (result.isEmpty())
			return new ResponseEntity<List<Festivity>>(HttpStatus.NOT_FOUND);
		else
			return new ResponseEntity<List<Festivity>>(result, HttpStatus.OK);
	}
	
	private ResponseEntity<Festivity> validateResult(Festivity result) {
		if (result == null)
			return new ResponseEntity<Festivity>(HttpStatus.NOT_FOUND);
		else
			return new ResponseEntity<Festivity>(result, HttpStatus.OK);
	}
	
	public boolean isValidFestivity(Festivity festivity){
		return festivity.getName() != null &&  festivity.getStartDate()!= null && festivity.getPlace() != null;
	}
	
	
}
