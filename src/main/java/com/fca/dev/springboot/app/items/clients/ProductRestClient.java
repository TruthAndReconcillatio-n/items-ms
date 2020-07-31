package com.fca.dev.springboot.app.items.clients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.fca.dev.springboot.app.items.models.Product;

@FeignClient(name="service.products")
public interface ProductRestClient {
	
	@GetMapping("/ver")
	public List<Product> listar();
	
	@GetMapping("/ver/{id}")
	public Product detalle(@PathVariable Long id);
}
