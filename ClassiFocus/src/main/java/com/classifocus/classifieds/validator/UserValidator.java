package com.classifocus.classifieds.validator;

import org.springframework.stereotype.Component;

import com.classifocus.classifieds.error.ClassiFocusException;
import com.classifocus.classifieds.model.SignUpRequest;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class UserValidator extends BaseValidator {

	public void validateSignUpRequest(SignUpRequest signUpRequest) throws ClassiFocusException {

		validateRequest(signUpRequest);
		validateEmail(signUpRequest.getEmail());

		log.info("SignUpRequest validated. SignUpRequest: " + signUpRequest.toString() + " User:" + userService.getUser());

	}

	private void validateEmail(String email) {

		if (userRepository.findByEmail(email).isPresent()) {
			throw new ClassiFocusException("User already registed.");
		}

	}

}
