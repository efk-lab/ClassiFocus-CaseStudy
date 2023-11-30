package com.classifocus.classifieds.validator;

import org.springframework.beans.factory.annotation.Autowired;

import com.classifocus.classifieds.conf.security.UserService;
import com.classifocus.classifieds.dao.ClassifiedDao;
import com.classifocus.classifieds.document.Classified;
import com.classifocus.classifieds.error.ClassiFocusException;
import com.classifocus.classifieds.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
abstract class BaseValidator {

	@Autowired
	protected UserService userService;
	
	@Autowired
	protected ClassifiedDao classifiedDao;
	
	@Autowired
	protected UserRepository userRepository;
	

	protected void validateRequest(Object request) {

		if (request == null) {
			log.error("Error during validation of request. Details: Request cannot be null.");
			throw new ClassiFocusException("Request cannot be null.");
		}

	}
	
	protected void validateClassifiedId(String classifiedId) {

		Classified classified = classifiedDao.findById(classifiedId);
		
		if (classified == null) {
			throw new ClassiFocusException("Classified cannot be found.");
		}
		
	}

}
