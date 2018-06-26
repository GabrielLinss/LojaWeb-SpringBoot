package com.ufc.br;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class LojaWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(LojaWebApplication.class, args);
	}
}
