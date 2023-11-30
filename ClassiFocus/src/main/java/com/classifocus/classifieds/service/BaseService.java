package com.classifocus.classifieds.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.classifocus.classifieds.conf.security.UserService;

public abstract class BaseService {
	
	@Autowired
	protected UserService userService;
	
}
