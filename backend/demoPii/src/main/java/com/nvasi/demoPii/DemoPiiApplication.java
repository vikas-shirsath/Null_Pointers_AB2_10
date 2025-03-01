package com.nvasi.demoPii;

import com.nvasi.demoPii.service.OCRservice;
import com.nvasi.demoPii.service.PIIDetector;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.List;
import java.util.Map;

@SpringBootApplication
public class DemoPiiApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoPiiApplication.class, args);
	}
}
