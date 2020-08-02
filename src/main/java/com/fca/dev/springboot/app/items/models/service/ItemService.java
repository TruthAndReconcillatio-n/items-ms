package com.fca.dev.springboot.app.items.models.service;
import com.fca.dev.springboot.app.commons.models.entity.Product;

import java.util.List;

import com.fca.dev.springboot.app.items.models.Item;

public interface ItemService {

	public List<Item> findAll();
	public Item findById(Long id, Integer cantidad);
	public Product save(Product product);
	public Product update(Product product, Long id);
	public void delete (Long id);
}
