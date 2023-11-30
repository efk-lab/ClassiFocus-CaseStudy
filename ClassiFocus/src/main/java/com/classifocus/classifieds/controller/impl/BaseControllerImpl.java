package com.classifocus.classifieds.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.classifocus.classifieds.conf.security.UserService;

public abstract class BaseControllerImpl {
	
	@Autowired
	protected UserService userService;
	
}
