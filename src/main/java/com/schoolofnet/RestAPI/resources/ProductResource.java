package com.schoolofnet.RestAPI.resources;

import com.schoolofnet.RestAPI.models.Product;
import com.schoolofnet.RestAPI.services.ProductService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "API REST - Model Product")
@RestController
@RequestMapping("/products")
public class ProductResource {
	
	@Autowired
	private ProductService productService;
	
	public ProductResource(ProductService productService) {
		this.productService = productService;
	}
	
	@ApiOperation(value = "Find all products in database")
	@GetMapping
	@ResponseBody
	@ResponseStatus(code = HttpStatus.OK)
	public List<Product> findAll() {
		return this.productService.findAll();
	}
	
	@ApiOperation(value = "Find by id in database")
	@GetMapping("/{id}")
	@ResponseBody
	@ResponseStatus(code = HttpStatus.OK)
	public Optional<Product> findById(@PathVariable(value = "id") Long id) {
		return this.productService.findById(id);
	}
	
	@ApiOperation(value = "Create new product")
	@PostMapping
	@ResponseBody
	@ResponseStatus(code = HttpStatus.CREATED)
	public ResponseEntity<?> create(@Valid @RequestBody Product product, Errors errors) {
		
		if (!errors.hasErrors()) {
			Product productCreated = this.productService.create(product);
			return new ResponseEntity<Product>(productCreated, HttpStatus.CREATED);
		}
		
		return ResponseEntity
				.badRequest()
				.body(errors
						.getAllErrors()
						.stream()
						.map(msg -> msg.getDefaultMessage())
						.collect(Collectors.joining(",")));
	}
	
	@ApiOperation(value = "Update product")
	@PutMapping("/{id}")
	@ResponseBody
	@ResponseStatus(code = HttpStatus.ACCEPTED)
	public ResponseEntity<?> update (@PathVariable(value = "id") Long id,@Valid @RequestBody Product product, Errors errors) {
		if (!errors.hasErrors()) {
			Product productCreated = this.productService.create(product);
			return new ResponseEntity<Product>(productCreated, HttpStatus.CREATED);
		}
		
		return ResponseEntity
				.badRequest()
				.body(errors
						.getAllErrors()
						.stream()
						.map(msg -> msg.getDefaultMessage())
						.collect(Collectors.joining(",")));
	}
	
	@ApiOperation(value = "Delete product")
	@DeleteMapping("/{id}")
	@ResponseStatus(code = HttpStatus.ACCEPTED)
	public void delete(@PathVariable(value = "id") Long id) {
		this.productService.delete(id);
	}
}
