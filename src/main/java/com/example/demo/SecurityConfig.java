package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;


import com.example.demo.services.CustomUserDetailService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	@Autowired
	private CustomUserDetailService customUserDetailService;
	@Bean
	BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.csrf(csrf->csrf.disable()).authorizeHttpRequests((auth)->auth
				.requestMatchers("/*").permitAll()
				.requestMatchers("/admin/product/**","/admin/category").hasAuthority("ADMIN")
				.anyRequest().authenticated()
			)
			.formLogin(login -> login
				.loginPage("/logon")
				.loginProcessingUrl("/logon")
				.usernameParameter("username")
				.passwordParameter("password")
				.defaultSuccessUrl("/admin",true))
				.logout(logout->logout.logoutUrl("/admin-logout").logoutSuccessUrl("/logon"))
				.logout(logout->logout.logoutUrl("/admin-logout").logoutSuccessUrl("/logon")
			)
			;

		return http.build();
	}


	@Bean
	WebSecurityCustomizer webSecurityCustomizer() {
		return (web)->web.ignoring().requestMatchers("/static/**","/fe/**","/assets/**","/uploads/**");
	}
//	@Bean
//	public UserDetailsService userDetailsService() {
//		UserDetails user =
//			 User.withDefaultPasswordEncoder()
//				.username("user")
//				.password("password")
//				.roles("USER")
//				.build();
//
//		return new InMemoryUserDetailsManager(user);
//	}

}
