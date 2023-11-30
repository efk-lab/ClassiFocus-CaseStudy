package com.classifocus.classifieds.controller;


import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.classifocus.classifieds.model.GetProcessLogResponse;

@RequestMapping("/dashboard/classifieds")
public interface ProcessLogController {
	@RequestMapping(
			value = "/processlogs", 
			method = RequestMethod.POST, 
			produces = { MediaType.APPLICATION_JSON_VALUE })
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<Page<GetProcessLogResponse>> getProcessLogs(@RequestParam int pageNumber, @RequestParam int pageSize);
}
