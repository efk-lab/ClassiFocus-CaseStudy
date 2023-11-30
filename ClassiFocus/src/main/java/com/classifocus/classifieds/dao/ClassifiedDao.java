package com.classifocus.classifieds.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import com.classifocus.classifieds.document.Classified;
import com.classifocus.classifieds.repository.ClassifiedRepository;

@Component
public class ClassifiedDao {

	@Autowired
	private ClassifiedRepository classifiedRepository;
	
	
	@CacheEvict(value = "classifieds", allEntries = true) 
	public Classified save(Classified classified) {
		return classifiedRepository.save(classified);
	}

	@Cacheable(cacheNames = "classifieds", unless="#result == null")
	public Classified findByTitleAndDetailAndCategory(String title, String detail, int category) {
		return classifiedRepository.findByTitleAndDetailAndCategory(title, detail,category);
	}
	
	@Cacheable(cacheNames = "classifieds", unless="#result == null")
	public Classified findByIdAndStatus(String id, int status){
		return classifiedRepository.findByIdAndStatus(id, status);
	}	
	
	@Cacheable(cacheNames = "classifieds", unless="#result == null")
	public Classified findById(String id){
		return classifiedRepository.findById(id).orElseGet(null);
	}	
	
}
