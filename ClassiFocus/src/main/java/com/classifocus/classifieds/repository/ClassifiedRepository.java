package com.classifocus.classifieds.repository;

import org.springframework.stereotype.Repository;

import com.classifocus.classifieds.document.Classified;
import com.redis.om.spring.repository.RedisDocumentRepository;

@Repository
public interface ClassifiedRepository extends RedisDocumentRepository<Classified, String> {


	Classified findByTitleAndDetailAndCategory(String title, String Detail, int category);

	Classified findByIdAndStatus(String id, int status);

}
