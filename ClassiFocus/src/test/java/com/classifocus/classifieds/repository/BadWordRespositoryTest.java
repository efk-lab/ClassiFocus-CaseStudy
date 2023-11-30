//package com.classifocus.classifieds.repository;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//import java.util.List;
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
//import com.classifocus.classifieds.document.BadWord;
//
//@ExtendWith(SpringExtension.class)
//@DataRedisTest
//@Import(TestRedisConfiguration.class)
//public class BadWordRespositoryTest {
//
//	@Autowired
//	private BadWordRespository badWordRespository;
//	
//
//	@BeforeEach
//	public void setUp() {
//		badWordRespository.deleteAll();
//	}
//
//
//	@Test
//	public void testSearchByBadWord() throws Exception {
//		BadWord badWord = prepareBadWord();
//
//		BadWord savedBadWord = badWordRespository.save(badWord);
//		List<BadWord> foundBadWordList = badWordRespository.searchByBadWord(savedBadWord.getBadWord());
//		
//		assertThat(foundBadWordList.get(0).getBadWord()).isEqualTo(savedBadWord.getBadWord());
//
//	}
//	
//	private BadWord prepareBadWord() {
//		return BadWord.builder().id("01GW845AQ7SRFM9CPSTR9DHTZM").badWord("xyx").build();
//	}
//
//	
//}
