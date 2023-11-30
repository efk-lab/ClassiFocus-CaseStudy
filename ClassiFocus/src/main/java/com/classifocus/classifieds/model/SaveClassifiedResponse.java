package com.classifocus.classifieds.model;

import java.time.LocalDateTime;

import com.classifocus.classifieds.constant.ClassifiedCategory;
import com.classifocus.classifieds.constant.ClassifiedStatus;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@EqualsAndHashCode(callSuper = false)
@SuperBuilder
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor
public class SaveClassifiedResponse extends BaseResponse {

	private static final long serialVersionUID = -128646894984007869L;

	private String classifiedId;

	private String title;

	private String detail;

	private ClassifiedCategory category;

	private ClassifiedStatus status;

	private LocalDateTime endDate;

}
