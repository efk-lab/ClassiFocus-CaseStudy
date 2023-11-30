package com.classifocus.classifieds.controller.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.classifocus.classifieds.controller.EventSourceController;
import com.classifocus.classifieds.error.ClassiFocusException;
import com.classifocus.classifieds.model.GetClassifiedEventsRequest;
import com.classifocus.classifieds.model.GetClassifiedEventsResponse;
import com.classifocus.classifieds.service.EventSourceService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class EventSourceControllerImpl extends BaseControllerImpl implements EventSourceController {

	@Autowired
	private EventSourceService eventSourceService;
	

	@Override
	public ResponseEntity<Page<GetClassifiedEventsResponse>> getClassifiedEvents(GetClassifiedEventsRequest getClassifiedEventsRequest, int pageNumber, int pageSize) throws ClassiFocusException {
		
		log.info("GetClassifiedEventsRequest received: " + getClassifiedEventsRequest.toString() + " RequestedBy: " + userService.getUser());
		
		List<GetClassifiedEventsResponse> classifiedEvents = new ArrayList<GetClassifiedEventsResponse>();
		
		Pageable pageable = PageRequest.of(pageNumber - 1, pageSize);
		Page<GetClassifiedEventsResponse> pageOrders = eventSourceService.getClassifiedEvents(getClassifiedEventsRequest, pageable);
		classifiedEvents = pageOrders.getContent();

		if (classifiedEvents.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>(pageOrders, HttpStatus.OK);
		}
		
		
	}

}
