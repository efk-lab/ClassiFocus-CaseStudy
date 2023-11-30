package com.classifocus.classifieds.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;

import com.classifocus.classifieds.conf.security.UserService;
import com.classifocus.classifieds.document.Classified;
import com.classifocus.classifieds.document.Classified$;
import com.classifocus.classifieds.mapper.StatisticsMapper;
import com.classifocus.classifieds.model.GetClassifiedStatsByCategoryResponse;
import com.classifocus.classifieds.model.GetClassifiedStatsByStatusResponse;
import com.redis.om.spring.annotations.ReducerFunction;
import com.redis.om.spring.search.stream.EntityStream;
import com.redis.om.spring.tuple.Triple;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class StatisticsService {

	@Autowired
	private UserService userService;

	@Autowired
	private StatisticsMapper statisticsMapper;

	@Autowired
	private EntityStream entityStream;

	
	public List<GetClassifiedStatsByStatusResponse> getClassifiedStatsByStatus() {
		
		List<GetClassifiedStatsByStatusResponse> response = null;
		
		@SuppressWarnings("unchecked")		
		List<Triple<String, Long, Long>> aggTripleList = entityStream.of(Classified.class) 
																	.groupBy(Classified$.STATUS) 
																	.reduce(ReducerFunction.COUNT) 
																	.reduce(ReducerFunction.COUNT_DISTINCT, Classified$.CATEGORY).as("distinctCategory") 
																	.sorted(Order.desc("@distinctCategory")) 
																	.toList(String.class, Long.class, Long.class);
		response = statisticsMapper.toGetClassifiedStatsByStatus(aggTripleList);
		
		log.info("ClassifiedStatsByStatusResponse retrieved. GetClassifiedStatsByStatusResponse: " + response.toString() + " User:" + userService.getUser());
		
		return response;
	}
	
	public List<GetClassifiedStatsByCategoryResponse> getClassifiedStatsByCategory(){

		log.info("Processing getClassifiedStatsByStatus request. User:" + userService.getUser());

		List<GetClassifiedStatsByCategoryResponse> response = null;
		
		@SuppressWarnings("unchecked")		
		List<Triple<String, Long, Long>> aggTripleList = entityStream.of(Classified.class) 
																	.groupBy(Classified$.CATEGORY) 
																	.reduce(ReducerFunction.COUNT) 
																	.reduce(ReducerFunction.COUNT_DISTINCT, Classified$.STATUS).as("distinctStatus") 
																	.sorted(Order.desc("@distinctStatus")) 
																	.toList(String.class, Long.class, Long.class);
		response = statisticsMapper.toGetClassifiedStatsByCategory(aggTripleList);
		
		log.info("ClassifiedStatsByCategoryResponse retrieved. GetClassifiedStatsByCategoryResponse: " + response.toString() + " User:" + userService.getUser());
		
		return response;
	}

}
