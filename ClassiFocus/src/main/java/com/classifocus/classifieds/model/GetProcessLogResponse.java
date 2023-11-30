package com.classifocus.classifieds.model;

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
public class GetProcessLogResponse extends BaseResponse {

	private static final long serialVersionUID = 5517402562614829687L;

	private String processLogId;

	private String className;

	private String methodName;

	private String methodParameters;

	private Long totalTimeMillis;

}
