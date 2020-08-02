package com.fca.dev.springboot.app.items.clients;

import java.util.List;
import com.fca.dev.springboot.app.commons.models.entity.Product;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name="service.product")
public interface ProductRestClient {
	
	@GetMapping("/ver")
	public List<Product> listar();
	
	@GetMapping("/ver/{id}")
	public Product detalle(@PathVariable Long id);
	
	@PostMapping("/create/")
	public Product create(@RequestBody Product product);
	
	@PutMapping("/edit/{id}")
	public Product update(@RequestBody Product product, @PathVariable Long id);
	
	@DeleteMapping("/delete/{id}")
	public void delete(@PathVariable Long id);
}
