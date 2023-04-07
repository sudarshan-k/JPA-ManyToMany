package com.test;

import org.apache.commons.text.StringEscapeUtils;
import org.owasp.esapi.ESAPI;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class JpaManytomanyApplication {

/*	@PostConstruct
	public void initESAPI() {
		ESAPI.initialize();

	}*/


	public static void main(String[] args) {
//		SpringApplication.run(JpaManytomanyApplication.class, args);



		String abc = "abc><><&";
		System.out.println((StringEscapeUtils.escapeHtml4(null)));
//		ESAPI.securityConfiguration().setResourceDirectory("classpath:");
/*		String result = ESAPI.encoder().encodeForHTML(abc);
		System.out.println(result);*/
	}

}
