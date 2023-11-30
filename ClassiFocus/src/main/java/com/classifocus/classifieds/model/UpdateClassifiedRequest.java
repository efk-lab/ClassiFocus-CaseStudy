package com.classifocus.classifieds.model;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.classifocus.classifieds.constant.ClassifiedStatus;

import lombok.Data;

@Data
public class UpdateClassifiedRequest implements Serializable {

	private static final long serialVersionUID = -5489406507617616962L;

	@NotNull
	@NotBlank
	@NotEmpty
	private String classifiedId;

	@NotNull
	private ClassifiedStatus classifiedStatus;

}
