package com.classifocus.classifieds.runner;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.classifocus.classifieds.document.BadWord;
import com.classifocus.classifieds.repository.BadWordRespository;

import lombok.extern.slf4j.Slf4j;

@Component
@Order(Ordered.LOWEST_PRECEDENCE)
@Slf4j
public class BadwordsRunner implements CommandLineRunner {

	@Autowired
	private BadWordRespository badWordRespository;

	@Override
	public void run(String... args) throws Exception {
		
		log.info("Deleting previous badwords for fresh restart.");
		badWordRespository.deleteAll();
		
		InputStream inputStream = BadwordsRunner.class.getResourceAsStream("/badwords.txt");
		InputStreamReader streamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
		BufferedReader reader = new BufferedReader(streamReader);
		List<BadWord> badWordList = new ArrayList<BadWord>();
		
		for (String line; (line = reader.readLine()) != null;) {
			BadWord badword = new BadWord();
			badword.setBadWord(line);
			badWordList.add(badword);
		}
		
		reader.close();
		streamReader.close();

		log.info("Saving badwords for fresh restart.");
		badWordRespository.saveAll(badWordList);

	}

}
