package com.classifocus.classifieds.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.classifocus.classifieds.conf.security.UserService;
import com.classifocus.classifieds.dao.ClassifiedDao;
import com.classifocus.classifieds.document.Classified;
import com.classifocus.classifieds.error.ClassiFocusException;
import com.classifocus.classifieds.mapper.ClassifiedMapper;
import com.classifocus.classifieds.model.GetClassifiedRequest;
import com.classifocus.classifieds.model.GetClassifiedResponse;
import com.classifocus.classifieds.model.SaveClassifiedRequest;
import com.classifocus.classifieds.model.SaveClassifiedResponse;
import com.classifocus.classifieds.model.UpdateClassifiedRequest;
import com.classifocus.classifieds.model.UpdateClassifiedResponse;
import com.classifocus.classifieds.validator.ClassifiedValidator;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ClassifiedService {

	@Autowired
	private ClassifiedDao classifiedDao;

	@Autowired
	private ClassifiedValidator classifiedValidator;

	@Autowired
	private ClassifiedMapper classifiedMapper;

	@Autowired
	private UserService userService;

	public SaveClassifiedResponse saveClassified(SaveClassifiedRequest saveClassifiedRequest) throws ClassiFocusException {

		Classified classified = null;
		Classified savedClassified = null;
		SaveClassifiedResponse response = null;

		classifiedValidator.validateSaveClassifiedRequest(saveClassifiedRequest);
		classified = classifiedMapper.toClassified(saveClassifiedRequest);
		savedClassified = classifiedDao.save(classified);
		response = classifiedMapper.toSaveClassifiedResponse(savedClassified);

		log.info("Classified saved. SaveClassifiedResponse: " + response.toString() + " User:" + userService.getUser());

		return response;

	}

	public GetClassifiedResponse getClassified(GetClassifiedRequest getClassifiedRequest) throws ClassiFocusException {

		//Optional<Classified> classified = null;
		Classified classified = null;
		GetClassifiedResponse response = null;

		classifiedValidator.validateGetClassifiedRequest(getClassifiedRequest);
		classified = classifiedDao.findById(getClassifiedRequest.getClassifiedId());
		response = classifiedMapper.toGetClassifiedResponse(classified);

		log.info("Classified retrieved. GetClassifiedResponse: " + response.toString() + " User:" + userService.getUser());

		return response;

	}

	public UpdateClassifiedResponse updateClassified(UpdateClassifiedRequest updateClassifiedRequest) throws ClassiFocusException {

		Classified classified = null;
		Classified updatedClassified = null;
		UpdateClassifiedResponse response = null;

		classifiedValidator.validateUpdateClassifiedRequest(updateClassifiedRequest);
		classified = classifiedMapper.toClassified(updateClassifiedRequest);
		updatedClassified = classifiedDao.save(classified);
		response = classifiedMapper.toUpdateClassifiedResponse(updatedClassified);

		log.info("Classified updated. UpdateClassifiedResponse: " + response.toString() + " User:" + userService.getUser());

		return response;

	}
}
