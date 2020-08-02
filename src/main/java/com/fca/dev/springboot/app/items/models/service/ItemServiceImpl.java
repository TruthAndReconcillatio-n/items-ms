package com.fca.dev.springboot.app.items.models.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fca.dev.springboot.app.items.models.Item;
import com.fca.dev.springboot.app.items.models.Product;

@Primary
@Service("serviceRestTemplate")
public class ItemServiceImpl implements ItemService {

	@Autowired
	private RestTemplate restClient;
	
	@Override
	public List<Item> findAll() {
		List<Product> productos = Arrays.asList(restClient.getForObject("http://service.product/ver", Product[].class));
		return productos.stream().map(p -> new Item(p, 1)).collect(Collectors.toList());
	}

	@Override
	public Item findById(Long id, Integer cantidad) {
		Map<String,String> pathVars = new HashMap<String,String>();
		pathVars.put("id", id.toString());
		Product product = restClient.getForObject("http://service.product/ver/{id}", Product.class,pathVars);
		
		return new Item(product, cantidad);
	}

	@Override
	public Product save(Product product) {
		HttpEntity<Product> body = new HttpEntity<Product>(product);
		ResponseEntity<Product> response =  restClient.exchange("http://service.product/create", HttpMethod.POST, body, Product.class);
		Product productResponse = response.getBody();
		return productResponse;
	}

	@Override
	public Product update(Product product, Long id) {
		Map<String,String> pathVars = new HashMap<String,String>();
		pathVars.put("id", id.toString());
		HttpEntity<Product> body = new HttpEntity<Product>(product);
		ResponseEntity<Product> response = restClient.exchange("http://service.product/edit/{id}",HttpMethod.PUT, body,Product.class,pathVars);
		return response.getBody();
	}

	@Override
	public void delete(Long id) {
		Map<String,String> pathVars = new HashMap<String,String>();
		pathVars.put("id", id.toString());
		restClient.delete("http://service.product/delete/{id}",pathVars);
		
	}

}
