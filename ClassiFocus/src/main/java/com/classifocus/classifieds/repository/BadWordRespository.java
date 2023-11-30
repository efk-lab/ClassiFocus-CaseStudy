package com.classifocus.classifieds.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.classifocus.classifieds.document.BadWord;
import com.redis.om.spring.repository.RedisDocumentRepository;

@Repository
public interface BadWordRespository extends RedisDocumentRepository<BadWord, String> {

	List<BadWord> searchByBadWord(String text);

}
