package com.classifocus.classifieds.document;

import java.util.Date;

import org.springframework.data.annotation.Id;

import com.classifocus.classifieds.constant.ClassifiedEventType;
import com.classifocus.classifieds.constant.ClassifiedStatus;
import com.redis.om.spring.annotations.Document;
import com.redis.om.spring.annotations.Indexed;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Document
@Data
@EqualsAndHashCode(callSuper = false)
@SuperBuilder
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor
public class ClassifiedEvent extends BaseDocument {

	private static final long serialVersionUID = -5244964381052000994L;

	@Id
	private String id;

	@Indexed
	private String classifiedId;

	@Indexed
	private ClassifiedEventType classifiedEventType;

	@Indexed
	private ClassifiedStatus classifiedStatus;

	private Date eventDate;

}
