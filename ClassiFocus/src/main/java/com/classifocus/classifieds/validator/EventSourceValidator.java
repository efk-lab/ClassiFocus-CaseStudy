package com.classifocus.classifieds.validator;

import org.springframework.stereotype.Component;

import com.classifocus.classifieds.model.GetClassifiedEventsRequest;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class EventSourceValidator extends BaseValidator {

	public void validateGetClassifiedEventsRequest(GetClassifiedEventsRequest getClassifiedEventsRequest) {

		validateRequest(getClassifiedEventsRequest);
		validateClassifiedId(getClassifiedEventsRequest.getClassifiedId());

		log.info("GetClassifiedEventsRequest validated. GetClassifiedEventsRequest: " + getClassifiedEventsRequest.toString() + " User:" + userService.getUser());
	}

}
