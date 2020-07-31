package com.fca.dev.springboot.app.items.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.fca.dev.springboot.app.items.models.Item;
import com.fca.dev.springboot.app.items.models.Product;
import com.fca.dev.springboot.app.items.models.service.ItemService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
public class ItemController {

	@Autowired
	@Qualifier("serviceRestTemplate")
	private ItemService itemService;
	
	@GetMapping("/ver")
	public List<Item> listar(){
		return itemService.findAll();
	}
	
	@HystrixCommand(fallbackMethod = "alternativeMethod")
	@GetMapping("/ver/{id}/cantidad/{cantidad}")
	public Item detalle(@PathVariable Long id, @PathVariable Integer cantidad){
		return itemService.findById(id, cantidad);
	}
	
	public Item alternativeMethod(@PathVariable Long id, @PathVariable Integer cantidad){
		Item item = new Item();
		Product product = new Product();
		item.setQuantity(cantidad);
		product.setId(id);
		product.setName("test product circuit breaker");
		product.setPrice(3500.00);
		item.setProduct(product);
		return item;
	}
}
