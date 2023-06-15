package com.example.todoapp;

import com.example.todoapp.controller.AuthController;
import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TodoappApplication {
//	private static final Logger LOG = Logger.getLogger(TodoappApplication.class);

	public static void main(String[] args) {
		System.out.println("------------------------App Started --------------------");
		SpringApplication.run(TodoappApplication.class, args);
		System.out.println("------------------------ Reading log4j properties file --------------------");

	}

}
