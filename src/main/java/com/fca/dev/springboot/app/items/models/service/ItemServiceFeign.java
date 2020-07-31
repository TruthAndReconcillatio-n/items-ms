package com.fca.dev.springboot.app.items.models.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.fca.dev.springboot.app.items.clients.ProductRestClient;
import com.fca.dev.springboot.app.items.models.Item;

@Service
@Primary
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

}
