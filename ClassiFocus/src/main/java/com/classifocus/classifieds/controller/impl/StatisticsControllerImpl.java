package com.classifocus.classifieds.controller.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.classifocus.classifieds.controller.StatisticsController;
import com.classifocus.classifieds.model.GetClassifiedStatsByCategoryResponse;
import com.classifocus.classifieds.model.GetClassifiedStatsByStatusResponse;
import com.classifocus.classifieds.service.StatisticsService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class StatisticsControllerImpl extends BaseControllerImpl implements StatisticsController {

	@Autowired
	private StatisticsService statisticsService;
	

	@Override
	public ResponseEntity<List<GetClassifiedStatsByStatusResponse>> getClassifiedStatsByStatus(){
		
		log.info("GetClassifiedStatsByStatus request received. RequestedBy: " + userService.getUser());

		List<GetClassifiedStatsByStatusResponse> response = statisticsService.getClassifiedStatsByStatus();

		if (response != null) {
			return new ResponseEntity<>(response, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
		}
		
	}

	@Override
	public ResponseEntity<List<GetClassifiedStatsByCategoryResponse>> getClassifiedStatsByCategory() {
		
		log.info("GetClassifiedStatsByCategory request received. RequestedBy: " + userService.getUser());

		List<GetClassifiedStatsByCategoryResponse> response = statisticsService.getClassifiedStatsByCategory();

		if (response != null) {
			return new ResponseEntity<>(response, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
		}
		
	}

}
