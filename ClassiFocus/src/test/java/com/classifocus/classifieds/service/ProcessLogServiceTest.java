package com.classifocus.classifieds.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import com.classifocus.classifieds.conf.security.UserService;
import com.classifocus.classifieds.document.ProcessLog;
import com.classifocus.classifieds.mapper.ProcessLogMapper;
import com.classifocus.classifieds.model.GetProcessLogResponse;
import com.classifocus.classifieds.repository.ProcessLogRepository;

@ExtendWith(MockitoExtension.class)
public class ProcessLogServiceTest {

	@Mock
	private UserService userService;

	@Mock
	private ProcessLogMapper processLogMapper;

	@Mock
	private ProcessLogRepository processLogRepository;

	@InjectMocks
	private ProcessLogService processLogService;

	@Test
	public void testGetProcessLogs() throws Exception {

		Page<GetProcessLogResponse> getProcessLogResponsePageExpected = prepareGetProcessLogResponse();
		Page<ProcessLog> processLogs = prepareProcessLogs();

		given(processLogRepository.findAll(PageRequest.of(0, 1))).willReturn(processLogs);
		given(processLogMapper.toGetProcessLogResponse(processLogs)).willReturn(getProcessLogResponsePageExpected);

		Page<GetProcessLogResponse> getProcessLogResponseActual = processLogService.getProcessLogs(PageRequest.of(0, 1));

		assertThat(getProcessLogResponseActual.getContent().get(0).getClassName()).isEqualTo(getProcessLogResponsePageExpected.getContent().get(0).getClassName());

	}

	private Page<GetProcessLogResponse> prepareGetProcessLogResponse() {

		List<GetProcessLogResponse> processLogs = new ArrayList<GetProcessLogResponse>();
		processLogs.add(GetProcessLogResponse.builder().className("ClassifiedService").methodName("saveClassified").build());
		return new PageImpl<GetProcessLogResponse>(processLogs, PageRequest.of(0, 1), 1);

	}

	private Page<ProcessLog> prepareProcessLogs() {

		List<ProcessLog> processLogs = new ArrayList<ProcessLog>();

		processLogs.add(ProcessLog.builder().className("ClassifiedService").methodName("saveClassified").build());

		return new PageImpl<ProcessLog>(processLogs, PageRequest.of(0, 1), 1);

	}
}
