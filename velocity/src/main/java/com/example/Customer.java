package com.example;

public class Customer {
	
	public String name;

	Customer(String name) {
		this.name = name;
	}
	
	public String getNameWithLabel() {
		return "#### " + name + " ####";
	}

}
