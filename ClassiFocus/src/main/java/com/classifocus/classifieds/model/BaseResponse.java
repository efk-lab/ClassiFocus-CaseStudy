package com.classifocus.classifieds.model;

import java.io.Serializable;
import java.util.Date;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor
public class BaseResponse implements Serializable {

	private static final long serialVersionUID = -8583988220190816634L;

	private String createdBy;

	private Date creationDate;

	private String modifiedBy;

	private Date modificationDate;

}
