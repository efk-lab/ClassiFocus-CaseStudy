package com.classifocus.classifieds.document;

import java.io.Serializable;
import java.util.Date;

import com.redis.om.spring.annotations.Indexed;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor
public class BaseDocument implements Serializable {

	private static final long serialVersionUID = 2629837058985871660L;

	@Indexed
	protected String createdBy;

	@Indexed
	protected Date creationDate;

	@Indexed
	protected String modifiedBy;

	@Indexed
	protected Date modificationDate;

}
