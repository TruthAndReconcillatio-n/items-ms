package com.fca.dev.springboot.app.items.models;

import java.util.Date;

import lombok.Data;

@Data
public class Product {
	
	private Long id;
	private String name;
	private Double price;
	private Date createAt;

}
