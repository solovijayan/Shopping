package com.mock.Shopping;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages={"com.mock.security"}) 
public class App 
{
	private static final Logger logger = LogManager.getLogger(App.class);
		public static void main(String[] args) {
			SpringApplication.run(App.class, args);
			
		}
}
