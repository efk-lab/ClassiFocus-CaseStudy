package com.classifocus.classifieds.validator;

import java.util.StringTokenizer;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.classifocus.classifieds.constant.ClassifiedCategory;
import com.classifocus.classifieds.constant.ClassifiedStatus;
import com.classifocus.classifieds.document.Classified;
import com.classifocus.classifieds.error.ClassiFocusException;
import com.classifocus.classifieds.model.GetClassifiedRequest;
import com.classifocus.classifieds.model.SaveClassifiedRequest;
import com.classifocus.classifieds.model.UpdateClassifiedRequest;
import com.classifocus.classifieds.service.BadwordBloomFilterService;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ClassifiedValidator extends BaseValidator {
	
	private static String LETTER_DIGIT_PATTERN = "[a-z0-9]";

	@Autowired
    private BadwordBloomFilterService bloomFilterService;


	public void validateSaveClassifiedRequest(SaveClassifiedRequest saveClassifiedRequest) {

		validateRequest(saveClassifiedRequest);
		validateTitle(saveClassifiedRequest.getTitle());
		validateCategory(saveClassifiedRequest.getCategory());

		log.info("SaveClassifiedRequest validated. SaveClassifiedRequest: " + saveClassifiedRequest.toString() + " User:" + userService.getUser());

	}

	private void validateTitle(String title) {

		Pattern pattern = Pattern.compile(LETTER_DIGIT_PATTERN, Pattern.CASE_INSENSITIVE);
		if (!(pattern.matcher(title.substring(0, 1)).matches())) {
			throw new ClassiFocusException("Title must start with a letter or a number.");
		}
		
		StringTokenizer tokenizer = new StringTokenizer(title);
	    while (tokenizer.hasMoreTokens()) {    	
	    	if(bloomFilterService.getUserNameBloomFilter().contains(tokenizer.nextToken())) {
				throw new ClassiFocusException("Badword detected in title.");
			}
        }

	}

	private void validateCategory(ClassifiedCategory category) {

		if (!ClassifiedCategory.isMember(category)) {
			throw new ClassiFocusException("Invalid category.");
		}

	}

	public void validateGetClassifiedRequest(GetClassifiedRequest getClassifiedRequest) {

		validateRequest(getClassifiedRequest);
		validateClassifiedId(getClassifiedRequest.getClassifiedId());
		
		log.info("GetClassifiedRequest validated. GetClassifiedRequest: " + getClassifiedRequest.toString() + " User:" + userService.getUser());

	}

	public void validateUpdateClassifiedRequest(UpdateClassifiedRequest updateClassifiedRequest) {

		validateRequest(updateClassifiedRequest);
		validateClassifiedId(updateClassifiedRequest.getClassifiedId());
		validateStatus(updateClassifiedRequest);
		
		log.info("UpdateClassifiedRequest validated. UpdateClassifiedRequest: " + updateClassifiedRequest.toString() + " User:" + userService.getUser());

	}

	private void validateStatus(UpdateClassifiedRequest updateClassifiedRequest) {

		Classified dublicateClassified = classifiedDao.findByIdAndStatus(updateClassifiedRequest.getClassifiedId(), ClassifiedStatus.DUPLICATE.getValue());

		if (dublicateClassified != null) {
			throw new ClassiFocusException("Duplicate classified cannot be updated.");
		}

		Classified classifiedPendingApproval = classifiedDao.findByIdAndStatus(updateClassifiedRequest.getClassifiedId(), ClassifiedStatus.PENDING_APPROVAL.getValue());

		
		if (classifiedPendingApproval != null
				&& (updateClassifiedRequest.getClassifiedStatus().getValue() != ClassifiedStatus.ACTIVE.getValue() && updateClassifiedRequest.getClassifiedStatus().getValue() != ClassifiedStatus.PASSIVE.getValue())) {
			throw new ClassiFocusException("Classified in PENDING_APPROVAL state cannot be updated to any state other then ACTIVE or PASSIVE.");
		}

		Classified classifiedActive = classifiedDao.findByIdAndStatus(updateClassifiedRequest.getClassifiedId(), ClassifiedStatus.ACTIVE.getValue());

		
		if (classifiedActive != null && updateClassifiedRequest.getClassifiedStatus().getValue() != ClassifiedStatus.PASSIVE.getValue()) {
			throw new ClassiFocusException("Classified in ACTIVE state cannot be updated to any state other then PASSIVE.");
		}

	}

}
