package com.festivities_register.domain;

import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.festivities_register.beanvalidation.DateRangeValidation;

@Entity
@DateRangeValidation(from = "startDate", to = "endDate")
public class Festivity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@NotEmpty
	private String name;
	@NotNull
	private Calendar startDate;
	private Calendar endDate;
	@NotEmpty
	private String place;
	

	public Festivity() {
	}

	public Festivity(String name, Calendar startDate, Calendar endDate, String place) {
		this.name = name;
		this.place = place;
		this.startDate = startDate;
		if(endDate == null)
			this.endDate = startDate;
		else
			this.endDate = endDate;
	}
	
	public long getId() {
		return id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setStartDate(Calendar startDate) {
		this.startDate = startDate;
	}

	public void setEndDate(Calendar endDate) {
		this.endDate = endDate;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public String getName() {
		return name;
	}

	public final Calendar getStartDate() {
		return startDate;
	}

	public final Calendar getEndDate() {
		return endDate;
	}

	public String getPlace() {
		return place;
	}
}
