package com.classifocus.classifieds.model;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class GetClassifiedEventsRequest implements Serializable {

	private static final long serialVersionUID = 715068484795923225L;

	@NotNull
	@NotBlank
	@NotEmpty
	private String classifiedId;

}
