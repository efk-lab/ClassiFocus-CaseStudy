package com.classifocus.classifieds.controller.impl;

import static org.mockito.ArgumentMatchers.any;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import com.classifocus.classifieds.ClassiFocusApplicationTest;
import com.classifocus.classifieds.conf.security.UserService;
import com.classifocus.classifieds.model.GetClassifiedEventsRequest;
import com.classifocus.classifieds.model.GetClassifiedEventsResponse;
import com.classifocus.classifieds.service.EventSourceService;
import com.fasterxml.jackson.databind.ObjectMapper;

@ContextConfiguration(classes = ClassiFocusApplicationTest.class)
@WebMvcTest(EventSourceControllerImpl.class)
@AutoConfigureMockMvc(addFilters = false)
@Import(EventSourceControllerImpl.class)
public class EventSourceControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@MockBean
	private EventSourceService eventSourceService;

	@MockBean
	private UserService userService;
	
	@MockBean 
	private LocalValidatorFactoryBean validator;
	

	@Test
	public void testGetClassifiedEvents() throws Exception {

		GetClassifiedEventsRequest getClassifiedEventsRequest = new GetClassifiedEventsRequest();
		Page<GetClassifiedEventsResponse> pageClassifiedEventsExpected = prepareGetClassifiedEventsResponse();
		
		given(userService.getUser()).willReturn("xxx@gmail.com");
		given(eventSourceService.getClassifiedEvents(any(GetClassifiedEventsRequest.class), any(Pageable.class))).willReturn(pageClassifiedEventsExpected);

		this.mockMvc.perform(post("/dashboard/classified/events")
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(objectMapper.writeValueAsString(getClassifiedEventsRequest))
				.param("pageNumber", "1")
		        .param("pageSize", "1"))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.content.[0].classifiedEventId").value("01GW845AQ7SRFM9CPSTR9DHTZM"));

	}

	@Test
	public void testGetClassifiedEventsReturnsNoContent() throws Exception {

		GetClassifiedEventsRequest getClassifiedEventsRequest = new GetClassifiedEventsRequest();
		Page<GetClassifiedEventsResponse> getClassifiedEventsResponseExpected = new PageImpl<GetClassifiedEventsResponse>(new ArrayList<GetClassifiedEventsResponse>(), PageRequest.of(1, 1), 0);

		given(userService.getUser()).willReturn("xxx@gmail.com");
		given(eventSourceService.getClassifiedEvents(any(GetClassifiedEventsRequest.class), any(Pageable.class))).willReturn(getClassifiedEventsResponseExpected);

		this.mockMvc.perform(post("/dashboard/classified/events")
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(objectMapper.writeValueAsString(getClassifiedEventsRequest))
				.param("pageNumber", "1")
		        .param("pageSize", "1"))
		.andExpect(status().isNoContent());

	}

	private Page<GetClassifiedEventsResponse> prepareGetClassifiedEventsResponse() {
		
		GetClassifiedEventsResponse getClassifiedEventsResponse = GetClassifiedEventsResponse.builder().classifiedEventId("01GW845AQ7SRFM9CPSTR9DHTZM").classifiedId("61GW845AQ7SRFM9CPSTR9DHTZM").build();
		
		List<GetClassifiedEventsResponse> classifiedEventsResponseList = new ArrayList<GetClassifiedEventsResponse>();
		classifiedEventsResponseList.add(getClassifiedEventsResponse);
		Pageable pageable = PageRequest.of(1, 1);
		
		return new PageImpl<GetClassifiedEventsResponse>(classifiedEventsResponseList, pageable, 0);
		
	}
}
