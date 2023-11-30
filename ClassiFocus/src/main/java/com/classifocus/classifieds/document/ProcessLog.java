package com.classifocus.classifieds.document;

import org.springframework.data.annotation.Id;

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
public class ProcessLog extends BaseDocument {

	private static final long serialVersionUID = 1541095357738912465L;

	@Id
	private String id;

	@Indexed
	private String className;

	@Indexed
	private String methodName;

	private String methodParameters;
	
	private Long totalTimeMillis;

}
