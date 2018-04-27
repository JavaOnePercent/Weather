package com.example.demo;

import com.example.demo.forms.MainForm;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) throws Exception {
		MainForm mainApp = new MainForm();
		SpringApplication.run(DemoApplication.class, args);
		mainApp.setVisible(true);

	}
}
