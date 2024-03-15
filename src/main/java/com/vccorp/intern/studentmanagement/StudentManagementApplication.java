package com.vccorp.intern.studentmanagement;

import com.vccorp.intern.studentmanagement.service.StudentManagement;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class StudentManagementApplication {

	public static void main(String[] args) {
		new StudentManagement().inputData();
		ApplicationContext context = SpringApplication.run(StudentManagementApplication.class, args);
	}

}
