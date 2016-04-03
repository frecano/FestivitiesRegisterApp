package com.festivities_register.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.festivities_register.domain.Festivity;
import com.festivities_register.repository.FestivityRepository;


@Service
public class FestivityService {
	@Autowired
	private FestivityRepository festivityRespository;
	
	public Festivity create(Festivity festivity) {
		Festivity festivityCreated = festivityRespository.saveAndFlush(festivity);
		return festivityCreated;
	}
	
	public List<Festivity> findAll() {
		return festivityRespository.findAll();
	}
}
	