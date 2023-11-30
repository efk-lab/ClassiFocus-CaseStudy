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
//import com.classifocus.classifieds.document.Classified;
//
//@ExtendWith(SpringExtension.class)
//@DataRedisTest
//@Import(TestRedisConfiguration.class)
//public class ClassifiedRepositoryTest {
//	
//	@Autowired
//	private ClassifiedRepository classifiedRepository;
//	
//
//	@BeforeEach
//	public void setUp() {
//		classifiedRepository.deleteAll();
//	}
//
//
//	@Test
//	public void testFindByTitleAndDetailAndCategory() throws Exception  {
//		Classified classified = prepareClassified();
//
//		Classified savedClassified = classifiedRepository.save(classified);
//		Classified foundClassified = classifiedRepository.findByTitleAndDetailAndCategory(savedClassified.getTitle(), savedClassified.getDetail(), savedClassified.getCategory());
//		
//		assertThat(foundClassified.getTitle()).isEqualTo(savedClassified.getTitle());
//
//	}
//	
//	@Test
//	public void testFindByIdAndStatus() throws Exception {
//		Classified classified = prepareClassified();
//
//		Classified savedClassified = classifiedRepository.save(classified);
//		Classified foundClassified = classifiedRepository.findByIdAndStatus(savedClassified.getId(), savedClassified.getStatus());
//		
//		assertThat(foundClassified.getTitle()).isEqualTo(savedClassified.getTitle());
//	}
//	
//	private Classified prepareClassified() {
//		return Classified.builder().id("01GW845AQ7SRFM9CPSTR9DHTZM").title("İlan").detail("Satılık Daire").category(1).status(1).build();
//	}
//
//}
