package com.classifocus.classifieds.model;

import java.util.Date;

import com.classifocus.classifieds.constant.ClassifiedEventType;
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
public class GetClassifiedEventsResponse extends BaseResponse {

	private static final long serialVersionUID = -4739479769612957294L;

	private String classifiedEventId;

	private String classifiedId;

	private ClassifiedEventType classifiedEventType;

	private ClassifiedStatus classifiedStatus;

	private Date eventDate;

}