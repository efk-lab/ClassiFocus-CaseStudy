package com.classifocus.classifieds.controller;

import javax.validation.Valid;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.classifocus.classifieds.error.ClassiFocusException;
import com.classifocus.classifieds.model.GetClassifiedRequest;
import com.classifocus.classifieds.model.GetClassifiedResponse;
import com.classifocus.classifieds.model.SaveClassifiedRequest;
import com.classifocus.classifieds.model.SaveClassifiedResponse;
import com.classifocus.classifieds.model.UpdateClassifiedRequest;
import com.classifocus.classifieds.model.UpdateClassifiedResponse;

@RequestMapping("/dashboard/classified")
public interface ClassifiedController {

	@RequestMapping(
			value = "/record", 
			method = RequestMethod.POST, 
			consumes = { MediaType.APPLICATION_JSON_VALUE }, 
			produces = { MediaType.APPLICATION_JSON_VALUE })
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
	public ResponseEntity<SaveClassifiedResponse> saveClassified(@Valid @RequestBody SaveClassifiedRequest saveClassifiedRequest) throws ClassiFocusException;
	
	@RequestMapping(
			value = "/details", 
			method = RequestMethod.POST, 
			consumes = { MediaType.APPLICATION_JSON_VALUE }, 
			produces = { MediaType.APPLICATION_JSON_VALUE })
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
	public ResponseEntity<GetClassifiedResponse> getClassified(@Valid @RequestBody GetClassifiedRequest getClassifiedRequest) throws ClassiFocusException;
	
	@RequestMapping(
			value = "/update", 
			method = RequestMethod.POST, 
			consumes = { MediaType.APPLICATION_JSON_VALUE }, 
			produces = { MediaType.APPLICATION_JSON_VALUE })
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
	public ResponseEntity<UpdateClassifiedResponse> updateClassified(@Valid @RequestBody UpdateClassifiedRequest updateClassifiedRequest) throws ClassiFocusException;
}
