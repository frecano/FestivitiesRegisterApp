package com.festivities_register.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.festivities_register.domain.Festivity;
import com.festivities_register.service.FestivityService;

@RestController
public class FestivityController {
	
	@Autowired
	private FestivityService festivityService;

	@RequestMapping(path = "/festivities/", method = RequestMethod.POST)
	public Festivity createFestivity(@RequestBody Festivity festivity){
		return festivityService.create(festivity);
	}
	
	@RequestMapping(path = "/festivities/", method = RequestMethod.GET)
	public List<Festivity> getAllFestivities(){
		return festivityService.findAll();
	}
	
	@RequestMapping(path = "/festivities/{id}", method = RequestMethod.POST)
	public Festivity updateFestivity(@PathVariable("id") long id, @RequestBody Festivity festivity){
		return festivityService.update(id, festivity);
	}
}
