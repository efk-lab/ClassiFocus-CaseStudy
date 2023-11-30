//package com.classifocus.classifieds.repository;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.data.redis.DataRedisTest;
//import org.springframework.context.annotation.Import;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//
//import com.classifocus.classifieds.conf.redis.TestRedisConfiguration;
//import com.classifocus.classifieds.document.User;
//
//
//@ExtendWith(SpringExtension.class)
//@DataRedisTest
//@Import(TestRedisConfiguration.class)
//public class UserRepositoryTest {
//	
//	@Autowired
//	private UserRepository userRepository;
//	
//
//	@BeforeEach
//	public void setUp() {
//		userRepository.deleteAll();
//	}
//
//
//	@Test
//	public void testSaveUser() throws Exception {
//
//		User user = prepareUser();
//
//		User savedUser = userRepository.save(user);
//		User foundUser = userRepository.findById(savedUser.getUserId()).get();
//		
//		assertThat(foundUser.getEmail()).isEqualTo(savedUser.getEmail());
//
//	}
//	
//
//	@Test
//	public void testFindByEmail() throws Exception {
//
//		User user = prepareUser();
//
//		User savedUser = userRepository.save(user);
//		User foundUser = userRepository.findByEmail("xxx@gmail.com").get();
//		
//		assertThat(foundUser.getEmail()).isEqualTo(savedUser.getEmail());
//
//	}
//	
//	private User prepareUser() {
//		return User.builder().userId("62d322ddf9f5e01864bed242").email("xx@gmail.com").build();	
//	}
//}
