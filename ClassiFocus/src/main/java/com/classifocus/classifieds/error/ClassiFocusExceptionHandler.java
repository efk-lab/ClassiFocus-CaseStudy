package com.classifocus.classifieds.error;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import lombok.extern.slf4j.Slf4j;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
@Slf4j
public class ClassiFocusExceptionHandler extends ResponseEntityExceptionHandler {

	
	@Override
	protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

		String error = ex.getParameterName() + " parameter is missing";

		return buildResponseEntity(new ClassiFocusError(BAD_REQUEST, error, ex));

	}

	@Override
	protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

		StringBuilder builder = new StringBuilder();

		builder.append(ex.getContentType());
		builder.append(" media type is not supported. Supported media types are ");
		ex.getSupportedMediaTypes().forEach(t -> builder.append(t).append(", "));

		return buildResponseEntity(new ClassiFocusError(HttpStatus.UNSUPPORTED_MEDIA_TYPE, builder.substring(0, builder.length() - 2), ex));

	}

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

		ServletWebRequest servletWebRequest = (ServletWebRequest) request;

		log.info("{} to {}", servletWebRequest.getHttpMethod(), servletWebRequest.getRequest().getServletPath());
		String error = "Malformed JSON request";

		return buildResponseEntity(new ClassiFocusError(HttpStatus.BAD_REQUEST, error, ex));

	}

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotWritable(HttpMessageNotWritableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

		String error = "Error writing JSON output";

		return buildResponseEntity(new ClassiFocusError(HttpStatus.INTERNAL_SERVER_ERROR, error, ex));

	}

	@Override
	protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		ClassiFocusError apiError = new ClassiFocusError(BAD_REQUEST);

		apiError.setMessage(String.format("Could not find the %s method for URL %s", ex.getHttpMethod(), ex.getRequestURL()));
		apiError.setDebugMessage(ex.getMessage());

		return buildResponseEntity(apiError);

	}

	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	protected ResponseEntity<Object> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex, WebRequest request) {
		ClassiFocusError apiError = new ClassiFocusError(BAD_REQUEST);

		apiError.setMessage(
				String.format("The parameter '%s' of value '%s' could not be converted to type '%s'", ex.getName(), ex.getValue(), ex.getRequiredType().getSimpleName()));
		apiError.setDebugMessage(ex.getMessage());

		return buildResponseEntity(apiError);

	}

	@ExceptionHandler(AccessDeniedException.class)
	protected ResponseEntity<Object> handleMethodArgumentTypeMismatch(AccessDeniedException ex, WebRequest request) {

		ClassiFocusError apiError = buildClassiFocusError(ex, HttpStatus.UNAUTHORIZED);
		
		return buildResponseEntity(apiError);

	}

	@ExceptionHandler(ClassiFocusException.class)
	protected ResponseEntity<Object> handleClassiFocusException(ClassiFocusException ex, WebRequest request) {

		ClassiFocusError apiError = buildClassiFocusError(ex, HttpStatus.UNPROCESSABLE_ENTITY);

		return buildResponseEntity(apiError);

	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

		ClassiFocusError apiError = buildClassiFocusError(ex, HttpStatus.BAD_REQUEST);
		
		return buildResponseEntity(apiError);

	}
	
	private ClassiFocusError buildClassiFocusError(Exception ex, HttpStatus httpStatus) {
		
		ClassiFocusError apiError = new ClassiFocusError(httpStatus);

		apiError.setMessage(ex.getMessage());
		apiError.setDebugMessage(ex.getMessage());
		
		return apiError;
	}

	private ResponseEntity<Object> buildResponseEntity(ClassiFocusError apiError) {

		return new ResponseEntity<Object>(apiError, (HttpStatus) apiError.getStatus());

	}

}