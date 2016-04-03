package com.festivities_register_test.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import org.junit.Test;
import org.springframework.http.HttpStatus;

import com.festivities_register.domain.Festivity;
import com.jayway.restassured.http.ContentType;

import static com.jayway.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

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
}
