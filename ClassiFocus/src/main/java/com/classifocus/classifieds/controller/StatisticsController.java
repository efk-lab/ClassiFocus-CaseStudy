package com.classifocus.classifieds.controller;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.classifocus.classifieds.model.GetClassifiedStatsByCategoryResponse;
import com.classifocus.classifieds.model.GetClassifiedStatsByStatusResponse;

@RequestMapping("/dashboard/classifieds/statistics")
public interface StatisticsController {
	
	@RequestMapping(
			value = "/by-status", 
			method = RequestMethod.POST, 
			produces = { MediaType.APPLICATION_JSON_VALUE })
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<List<GetClassifiedStatsByStatusResponse>> getClassifiedStatsByStatus();
	
	@RequestMapping(
			value = "/by-category", 
			method = RequestMethod.POST, 
			produces = { MediaType.APPLICATION_JSON_VALUE })
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<List<GetClassifiedStatsByCategoryResponse>> getClassifiedStatsByCategory();
}
