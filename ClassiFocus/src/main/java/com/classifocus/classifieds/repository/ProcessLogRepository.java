package com.classifocus.classifieds.repository;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.classifocus.classifieds.document.ProcessLog;
import com.redis.om.spring.repository.RedisDocumentRepository;

@Repository
public interface ProcessLogRepository extends RedisDocumentRepository<ProcessLog, String> {

	List<ProcessLog> findByCreationDateAfter(Date startDate);

}
