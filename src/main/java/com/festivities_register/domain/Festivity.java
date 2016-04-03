package com.festivities_register.domain;

import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Festivity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	private String name;
	private Calendar startDate;
	private Calendar endDate;
	private String place;
	

	public Festivity() {
	}

	public Festivity(String name, Calendar startDate, Calendar endDate, String place) {
		this.name = name;
		this.startDate = startDate;
		this.endDate = endDate;
		this.place = place;
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
