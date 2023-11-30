package com.classifocus.classifieds.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.classifocus.classifieds.conf.security.UserService;
import com.classifocus.classifieds.document.User;
import com.classifocus.classifieds.mapper.UserMapper;
import com.classifocus.classifieds.model.SignUpRequest;
import com.classifocus.classifieds.model.SignUpResponse;
import com.classifocus.classifieds.repository.UserRepository;
import com.classifocus.classifieds.validator.UserValidator;

@ExtendWith(MockitoExtension.class)
public class UserRegistryServiceTest {

	@Mock
	private UserRepository userRepository;

	@Mock
	private UserService userService;

	@Mock
	private UserMapper userMapper;

	@Mock
	private UserValidator userValidator;

	@InjectMocks
	private UserRegistryService userRegistryService;

	@Test
	public void testSignUp() throws Exception {

		SignUpRequest signUpRequest = new SignUpRequest();
		SignUpResponse signUpResponseExpected = prepareSignUpResponse();
		User user = prepareUser();

		doNothing().when(userValidator).validateSignUpRequest(signUpRequest);
		given(userMapper.toUser(any(SignUpRequest.class))).willReturn(user);
		given(userRepository.save(any(User.class))).willReturn(user);
		given(userMapper.toSignUpResponse(any(User.class))).willReturn(signUpResponseExpected);

		SignUpResponse signUpResponseActual = userRegistryService.signUp(signUpRequest);

		assertThat(signUpResponseActual.getEmail()).isEqualTo(signUpResponseExpected.getEmail());

	}

	private SignUpResponse prepareSignUpResponse() {
		return SignUpResponse.builder().userId("01GW3CQ2SFHRSEGA7H9W9D9H53").email("xxx@gmail.com").build();
	}

	private User prepareUser() {
		return User.builder().userId("01GW3CQ2SFHRSEGA7H9W9D9H53").email("xxx@gmail.com").build();
	}

}