package com.classifocus.classifieds.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.classifocus.classifieds.conf.security.UserService;
import com.classifocus.classifieds.document.User;
import com.classifocus.classifieds.error.ClassiFocusException;
import com.classifocus.classifieds.mapper.UserMapper;
import com.classifocus.classifieds.model.SignUpRequest;
import com.classifocus.classifieds.model.SignUpResponse;
import com.classifocus.classifieds.repository.UserRepository;
import com.classifocus.classifieds.validator.UserValidator;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserRegistryService {

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserValidator userValidator;
	
	@Autowired
	private UserService userService;
	

	public SignUpResponse signUp(SignUpRequest signUpRequest) throws ClassiFocusException {

		userValidator.validateSignUpRequest(signUpRequest);
		User user = userMapper.toUser(signUpRequest);
		User savedUser = userRepository.save(user);
		SignUpResponse response = userMapper.toSignUpResponse(savedUser);
		
		log.info("User saved. SignUpResponse: " + response.toString() + " User:" + userService.getUser());


		return response;
	}
}
