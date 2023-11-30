package com.classifocus.classifieds.controller.impl;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.validation.ValidationAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import com.classifocus.classifieds.ClassiFocusApplicationTest;
import com.classifocus.classifieds.conf.security.UserService;
import com.classifocus.classifieds.constant.ClassifiedStatus;
import com.classifocus.classifieds.model.GetClassifiedRequest;
import com.classifocus.classifieds.model.GetClassifiedResponse;
import com.classifocus.classifieds.model.SaveClassifiedRequest;
import com.classifocus.classifieds.model.SaveClassifiedResponse;
import com.classifocus.classifieds.model.UpdateClassifiedRequest;
import com.classifocus.classifieds.model.UpdateClassifiedResponse;
import com.classifocus.classifieds.service.ClassifiedService;
import com.fasterxml.jackson.databind.ObjectMapper;

@ContextConfiguration(classes = {ClassiFocusApplicationTest.class})
@WebMvcTest(controllers = ClassifiedControllerImpl.class, excludeAutoConfiguration = ValidationAutoConfiguration.class)
@AutoConfigureMockMvc(addFilters = false)
@Import(ClassifiedControllerImpl.class)
public class ClassifiedControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private ClassifiedService classifiedService;
	
	@MockBean
	private UserService userService;

	@Autowired
	private ObjectMapper objectMapper;
	
	@MockBean 
	private LocalValidatorFactoryBean validator;

	@Test
	public void testSaveClassified() throws Exception {
		

		SaveClassifiedRequest saveClassifiedRequest = new SaveClassifiedRequest();
		SaveClassifiedResponse saveClassifiedResponse = prepareSaveClassifiedResponse();

		given(userService.getUser()).willReturn("xxx@gmail.com");
		given(classifiedService.saveClassified(any(SaveClassifiedRequest.class))).willReturn(saveClassifiedResponse);

		this.mockMvc.perform(post("/dashboard/classified/record")
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(objectMapper.writeValueAsString(saveClassifiedRequest)))
		.andExpect(status().isOk())
		.andExpect(jsonPath("title").value("İlan"));

	}
	
	@Test
	public void saveClassifiedReturnsNoContent() throws Exception {

		SaveClassifiedRequest saveClassifiedRequest = new SaveClassifiedRequest();
		SaveClassifiedResponse saveClassifiedResponse = null;
		
		given(userService.getUser()).willReturn("xxx@gmail.com");
		given(classifiedService.saveClassified(any(SaveClassifiedRequest.class))).willReturn(saveClassifiedResponse);

		this.mockMvc.perform(post("/dashboard/classified/record")
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(objectMapper.writeValueAsString(saveClassifiedRequest)))
		        .andExpect(status().isNoContent());

	}
	
	@Test
	public void testGetClassified() throws Exception {

		GetClassifiedRequest getClassifiedRequest = new GetClassifiedRequest();
		GetClassifiedResponse getClassifiedResponse = prepareGetClassifiedResponse();

		given(userService.getUser()).willReturn("xxx@gmail.com");
		given(classifiedService.getClassified(any(GetClassifiedRequest.class))).willReturn(getClassifiedResponse);

		this.mockMvc.perform(post("/dashboard/classified/details")
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(objectMapper.writeValueAsString(getClassifiedRequest)))
		.andExpect(status().isOk())
		.andExpect(jsonPath("title").value("İlan"));

	}
	
	@Test
	public void testGetClassifiedReturnsNoContent() throws Exception {

		GetClassifiedRequest getClassifiedRequest = new GetClassifiedRequest();
		GetClassifiedResponse getClassifiedResponse = null;

		given(userService.getUser()).willReturn("xxx@gmail.com");
		given(classifiedService.getClassified(any(GetClassifiedRequest.class))).willReturn(getClassifiedResponse);

		this.mockMvc.perform(post("/dashboard/classified/details")
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(objectMapper.writeValueAsString(getClassifiedRequest)))
		        .andExpect(status().isNoContent());

	}
	
	@Test
	public void testUpdateClassified() throws Exception {

		UpdateClassifiedRequest updateClassifiedRequest = new UpdateClassifiedRequest();
		UpdateClassifiedResponse updateClassifiedResponse = prepareUpdateClassifiedResponse();

		given(userService.getUser()).willReturn("xxx@gmail.com");
		given(classifiedService.updateClassified(any(UpdateClassifiedRequest.class))).willReturn(updateClassifiedResponse);

		this.mockMvc.perform(post("/dashboard/classified/update")
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(objectMapper.writeValueAsString(updateClassifiedRequest)))
		.andExpect(status().isOk())
		.andExpect(jsonPath("classifiedId").value("01GW845AQ7SRFM9CPSTR9DHTZM"));

	}
	
	@Test
	public void testUpdateClassifiedReturnsNoContent() throws Exception {

		GetClassifiedRequest getClassifiedRequest = new GetClassifiedRequest();
		GetClassifiedResponse getClassifiedResponse = null;

		given(userService.getUser()).willReturn("xxx@gmail.com");
		given(classifiedService.getClassified(any(GetClassifiedRequest.class))).willReturn(getClassifiedResponse);

		this.mockMvc.perform(post("/dashboard/classified/update")
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(objectMapper.writeValueAsString(getClassifiedRequest)))
		        .andExpect(status().isNoContent());

	}
	

	private SaveClassifiedResponse prepareSaveClassifiedResponse() {
		return SaveClassifiedResponse.builder().title("İlan").detail("Ev İlanı").build();
	}
	
	private GetClassifiedResponse prepareGetClassifiedResponse() {
		return GetClassifiedResponse.builder().title("İlan").detail("Ev İlanı").build();
	}
	
	private UpdateClassifiedResponse prepareUpdateClassifiedResponse() {
		return UpdateClassifiedResponse.builder().classifiedId("01GW845AQ7SRFM9CPSTR9DHTZM").status(ClassifiedStatus.ACTIVE).build();
	}
	
}
