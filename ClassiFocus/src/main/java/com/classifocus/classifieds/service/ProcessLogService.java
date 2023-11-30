package com.classifocus.classifieds.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.classifocus.classifieds.conf.security.UserService;
import com.classifocus.classifieds.document.ProcessLog;
import com.classifocus.classifieds.mapper.ProcessLogMapper;
import com.classifocus.classifieds.model.GetProcessLogResponse;
import com.classifocus.classifieds.repository.ProcessLogRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ProcessLogService {
	
	@Autowired
	private UserService userService;

	@Autowired
	private ProcessLogMapper processLogMapper;

	@Autowired
	private ProcessLogRepository processLogRepository;
	

	public Page<GetProcessLogResponse> getProcessLogs(Pageable pageable){
		
		Page<ProcessLog> processLogs = processLogRepository.findAll(pageable);
		Page<GetProcessLogResponse> response = processLogMapper.toGetProcessLogResponse(processLogs);
		
		log.info("ProcessLogs page retrieved. Page<GetProcessLogResponse>: " + response.toString() + " User:" + userService.getUser());
		
		return response;
	}
}
