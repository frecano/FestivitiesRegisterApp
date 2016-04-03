package com.festivities_register_test.service;

import static org.junit.Assert.assertEquals;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.festivities_register.domain.Festivity;
import com.festivities_register.repository.FestivityRepository;
import com.festivities_register.service.FestivityService;

public class FestivityServiceTest {

	private MockMvc mockMvc;  
	@InjectMocks  
	private FestivityService festivityService; 

	@Mock
	private FestivityRepository mockFestivityRepository;


	@Before  
	public void setup() {  
		MockitoAnnotations.initMocks(this);  
		this.mockMvc = MockMvcBuilders.standaloneSetup(festivityService).build();  
	}  

	private final Calendar getStartDate(){
		SimpleDateFormat sdf = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss zzz");
		Date date = sdf.parse("Tue, 08 Feb 2016 00:00:00 UTC", new java.text.ParsePosition(0));
		Calendar startDate = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
		startDate.setTime(date);
		System.out.println("DATE "+startDate.getTime());
		return startDate;
	}

	private Calendar getEndDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss zzz");
		Date date = sdf.parse("Wed, 23 Feb 2016 23:59:59 UTC", new java.text.ParsePosition(0));
		Calendar endDate = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
		endDate.setTime(date);
		return endDate;
	}

	@Test
	public void test1CreateFestivity(){
		Festivity  festivity = new Festivity("Chinese New Year", getStartDate(), getEndDate(),"China");
		Mockito.when(mockFestivityRepository.saveAndFlush(festivity)).thenReturn(festivity);
		Festivity creada = festivityService.create(festivity);
		assertEquals(creada.getName(),"Chinese New Year");
	}
	
	@Test
	public void test2GetAllFestivities(){
		Festivity  festivity = new Festivity("Chinese New Year", getStartDate(), getEndDate(), "China");
		List<Festivity> list = new ArrayList<Festivity>();
		list.add(festivity);

		Mockito.when(mockFestivityRepository.findAll()).thenReturn(list);
		List<Festivity> respList = festivityService.findAll();
		assertEquals(respList.size(), 1);
		assertEquals(respList.get(0).getName(), "Chinese New Year");
	}

}
