package com.classifocus.classifieds.aspect;

import java.util.Date;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.classifocus.classifieds.constant.ClassifiedEventType;
import com.classifocus.classifieds.constant.Role;
import com.classifocus.classifieds.document.ClassifiedEvent;
import com.classifocus.classifieds.model.SaveClassifiedResponse;
import com.classifocus.classifieds.model.UpdateClassifiedResponse;
import com.classifocus.classifieds.repository.ClassifiedEventRepository;

import lombok.extern.slf4j.Slf4j;

@Aspect
@Component
@Slf4j
public class EventSourceAspect {

	@Autowired
	private ClassifiedEventRepository classifiedEventRepository;
	

	@AfterReturning(pointcut = "execution(* com.classifocus.classifieds.service.ClassifiedService.saveClassified(..))", returning = "returnObject")
	public void logSaveClassified(JoinPoint joinPoint, Object returnObject) {

		SaveClassifiedResponse saveClassifiedResponse = (SaveClassifiedResponse) returnObject;
		ClassifiedEvent event = new ClassifiedEvent();

		event.setClassifiedId(saveClassifiedResponse.getClassifiedId());
		event.setClassifiedEventType(ClassifiedEventType.CLASSIFIED_INSERTED);
		event.setClassifiedStatus(saveClassifiedResponse.getStatus());
		event.setEventDate(new Date());
		event.setCreatedBy(Role.SYSTEM.name());
		event.setCreationDate(new Date());

		classifiedEventRepository.save(event);

		log.info("Classified Inserted event occured. Event: " + event.toString());

	}

	@AfterReturning(pointcut = "execution(* com.classifocus.classifieds.service.ClassifiedService.updateClassified(..))", returning = "returnObject")
	public void logUpdateClassified(JoinPoint joinPoint, Object returnObject) {

		UpdateClassifiedResponse updateClassifiedResponse = (UpdateClassifiedResponse) returnObject;
		ClassifiedEvent event = new ClassifiedEvent();

		event.setClassifiedId(updateClassifiedResponse.getClassifiedId());
		event.setClassifiedEventType(ClassifiedEventType.CLASSIFIED_UPDATED);
		event.setClassifiedStatus(updateClassifiedResponse.getStatus());
		event.setEventDate(new Date());
		event.setCreatedBy(Role.SYSTEM.name());
		event.setCreationDate(new Date());

		classifiedEventRepository.save(event);

		log.info("Classified Updated event occured. Event: " + event.toString());

	}

}
