package com.classifocus.classifieds.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.classifocus.classifieds.controller.UserRegistryController;
import com.classifocus.classifieds.error.ClassiFocusException;
import com.classifocus.classifieds.model.SignUpRequest;
import com.classifocus.classifieds.model.SignUpResponse;
import com.classifocus.classifieds.service.UserRegistryService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class UserRegistryControllerImpl extends BaseControllerImpl implements UserRegistryController {

	@Autowired
	private UserRegistryService userRegistryService;


	@Override
	public ResponseEntity<SignUpResponse> signUp(SignUpRequest signUpRequest) throws ClassiFocusException {
		
		log.info("SignUpRequest received: " + signUpRequest.toString() + " RequestedBy:" + userService.getUser());

		SignUpResponse response = userRegistryService.signUp(signUpRequest);

		if (response != null) {
			return new ResponseEntity<>(response, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
		}

	}

}
