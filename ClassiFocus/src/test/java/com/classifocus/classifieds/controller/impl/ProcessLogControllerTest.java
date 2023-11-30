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

import com.classifocus.classifieds.ClassiFocusApplicationTest;
import com.classifocus.classifieds.conf.security.UserService;
import com.classifocus.classifieds.model.GetProcessLogResponse;
import com.classifocus.classifieds.service.ProcessLogService;

@ContextConfiguration(classes = ClassiFocusApplicationTest.class)
@WebMvcTest(ProcessLogControllerImpl.class)
@AutoConfigureMockMvc(addFilters = false)
@Import(ProcessLogControllerImpl.class)
public class ProcessLogControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private ProcessLogService processLogService;
	
	@MockBean
	private UserService userService;
	
	@Test
	public void testgGetProcessLogs() throws Exception {

		Page<GetProcessLogResponse> getProcessLogResponseExpected = prepareGetProcessLogResponse();
		
		given(userService.getUser()).willReturn("xxx@gmail.com");
		given(processLogService.getProcessLogs(any(Pageable.class))).willReturn(getProcessLogResponseExpected);

		this.mockMvc.perform(post("/dashboard/classifieds/processlogs")
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.param("pageNumber", "1")
		        .param("pageSize", "1"))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.content.[0].processLogId").value("01GW845AQ7SRFM9CPSTR9DHTZM"));

	}
	
	@Test
	public void testgGetProcessLogsReturnsNoContent() throws Exception {

		Page<GetProcessLogResponse> getProcessLogResponseExpected = new PageImpl<GetProcessLogResponse>(new ArrayList<GetProcessLogResponse>(), PageRequest.of(1, 1), 0);
		
		given(userService.getUser()).willReturn("xxx@gmail.com");
		given(processLogService.getProcessLogs(any(Pageable.class))).willReturn(getProcessLogResponseExpected);

		this.mockMvc.perform(post("/dashboard/classifieds/processlogs")
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.param("pageNumber", "1")
		        .param("pageSize", "1"))
		        .andExpect(status().isNoContent());

	}
	
	private PageImpl<GetProcessLogResponse> prepareGetProcessLogResponse() {
		
		GetProcessLogResponse getProcessLogResponse = GetProcessLogResponse.builder().processLogId("01GW845AQ7SRFM9CPSTR9DHTZM").className("ClassifiedService").build();
		
		List<GetProcessLogResponse> getProcessLogResponseList = new ArrayList<GetProcessLogResponse>();
		getProcessLogResponseList.add(getProcessLogResponse);
		Pageable pageable = PageRequest.of(1, 1);
		
		return new PageImpl<GetProcessLogResponse>(getProcessLogResponseList, pageable, 0);

	}
}
