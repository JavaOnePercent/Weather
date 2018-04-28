package com.example.demo;

import com.example.demo.forms.MainForm;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) throws Exception {
		ConfigurableApplicationContext context = new SpringApplicationBuilder(DemoApplication.class).headless(false).run(args);
		MainForm mainFrom = context.getBean(MainForm.class);
		mainFrom.setVisible(true);

	}
}
