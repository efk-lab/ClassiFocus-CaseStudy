package com.classifocus.classifieds.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.classifocus.classifieds.conf.security.UserService;
import com.classifocus.classifieds.document.ClassifiedEvent;
import com.classifocus.classifieds.mapper.EventMapper;
import com.classifocus.classifieds.model.GetClassifiedEventsRequest;
import com.classifocus.classifieds.model.GetClassifiedEventsResponse;
import com.classifocus.classifieds.repository.ClassifiedEventRepository;
import com.classifocus.classifieds.validator.EventSourceValidator;

@ExtendWith(MockitoExtension.class)
public class EventSourceServiceTest {

	@Mock
	private UserService userService;

	@Mock
	private EventSourceValidator eventSourceValidator;

	@Mock
	private EventMapper eventMapper;

	@Mock
	private ClassifiedEventRepository classifiedEventRepository;

	@InjectMocks
	private EventSourceService eventSourceService;

	@Test
	public void testGetClassifiedEvents() throws Exception {

		GetClassifiedEventsRequest getClassifiedEventsRequest = prepareGetClassifiedEventsRequest();
		Page<GetClassifiedEventsResponse> getClassifiedEventsResponseExpected = prepareGetClassifiedEventsResponse();
		Page<ClassifiedEvent> classifiedEventsPage = prepareClassifiedEvents();

		doNothing().when(eventSourceValidator).validateGetClassifiedEventsRequest(getClassifiedEventsRequest);
		given(classifiedEventRepository.findByClassifiedId(any(String.class), any(Pageable.class))).willReturn(classifiedEventsPage);
		given(eventMapper.toGetClassifiedEventsResponse(classifiedEventsPage)).willReturn(getClassifiedEventsResponseExpected);

		Page<GetClassifiedEventsResponse> getClassifiedEventsResponseActual = eventSourceService.getClassifiedEvents(getClassifiedEventsRequest, PageRequest.of(1, 1));

		assertThat(getClassifiedEventsResponseActual.getContent().get(0).getClassifiedId()).isEqualTo(getClassifiedEventsResponseExpected.getContent().get(0).getClassifiedId());

	}

	private GetClassifiedEventsRequest prepareGetClassifiedEventsRequest() {

		GetClassifiedEventsRequest getClassifiedEventsRequest = new GetClassifiedEventsRequest();

		getClassifiedEventsRequest.setClassifiedId("01GW845AQ7SRFM9CPSTR9DHTZM");

		return getClassifiedEventsRequest;
	}

	private Page<GetClassifiedEventsResponse> prepareGetClassifiedEventsResponse() {

		List<GetClassifiedEventsResponse> classifiedEvents = new ArrayList<GetClassifiedEventsResponse>();
		classifiedEvents.add(GetClassifiedEventsResponse.builder().classifiedEventId("01GW845AQ7SRFM9CPSTR9DHTZM").build());

		return new PageImpl<GetClassifiedEventsResponse>(classifiedEvents, PageRequest.of(1, 1), 0);

	}

	private Page<ClassifiedEvent> prepareClassifiedEvents() {

		List<ClassifiedEvent> classifiedEvents = new ArrayList<ClassifiedEvent>();
		classifiedEvents.add(ClassifiedEvent.builder().id("01GW845AQ7SRFM9CPSTR9DHTZM").build());

		return new PageImpl<ClassifiedEvent>(classifiedEvents, PageRequest.of(1, 1), 0);

	}
}
