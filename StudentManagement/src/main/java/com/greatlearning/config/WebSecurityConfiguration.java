package com.greatlearning.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.greatlearning.service.DomainUserDetailsService;

@Configuration
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	DomainUserDetailsService domainUserDetailsService;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(domainUserDetailsService).passwordEncoder(passwordEncoder);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().disable();
		http.csrf().disable();
		http.headers().frameOptions().disable();

		http.authorizeRequests().antMatchers("/h2-console/**", "/h2-console**").permitAll()
			.antMatchers("/students/showFormForUpdate", "/students/delete").hasRole("ADMIN")
			.antMatchers("/students/showFormForAdd", "/students/save", "/students/list").hasAnyRole("ADMIN", "USER")
			.anyRequest().fullyAuthenticated().and().exceptionHandling().accessDeniedPage("/students/403").and()
			.formLogin().defaultSuccessUrl("/students/list", true).and().httpBasic();
	}

}
