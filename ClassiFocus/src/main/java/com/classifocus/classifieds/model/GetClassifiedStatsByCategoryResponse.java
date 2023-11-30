package com.classifocus.classifieds.model;

import com.classifocus.classifieds.constant.ClassifiedCategory;

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
public class GetClassifiedStatsByCategoryResponse {
	
	private ClassifiedCategory classifiedCategory;

	private Long classifiedCount;

	private Long statusCount;

}
