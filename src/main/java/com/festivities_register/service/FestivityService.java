package com.festivities_register.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.festivities_register.domain.Festivity;
import com.festivities_register.repository.FestivityRepository;


@Service
public class FestivityService {
	@Autowired
	private FestivityRepository festivityRespository;

	@PersistenceContext
	private EntityManager em;

	public Festivity create(Festivity festivity) {
		Festivity festivityCreated = festivityRespository.saveAndFlush(festivity);
		return festivityCreated;
	}

	public List<Festivity> findAll() {
		return festivityRespository.findAll();
	}

	public Festivity update(long id, Festivity festivity) {
		if(festivityRespository.exists(id)){
			Festivity readFestivity = festivityRespository.getOne(id);
			Festivity preUpdatedFestivity = mapFields(readFestivity, festivity);
			return festivityRespository.saveAndFlush(preUpdatedFestivity);
		}
		return null; //TODO Handle exceptions
	}

	private Festivity mapFields(Festivity oldOne, Festivity newOne) {
		oldOne.setName(newOne.getName());
		oldOne.setStartDate(newOne.getStartDate());
		oldOne.setEndDate(newOne.getEndDate());
		oldOne.setPlace(newOne.getPlace());
		return oldOne;
	}

	public List<Festivity> customQuery(String name, String place, Calendar dateRangeStart, Calendar dateRangeEnd){
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Festivity> query = builder.createQuery(Festivity.class);
		List<Predicate> predicateList = new ArrayList<Predicate>(4);
		Root<Festivity> root = query.from(Festivity.class);
		Predicate predicate = null;
		if(name != null){ 
			predicate = addPredicate(predicate, builder.equal(root.get("name"), name), builder);
		}
		if(place != null){
			predicate = addPredicate(predicate, builder.equal(root.get("place"), place), builder);
		}
		if(dateRangeStart!= null){
			if(dateRangeEnd == null)
				predicate = addPredicate(predicate, builder.equal(root.get("startDate"), dateRangeStart), builder);
			else{
				/*ParameterExpression<Calendar> a = builder.parameter(Calendar.class, "dateRangeStart");
				ParameterExpression<Calendar> b = builder.parameter(Calendar.class, "dateRangeStart");*/
				predicate =addPredicate(predicate, builder.between(root.<Calendar> get("startDate"), dateRangeStart, dateRangeEnd), builder);
			}
		}

		query.where(predicate);
		return em.createQuery(query.select(root)).getResultList();
	}

	private Predicate addPredicate(Predicate p, Predicate newOne,CriteriaBuilder builder){
		if(p == null)
			return newOne;
		else
			return builder.and(p, newOne);
	}
}
