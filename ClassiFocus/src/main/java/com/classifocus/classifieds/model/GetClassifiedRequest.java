package com.classifocus.classifieds.model;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class GetClassifiedRequest implements Serializable {

	private static final long serialVersionUID = -4633353874398832102L;

	@NotNull
	@NotBlank
	@NotEmpty
	private String classifiedId;

}
