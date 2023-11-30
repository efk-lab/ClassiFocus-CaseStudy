package com.classifocus.classifieds.model;

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
public class UpdateClassifiedResponse extends BaseResponse {

	private static final long serialVersionUID = -9174981963427856355L;

	private String classifiedId;

	private ClassifiedStatus status;

}
