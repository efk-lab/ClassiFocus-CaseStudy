package com.classifocus.classifieds.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;

import com.classifocus.classifieds.document.ProcessLog;
import com.classifocus.classifieds.model.GetProcessLogResponse;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ProcessLogMapper extends BaseMapper {


	public Page<GetProcessLogResponse> toGetProcessLogResponse(Page<ProcessLog> processLogsPage) {
		
		log.info("Mapping ProcessLog's to GetProcessLogResponse. User:" + userService.getUser());
		
		List<GetProcessLogResponse> processLogResponseList = new ArrayList<GetProcessLogResponse>();
		List<ProcessLog> processLogs = processLogsPage.getContent();

		processLogs.stream()
		   .forEach(processLog -> processLogResponseList.add(
				   GetProcessLogResponse.builder()
				   				.processLogId(processLog.getId())
				   				.className(processLog.getClassName())
				   				.methodName(processLog.getMethodName())
				   				.methodParameters(processLog.getMethodParameters())
				   				.totalTimeMillis(processLog.getTotalTimeMillis())
				   				.createdBy(processLog.getCreatedBy())
								.creationDate(processLog.getCreationDate())
				   				.build()			   
				   ));

        return new PageImpl<>(processLogResponseList, processLogsPage.getPageable(), processLogsPage.getTotalElements());
        
	}
	
}
