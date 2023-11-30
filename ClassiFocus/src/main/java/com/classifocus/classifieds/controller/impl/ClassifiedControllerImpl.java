package com.classifocus.classifieds.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.classifocus.classifieds.controller.ClassifiedController;
import com.classifocus.classifieds.error.ClassiFocusException;
import com.classifocus.classifieds.model.GetClassifiedRequest;
import com.classifocus.classifieds.model.GetClassifiedResponse;
import com.classifocus.classifieds.model.SaveClassifiedRequest;
import com.classifocus.classifieds.model.SaveClassifiedResponse;
import com.classifocus.classifieds.model.UpdateClassifiedRequest;
import com.classifocus.classifieds.model.UpdateClassifiedResponse;
import com.classifocus.classifieds.service.ClassifiedService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class ClassifiedControllerImpl extends BaseControllerImpl implements ClassifiedController {

	@Autowired
	private ClassifiedService classifiedService;


	@Override
	public ResponseEntity<SaveClassifiedResponse> saveClassified(SaveClassifiedRequest saveClassifiedRequest) throws ClassiFocusException {
		
		log.info("SaveClassifiedRequest received: " + saveClassifiedRequest.toString() + " RequestedBy: " + userService.getUser());
		
		SaveClassifiedResponse response = classifiedService.saveClassified(saveClassifiedRequest);

		if (response != null) {
			return new ResponseEntity<>(response, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
		}
		
	}

	@Override
	public ResponseEntity<GetClassifiedResponse> getClassified(GetClassifiedRequest getClassifiedRequest) throws ClassiFocusException {
		
		log.info("GetClassifiedRequest received: " + getClassifiedRequest.toString() + " RequestedBy: " + userService.getUser());
		
		GetClassifiedResponse response = classifiedService.getClassified(getClassifiedRequest);

		if (response != null) {
			return new ResponseEntity<>(response, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
		}
		
	}

	@Override
	public ResponseEntity<UpdateClassifiedResponse> updateClassified(UpdateClassifiedRequest updateClassifiedRequest) throws ClassiFocusException {
		
		log.info("UpdateClassifiedRequest received: " + updateClassifiedRequest.toString() + " RequestedBy: " + userService.getUser());
		
		UpdateClassifiedResponse response = classifiedService.updateClassified(updateClassifiedRequest);

		if (response != null) {
			return new ResponseEntity<>(response, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
		}
		
	}

}
