package com.classifocus.classifieds.mapper;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.classifocus.classifieds.constant.ClassifiedCategory;
import com.classifocus.classifieds.constant.ClassifiedStatus;
import com.classifocus.classifieds.dao.ClassifiedDao;
import com.classifocus.classifieds.document.Classified;
import com.classifocus.classifieds.model.GetClassifiedResponse;
import com.classifocus.classifieds.model.SaveClassifiedRequest;
import com.classifocus.classifieds.model.SaveClassifiedResponse;
import com.classifocus.classifieds.model.UpdateClassifiedRequest;
import com.classifocus.classifieds.model.UpdateClassifiedResponse;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ClassifiedMapper extends BaseMapper {

	@Autowired
	private ClassifiedDao classifiedDao;


	public Classified toClassified(SaveClassifiedRequest saveClassifiedRequest) {

		log.info("Mapping SaveClassifiedRequest to Classified. SaveBookRequest:" + saveClassifiedRequest.toString() + " User:" + userService.getUser());

		Classified classified = Classified.builder()
				.title(saveClassifiedRequest.getTitle())
				.detail(saveClassifiedRequest.getDetail())
				.category(saveClassifiedRequest.getCategory().getValue())
				.createdBy(userService.getUser())
				.creationDate(new Date())
				.build();
		
		ClassifiedCategory classifiedCategory = saveClassifiedRequest.getCategory();
		Classified duplicateClassified = classifiedDao.findByTitleAndDetailAndCategory(saveClassifiedRequest.getTitle(), saveClassifiedRequest.getDetail(),
				classifiedCategory.getValue());
		if (duplicateClassified != null) {
			classified.setStatus(ClassifiedStatus.DUPLICATE.getValue());
		} else {
			if (ClassifiedCategory.OTHERS == classifiedCategory){
				classified.setStatus(ClassifiedStatus.ACTIVE.getValue());
			} else {
				classified.setStatus(ClassifiedStatus.PENDING_APPROVAL.getValue());
			}
		}
		
		if (ClassifiedCategory.ESTATE == classifiedCategory) {
			classified.setEndDate(LocalDateTime.now().plus(4, ChronoUnit.WEEKS));
		} else {
			classified.setEndDate(LocalDateTime.now().plus(3, ChronoUnit.WEEKS));
		}

		return classified;

	}

	public SaveClassifiedResponse toSaveClassifiedResponse(Classified classified) {

		log.info("Mapping Classified to SaveClassifiedResponse. Classified:" + classified.toString() + " User:" + userService.getUser());

		SaveClassifiedResponse response = SaveClassifiedResponse.builder()
				.classifiedId(classified.getId())
				.title(classified.getTitle())
				.detail(classified.getDetail())
				.category(ClassifiedCategory.nameOf(classified.getCategory()))
				.status(ClassifiedStatus.nameOf(classified.getStatus()))
				.endDate(classified.getEndDate())
				.createdBy(classified.getCreatedBy())
				.creationDate(classified.getCreationDate())
				.build();

		return (SaveClassifiedResponse) toResponse(response, classified);

	}

	public GetClassifiedResponse toGetClassifiedResponse(Classified classified) {

		log.info("Mapping Classified to GetClassifiedResponse. Classified:" + classified.toString() + " User:" + userService.getUser());
		
		GetClassifiedResponse response = GetClassifiedResponse.builder()
				.classifiedId(classified.getId())
				.title(classified.getTitle())
				.detail(classified.getDetail())
				.category(ClassifiedCategory.nameOf(classified.getCategory()))
				.status(ClassifiedStatus.nameOf(classified.getStatus()))
				.endDate(classified.getEndDate())
				.createdBy(classified.getCreatedBy())
				.creationDate(classified.getCreationDate())
				.modifiedBy(classified.getModifiedBy())
				.modificationDate(classified.getModificationDate())
				.build();
		
		return (GetClassifiedResponse) toResponse(response, classified);

	}

	public Classified toClassified(UpdateClassifiedRequest updateClassifiedRequest) {

		log.info("Mapping UpdateClassifiedRequest to Classified. UpdateClassifiedRequest:" + updateClassifiedRequest.toString() + " User:" + userService.getUser());

		Classified classified = classifiedDao.findById(updateClassifiedRequest.getClassifiedId());
		classified.setStatus(updateClassifiedRequest.getClassifiedStatus().getValue());
		classified.setModifiedBy(userService.getUser());
		classified.setModificationDate(new Date());

		return classified;

	}

	public UpdateClassifiedResponse toUpdateClassifiedResponse(Classified classified) {

		log.info("Mapping Classified to UpdateClassifiedResponse. Classified:" + classified.toString() + " User:" + userService.getUser());
		
		UpdateClassifiedResponse response = UpdateClassifiedResponse.builder()
				.classifiedId(classified.getId())
				.status(ClassifiedStatus.nameOf(classified.getStatus()))
				.build();

		return (UpdateClassifiedResponse) toResponse(response, classified);

	}

}
