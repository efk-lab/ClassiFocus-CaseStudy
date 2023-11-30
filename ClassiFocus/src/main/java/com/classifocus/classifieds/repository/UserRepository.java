package com.classifocus.classifieds.repository;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.classifocus.classifieds.document.User;
import com.redis.om.spring.repository.RedisDocumentRepository;

@Repository
public interface UserRepository extends RedisDocumentRepository<User, String> {

	Optional<User> findByEmail(String email);

	void deleteByEmail(String email);

}