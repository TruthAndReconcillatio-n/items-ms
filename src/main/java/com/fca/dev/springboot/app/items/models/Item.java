package com.fca.dev.springboot.app.items.models;

import com.fca.dev.springboot.app.commons.models.entity.Product;

import lombok.Data;

@Data
public class Item {

	private Product product;
	private Integer quantity;

	public Double getTotal() {
		return product.getPrice() * quantity.doubleValue();
	}

	public Item(Product product, Integer quantity) {
		this.product = product;
		this.quantity = quantity;
	}

	public Item() {
	}

}
