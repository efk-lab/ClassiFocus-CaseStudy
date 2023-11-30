package com.classifocus.classifieds.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.classifocus.classifieds.constant.ClassifiedCategory;
import com.classifocus.classifieds.constant.ClassifiedStatus;
import com.classifocus.classifieds.model.GetClassifiedStatsByCategoryResponse;
import com.classifocus.classifieds.model.GetClassifiedStatsByStatusResponse;
import com.redis.om.spring.tuple.Triple;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class StatisticsMapper extends BaseMapper {


	public List<GetClassifiedStatsByStatusResponse> toGetClassifiedStatsByStatus(List<Triple<String, Long, Long>> aggResultByStatus) {

		log.info("Mapping SaveDeliveryPointRequest to GetStatisticsResponse. User:" + userService.getUser());

		List<GetClassifiedStatsByStatusResponse> statsByStatusList = new ArrayList<GetClassifiedStatsByStatusResponse>();

		aggResultByStatus.stream().forEach(triple -> statsByStatusList
				.add(GetClassifiedStatsByStatusResponse.builder()
						.classifiedStatus(ClassifiedStatus.nameOf(Integer.parseInt(triple.getFirst())))
						.classifiedCount(triple.getSecond())
						.categoryCount(triple.getThird())
						.build()));

		return statsByStatusList;

	}

	public List<GetClassifiedStatsByCategoryResponse> toGetClassifiedStatsByCategory(List<Triple<String, Long, Long>> aggResultByCategory) {

		log.info("Mapping SaveDeliveryPointRequest to GetStatisticsResponse. User:" + userService.getUser());

		List<GetClassifiedStatsByCategoryResponse> statsByCategoryList = new ArrayList<GetClassifiedStatsByCategoryResponse>();
		
		aggResultByCategory.stream().forEach(triple -> statsByCategoryList
				.add(GetClassifiedStatsByCategoryResponse.builder()
						.classifiedCategory(ClassifiedCategory.nameOf(Integer.parseInt(triple.getFirst())))
						.classifiedCount(triple.getSecond())
						.statusCount(triple.getThird())
						.build()));


		return statsByCategoryList;

	}
}