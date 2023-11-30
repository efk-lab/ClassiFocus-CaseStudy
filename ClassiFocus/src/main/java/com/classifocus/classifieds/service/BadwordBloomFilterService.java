package com.classifocus.classifieds.service;

import org.redisson.api.RBloomFilter;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.classifocus.classifieds.repository.BadWordRespository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BadwordBloomFilterService extends BaseService {

	@Autowired
	private BadWordRespository badWordRespository;

	@Autowired
	private RedissonClient redissonClient;


	public RBloomFilter<String> getUserNameBloomFilter() {
		
		RBloomFilter<String> stringRBloomFilter = redissonClient.getBloomFilter("badwords");
		stringRBloomFilter.tryInit(2172, 0.001);
		badWordRespository.findAll().forEach(badword -> stringRBloomFilter.add(badword.getBadWord()));
		
		log.info("BadwordBloomFilterService finished. User:" + userService.getUser());
		
		return stringRBloomFilter;

	} 
}
