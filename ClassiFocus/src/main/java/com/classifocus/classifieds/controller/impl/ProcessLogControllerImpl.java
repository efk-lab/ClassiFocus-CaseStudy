package com.classifocus.classifieds.controller.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.classifocus.classifieds.controller.ProcessLogController;
import com.classifocus.classifieds.error.ClassiFocusException;
import com.classifocus.classifieds.model.GetProcessLogResponse;
import com.classifocus.classifieds.service.ProcessLogService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class ProcessLogControllerImpl extends BaseControllerImpl implements ProcessLogController {
	
	@Autowired
	private ProcessLogService processLogService;


	@Override
	public ResponseEntity<Page<GetProcessLogResponse>> getProcessLogs(@RequestParam int pageNumber, @RequestParam int pageSize) throws ClassiFocusException {
		
		log.info("GetProcessLog request received. RequestedBy: " + userService.getUser());
		
		List<GetProcessLogResponse> processLogs = new ArrayList<GetProcessLogResponse>();
		
		Pageable pageable = PageRequest.of(pageNumber - 1, pageSize);
		Page<GetProcessLogResponse> processLogsPage = processLogService.getProcessLogs(pageable);
		processLogs = processLogsPage.getContent();

		if (processLogs.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>(processLogsPage, HttpStatus.OK);
		}
		
	}
}
