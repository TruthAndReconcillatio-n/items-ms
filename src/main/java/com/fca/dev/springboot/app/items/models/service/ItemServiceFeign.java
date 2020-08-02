package com.fca.dev.springboot.app.items.models.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.fca.dev.springboot.app.items.clients.ProductRestClient;
import com.fca.dev.springboot.app.items.models.Item;
import com.fca.dev.springboot.app.items.models.Product;

@Service("feignRestClient")
public class ItemServiceFeign implements ItemService {

	@Autowired
	private ProductRestClient feignClient;
	
	@Override
	public List<Item> findAll() {
		return feignClient.listar().stream().map(p -> new Item(p, 1)).collect(Collectors.toList());
	}

	@Override
	public Item findById(Long id, Integer cantidad) {
		return new Item(feignClient.detalle(id), cantidad);
	}

	@Override
	public Product save(Product product) {
		return feignClient.create(product);
	}

	@Override
	public Product update(Product product, Long id) {
		return feignClient.update(product, id);
	}

	@Override
	public void delete(Long id) {
		feignClient.delete(id);
	}

}
