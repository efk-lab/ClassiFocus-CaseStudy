package com.classifocus.classifieds.conf.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

import com.classifocus.classifieds.constant.Role;
import com.classifocus.classifieds.error.ClassiFocusAccessDeniedHandler;
import com.classifocus.classifieds.error.ClassiFocusAuthenticationEntryPoint;

@Configuration
@EnableResourceServer
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {
	
	private final ClassiFocusAuthenticationEntryPoint classiFocusAuthenticationEntryPoint;
	
	private final String RESOURCE_SERVER_RESOURCE_ID = "classiFocus";

	public ResourceServerConfiguration(ClassiFocusAuthenticationEntryPoint classiFocusAuthenticationEntryPoint) {
		this.classiFocusAuthenticationEntryPoint = classiFocusAuthenticationEntryPoint;
	}

	@Override
	public void configure(ResourceServerSecurityConfigurer resources) {
		resources.resourceId(RESOURCE_SERVER_RESOURCE_ID);
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
				.antMatcher("/dashboard/**").authorizeRequests()
				.antMatchers("/dashboard/registry/sign-up/**").permitAll()
				.antMatchers("/dashboard/classified/record/**").hasAnyAuthority(Role.ADMIN.name(), Role.USER.name())
				.antMatchers("/dashboard/classified/details/**").hasAnyAuthority(Role.ADMIN.name(), Role.USER.name())
				.antMatchers("/dashboard/classified/update/**").hasAnyAuthority(Role.ADMIN.name(), Role.USER.name())
				.antMatchers("/dashboard/classified/events/**").hasAuthority(Role.ADMIN.name())
				.antMatchers("/dashboard/classifieds/statistics/by-status/**").hasAuthority(Role.ADMIN.name())
				.antMatchers("/dashboard/classifieds/statistics/by-category/**").hasAuthority(Role.ADMIN.name())
				.antMatchers("/dashboard/classifieds/processlogs/**").hasAuthority(Role.ADMIN.name())
				.antMatchers("/dashboard/**").authenticated().anyRequest().authenticated().and().exceptionHandling()
				.authenticationEntryPoint(classiFocusAuthenticationEntryPoint)
				.accessDeniedHandler(new ClassiFocusAccessDeniedHandler());

	}
}
