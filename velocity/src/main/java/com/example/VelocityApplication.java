package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@SpringBootApplication
public class VelocityApplication {

	public static void main(String[] args) {
		SpringApplication.run(VelocityApplication.class, args);
	}
	
	@RequestMapping(value="/hello")
	public String form(Model model) {
		Customer customer = new Customer("hanjoong.cho");
		model.addAttribute("name", "hanjoong.cho");
		model.addAttribute("customer", customer);
		return "hello";
	}
}
