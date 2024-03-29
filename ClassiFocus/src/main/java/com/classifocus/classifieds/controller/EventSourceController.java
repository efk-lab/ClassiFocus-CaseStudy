package com.classifocus.classifieds.controller;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.classifocus.classifieds.error.ClassiFocusException;
import com.classifocus.classifieds.model.GetClassifiedEventsRequest;
import com.classifocus.classifieds.model.GetClassifiedEventsResponse;

@RequestMapping("/dashboard/classified")
public interface EventSourceController {

	@RequestMapping(
			value = "/events", 
			method = RequestMethod.POST, 
			consumes = { MediaType.APPLICATION_JSON_VALUE }, 
			produces = { MediaType.APPLICATION_JSON_VALUE })
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<Page<GetClassifiedEventsResponse>> getClassifiedEvents(@Valid @RequestBody GetClassifiedEventsRequest getClassifiedEventsRequest, @RequestParam int pageNumber, @RequestParam int pageSize) throws ClassiFocusException;
	
}
