package com.classifocus.classifieds.controller.impl;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import com.classifocus.classifieds.ClassiFocusApplicationTest;
import com.classifocus.classifieds.conf.security.UserService;
import com.classifocus.classifieds.model.GetClassifiedStatsByCategoryResponse;
import com.classifocus.classifieds.model.GetClassifiedStatsByStatusResponse;
import com.classifocus.classifieds.service.StatisticsService;


@ContextConfiguration(classes = ClassiFocusApplicationTest.class)
@WebMvcTest(StatisticsControllerImpl.class)
@AutoConfigureMockMvc(addFilters = false)
@Import(StatisticsControllerImpl.class)
public class StatisticsControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private StatisticsService statisticsService;

	@MockBean
	private UserService userService;
	

	@Test
	public void testGetClassifiedStatsByStatus() throws Exception {

		List<GetClassifiedStatsByStatusResponse> getClassifiedStatsByStatusResponseExpected = prepareGetClassifiedStatsByStatusResponse();

		given(userService.getUser()).willReturn("xxx@gmail.com");
		given(statisticsService.getClassifiedStatsByStatus()).willReturn(getClassifiedStatsByStatusResponseExpected);

		this.mockMvc.perform(post("/dashboard/classifieds/statistics/by-status"))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.[0].categoryCount").value("1"));
			
	}
	
	@Test
	public void testgGetClassifiedStatsByStatusReturnsNoContent() throws Exception {

		List<GetClassifiedStatsByStatusResponse> getClassifiedStatsByStatusResponseExpected = null;

		given(userService.getUser()).willReturn("xxx@gmail.com");
		given(statisticsService.getClassifiedStatsByStatus()).willReturn(getClassifiedStatsByStatusResponseExpected);

		this.mockMvc.perform(post("/dashboard/classifieds/statistics/by-status"))
		.andExpect(status().isNoContent());
			
	}
	
	
	@Test
	public void testGetClassifiedStatsByCategory() throws Exception {

		List<GetClassifiedStatsByCategoryResponse> getClassifiedStatsByStatusResponseExpected = prepareGetClassifiedStatsByCategoryResponse();

		given(userService.getUser()).willReturn("xxx@gmail.com");
		given(statisticsService.getClassifiedStatsByCategory()).willReturn(getClassifiedStatsByStatusResponseExpected);
		
		this.mockMvc.perform(post("/dashboard/classifieds/statistics/by-category"))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.[0].classifiedCount").value("1"));
			
	}
	
	@Test
	public void testGetClassifiedStatsByCategoryReturnsNoContent() throws Exception {

		List<GetClassifiedStatsByCategoryResponse> getClassifiedStatsByCategoryResponse = null;

		given(userService.getUser()).willReturn("xxx@gmail.com");
		given(statisticsService.getClassifiedStatsByCategory()).willReturn(getClassifiedStatsByCategoryResponse);

		this.mockMvc.perform(post("/dashboard/classifieds/statistics/by-category"))
		.andExpect(status().isNoContent());
			
	}
	
	
	
	private List<GetClassifiedStatsByStatusResponse> prepareGetClassifiedStatsByStatusResponse() {
		
		List<GetClassifiedStatsByStatusResponse> classifiedStatsByStatusList = new ArrayList<GetClassifiedStatsByStatusResponse>();
		classifiedStatsByStatusList.add(GetClassifiedStatsByStatusResponse.builder().categoryCount(1L).classifiedCount(1L).build());
		
		return classifiedStatsByStatusList;
		
	}
	
	private List<GetClassifiedStatsByCategoryResponse> prepareGetClassifiedStatsByCategoryResponse() {
		
		List<GetClassifiedStatsByCategoryResponse> classifiedStatsByCategoryList = new ArrayList<GetClassifiedStatsByCategoryResponse>();
		classifiedStatsByCategoryList.add(GetClassifiedStatsByCategoryResponse.builder().classifiedCount(1L).build());
		
		return classifiedStatsByCategoryList;

	}

}
