package com.festivities_register_test.controller;

import static com.jayway.restassured.RestAssured.given;
import static com.jayway.restassured.RestAssured.when;
import static org.hamcrest.Matchers.equalTo;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.http.HttpStatus;

import com.festivities_register.domain.Festivity;
import com.jayway.restassured.http.ContentType;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class FestivityControllerIT {

	private final String FESTIVITY_ENDPOINT = "/festivities/";

	private final Calendar getStartDate(){
		SimpleDateFormat sdf = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss zzz");
		Date date = sdf.parse("Thu, 17 Mar 2016 00:00:00 UTC", new java.text.ParsePosition(0));
		Calendar startDate = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
		startDate.setTime(date);
		System.out.println("DATE "+startDate.getTime());
		return startDate;
	}

	private Calendar getEndDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss zzz");
		Date date = sdf.parse("Thu, 17 Mar 2016 23:59:59 UTC", new java.text.ParsePosition(0));
		Calendar endDate = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
		endDate.setTime(date);
		return endDate;
	}

	@Test
	public void test1CreateFestivity(){
		Festivity festivity = new Festivity("Saint Patrick's Day", getStartDate(), getEndDate(), "Italy");
		given().contentType(ContentType.JSON).body(festivity)
		.when().post(FESTIVITY_ENDPOINT).then()
		.assertThat().statusCode(equalTo(HttpStatus.OK.value()));
	}
	
	@Test
	public void test2GetAllFestivities(){
		when().get(FESTIVITY_ENDPOINT).then()
			.body("[0].name", equalTo("Saint Patrick's Day"))
			.body("[0].place", equalTo("Italy"));
	}
	
	@Test
	public void test3UpdateFestivity(){
		Festivity festivity = new Festivity("Saint Patrick's Day", getStartDate(), getEndDate(), "European Union");
		given().contentType(ContentType.JSON).body(festivity)
			.when().post(FESTIVITY_ENDPOINT+"1").then()
			.body("place", equalTo("European Union"));
	}
}
