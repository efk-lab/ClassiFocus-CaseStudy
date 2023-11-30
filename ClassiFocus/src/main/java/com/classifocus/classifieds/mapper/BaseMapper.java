package com.classifocus.classifieds.mapper;

import org.springframework.beans.factory.annotation.Autowired;

import com.classifocus.classifieds.conf.security.UserService;
import com.classifocus.classifieds.document.BaseDocument;
import com.classifocus.classifieds.model.BaseResponse;

public abstract class BaseMapper {
	
	@Autowired
	protected UserService userService;
	

	protected BaseResponse toResponse(BaseResponse baseResponse, BaseDocument baseDao) {

		baseResponse.setCreatedBy(baseDao.getCreatedBy());
		baseResponse.setCreationDate(baseDao.getCreationDate());
		baseResponse.setModifiedBy(baseDao.getModifiedBy());
		baseResponse.setModificationDate(baseDao.getModificationDate());

		return baseResponse;
	}

}
