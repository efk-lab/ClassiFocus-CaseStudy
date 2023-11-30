package com.classifocus.classifieds.runner;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.classifocus.classifieds.constant.Role;
import com.classifocus.classifieds.document.User;
import com.classifocus.classifieds.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Component
@Order(1)
@Slf4j
public class UserRunner implements CommandLineRunner {

	@Value("${admin.user.email}")
	private String adminEmail;

	@Value("${admin.user.password}")
	private String adminPassword;

	@Value("${system.user.email}")
	private String systemEmail;

	@Value("${system.user.password}")
	private String systemPassword;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public void run(String... args) throws Exception {

		log.info("Deleting Admin and System user for fresh restart.");
		userRepository.deleteByEmail(adminEmail);
		userRepository.deleteByEmail(systemEmail);

		User adminUser = User.builder()
				.email(adminEmail)
				.password(passwordEncoder.encode(adminPassword))
				.role(Role.ADMIN)
				.createdBy(adminEmail)
				.creationDate(new Date())
				.build();
		userRepository.save(adminUser);		
		
		
		User systemUser = User.builder()
				.email(systemEmail)
				.password(passwordEncoder.encode(systemPassword))
				.role(Role.SYSTEM)
				.createdBy(systemEmail)
				.creationDate(new Date())
				.build();
		userRepository.save(systemUser);		
	

		log.info("Admin and System user saved for fresh restart.");
	}

}
