package com.classifocus.classifieds.mapper;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.classifocus.classifieds.constant.Role;
import com.classifocus.classifieds.document.User;
import com.classifocus.classifieds.model.SignUpRequest;
import com.classifocus.classifieds.model.SignUpResponse;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class UserMapper extends BaseMapper {

	@Autowired
	private PasswordEncoder passwordEncoder;
	

	public User toUser(SignUpRequest signUpRequest) {

		log.info("Mapping SignUpRequest to User. SignUpRequest:" + signUpRequest.toString() + " User:" + userService.getUser());

		return User.builder()
				.email(signUpRequest.getEmail())
				.password(passwordEncoder.encode(signUpRequest.getPassword()))
				.role(Role.USER)
				.createdBy(userService.getUser())
				.creationDate(new Date())
				.build();

	}

	public SignUpResponse toSignUpResponse(User user) {

		log.info("Mapping User to toSignUpResponse. User:" + user.toString() + " User:" + userService.getUser());

		SignUpResponse signUpResponse = SignUpResponse.builder()
				.userId(user.getUserId())
				.email(user.getEmail())
				.build();
		
		return (SignUpResponse)toResponse(signUpResponse, user);

	}
}
