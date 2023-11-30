package com.classifocus.classifieds.error;

import static org.springframework.http.HttpStatus.UNAUTHORIZED;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class ClassiFocusAuthenticationEntryPoint implements AuthenticationEntryPoint {

	private final HttpMessageConverter<String> messageConverter;

	private final ObjectMapper mapper;

	public ClassiFocusAuthenticationEntryPoint(ObjectMapper mapper) {
		this.messageConverter = new StringHttpMessageConverter();
		this.mapper = mapper;
	}

	@Override
	public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException {
		ClassiFocusError classiFocusError = new ClassiFocusError(UNAUTHORIZED);
		classiFocusError.setMessage(e.getMessage());
		classiFocusError.setDebugMessage(e.getMessage());

		ServerHttpResponse outputMessage = new ServletServerHttpResponse(httpServletResponse);
		outputMessage.setStatusCode(HttpStatus.UNAUTHORIZED);

		messageConverter.write(mapper.writeValueAsString(classiFocusError), MediaType.APPLICATION_JSON, outputMessage);
	}
}