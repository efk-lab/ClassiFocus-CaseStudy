package com.classifocus.classifieds.model;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.classifocus.classifieds.constant.ClassifiedCategory;

import lombok.Data;

@Data
public class SaveClassifiedRequest implements Serializable {

	private static final long serialVersionUID = 6662333105142946369L;

	@NotNull
	@NotBlank
	@NotEmpty
	@Size(min = 10, max = 50)
	private String title;

	@NotNull
	@NotBlank
	@NotEmpty
	@Size(min = 20, max = 200)
	private String detail;

	@NotNull
	private ClassifiedCategory category;

}
