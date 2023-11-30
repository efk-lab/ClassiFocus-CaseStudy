package com.classifocus.classifieds.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;

import com.classifocus.classifieds.document.ClassifiedEvent;
import com.classifocus.classifieds.model.GetClassifiedEventsResponse;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class EventMapper extends BaseMapper {
	
	public Page<GetClassifiedEventsResponse> toGetClassifiedEventsResponse(Page<ClassifiedEvent> classifiedEventsPage) {
		
		log.info("Mapping ClassifiedEvent's to GetClassifiedEventsResponse. User:" + userService.getUser());

		List<GetClassifiedEventsResponse> classifiedEventsResponseList = new ArrayList<GetClassifiedEventsResponse>();
		List<ClassifiedEvent> classifiedEvents = classifiedEventsPage.getContent();

		classifiedEvents.stream().forEach(classifiedEvent -> classifiedEventsResponseList.add(
				GetClassifiedEventsResponse.builder()
					   			  .classifiedEventId(classifiedEvent.getId())
					   			  .classifiedId(classifiedEvent.getClassifiedId())
					   			  .classifiedEventType(classifiedEvent.getClassifiedEventType())
					   			  .classifiedStatus(classifiedEvent.getClassifiedStatus())
					   			  .eventDate(classifiedEvent.getEventDate())
					   			  .createdBy(classifiedEvent.getCreatedBy())
								  .creationDate(classifiedEvent.getCreationDate())
					   			  .build()			   
				));

        return new PageImpl<>(classifiedEventsResponseList, classifiedEventsPage.getPageable(), classifiedEventsPage.getTotalElements());
	}
	
}
