package com.classifocus.classifieds.document;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;

import com.redis.om.spring.annotations.Document;
import com.redis.om.spring.annotations.Indexed;
import com.redis.om.spring.annotations.Searchable;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Document
@Data
@EqualsAndHashCode(callSuper = false)
@SuperBuilder
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor
public class Classified extends BaseDocument {

	private static final long serialVersionUID = -6897750124260013635L;

	@Id
	@Indexed
	private String id;

	@Searchable
	private String title;

	@Searchable
	private String detail;

	@Indexed
	private int category;

	@Indexed
	private int status;

	private LocalDateTime endDate;

}
