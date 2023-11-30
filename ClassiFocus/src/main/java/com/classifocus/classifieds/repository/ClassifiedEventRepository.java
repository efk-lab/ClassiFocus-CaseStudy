package com.classifocus.classifieds.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.classifocus.classifieds.document.ClassifiedEvent;
import com.redis.om.spring.repository.RedisDocumentRepository;

@Repository
public interface ClassifiedEventRepository extends RedisDocumentRepository<ClassifiedEvent, String> {

	Page<ClassifiedEvent> findByClassifiedId(String classifiedId, Pageable pageable);

}
