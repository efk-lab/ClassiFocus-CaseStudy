package com.classifocus.classifieds.document;

import org.springframework.data.annotation.Id;

import com.classifocus.classifieds.constant.Role;
import com.redis.om.spring.annotations.Document;
import com.redis.om.spring.annotations.Indexed;

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
public class User extends BaseDocument {

	private static final long serialVersionUID = -3974851705660591780L;

	@Id
	private String userId;

	@Indexed
	private String email;

	private String password;

	private Role role;

}
