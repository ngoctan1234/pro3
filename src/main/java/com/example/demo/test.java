package com.example.demo;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class test {

	public static void main(String[] args) {
		System.out.println(new BCryptPasswordEncoder().encode("admin"));

	}

}
