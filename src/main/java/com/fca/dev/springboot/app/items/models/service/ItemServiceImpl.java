package com.fca.dev.springboot.app.items.models.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fca.dev.springboot.app.items.models.Item;
import com.fca.dev.springboot.app.items.models.Product;

@Service
public class ItemServiceImpl implements ItemService {

	@Autowired
	private RestTemplate restClient;
	
	@Override
	public List<Item> findAll() {
		List<Product> productos = Arrays.asList(restClient.getForObject("http://localhost:8002/ver", Product[].class));
		return productos.stream().map(p -> new Item(p, 1)).collect(Collectors.toList());
	}

	@Override
	public Item findById(Long id, Integer cantidad) {
		Map<String,String> pathVars = new HashMap<String,String>();
		pathVars.put("id", id.toString());
		Product product = restClient.getForObject("http://localhost:8002/ver/{id}", Product.class,pathVars);
		
		return new Item(product, cantidad);
	}

}
