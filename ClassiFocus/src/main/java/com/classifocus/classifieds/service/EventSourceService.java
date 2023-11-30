package com.classifocus.classifieds.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.classifocus.classifieds.conf.security.UserService;
import com.classifocus.classifieds.document.ClassifiedEvent;
import com.classifocus.classifieds.error.ClassiFocusException;
import com.classifocus.classifieds.mapper.EventMapper;
import com.classifocus.classifieds.model.GetClassifiedEventsRequest;
import com.classifocus.classifieds.model.GetClassifiedEventsResponse;
import com.classifocus.classifieds.repository.ClassifiedEventRepository;
import com.classifocus.classifieds.validator.EventSourceValidator;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class EventSourceService {

	@Autowired
	private UserService userService;

	@Autowired
	private EventSourceValidator eventSourceValidator;

	@Autowired
	private EventMapper eventMapper;

	@Autowired
	private ClassifiedEventRepository classifiedEventRepository;
	

	public Page<GetClassifiedEventsResponse> getClassifiedEvents(GetClassifiedEventsRequest getClassifiedEventsRequest, Pageable pageable) throws ClassiFocusException {

		Page<GetClassifiedEventsResponse> response = null;

		eventSourceValidator.validateGetClassifiedEventsRequest(getClassifiedEventsRequest);
		Page<ClassifiedEvent> classifiedEvents = classifiedEventRepository.findByClassifiedId(getClassifiedEventsRequest.getClassifiedId(), pageable);
		response = eventMapper.toGetClassifiedEventsResponse(classifiedEvents);

		log.info("ClassifiedEvents page retrieved. Page<GetClassifiedEventsResponse>: " + response.toString() + " User:" + userService.getUser());
		
		return response;
	}

}
