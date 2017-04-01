package com.example.home;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.home.mapper.HomeMapper;

@RestController
public class HomeController {
	@Autowired HomeMapper homeMapper;

	@RequestMapping("/")
    public String home() {
        return "Hello World!";
    }

	@RequestMapping("/{name}")
	public Home home(
			@PathVariable String name) {
		Home home = homeMapper.readHome(name);
		return home;
	}
	
	@RequestMapping("/admin")
	public String admin() {
		return "This is admin page";
	}
	
	@RequestMapping("/user")
	public String user() {
		return "this is user page";
	}
     
}
