package com.classifocus.classifieds.aspect;

import java.util.Arrays;
import java.util.Date;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import com.classifocus.classifieds.constant.Role;
import com.classifocus.classifieds.document.ProcessLog;
import com.classifocus.classifieds.error.ClassiFocusException;
import com.classifocus.classifieds.repository.ProcessLogRepository;

import lombok.extern.slf4j.Slf4j;

@Aspect
@Component
@Slf4j
public class ProcessLogAspect {
	
	@Value("${process.log.time.limit.millis}")
	private long timeLimit;

	@Autowired
	private ProcessLogRepository processLogRepository;
	

	@Around("execution(public * com.classifocus.classifieds.controller..*(..)))")
	public Object logRestfullMethods(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		try {
			StopWatch stopWatch = new StopWatch();
			stopWatch.start();
			Object methodResponse = proceedingJoinPoint.proceed();
			stopWatch.stop();

			if (stopWatch.getTotalTimeMillis() > timeLimit) {
				MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();

				ProcessLog processLog = new ProcessLog();
				processLog.setClassName(methodSignature.getDeclaringType().getName());
				processLog.setMethodName(methodSignature.getName());
				processLog.setMethodParameters(methodParametersToString(proceedingJoinPoint));
				processLog.setTotalTimeMillis(stopWatch.getTotalTimeMillis());
				processLog.setCreationDate(new Date());
				processLog.setCreatedBy(Role.SYSTEM.name());

				processLogRepository.save(processLog);

				log.info("ProcessLog saved: " + processLog.toString());
			}

			return methodResponse;

		} catch (ClassiFocusException e) {
			log.error("ProcessLogAspect encountered a ClassiFocusException. Details:" + e.getMessage());
			throw e;
		} catch (Throwable e) {
			log.error("ProcessLogAspect encountered an Unknown Exception. Details:" + e.getMessage());
			throw e;
		}
	}

	private String methodParametersToString(ProceedingJoinPoint proceedingJoinPoint) {
		String params = null;

		try {
			Object[] methodParameters = proceedingJoinPoint.getArgs();
			params = Arrays.toString(methodParameters);

			if (!params.isEmpty() && params.length() > 1000) {
				params = params.substring(0, 1000);
			}

		} catch (Throwable e) {
			log.error("Unknown Exception Occurred. Details:", e.getMessage());
		}

		return params;
	}

}
