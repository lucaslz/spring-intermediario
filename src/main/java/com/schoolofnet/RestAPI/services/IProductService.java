package com.schoolofnet.RestAPI.services;

import java.util.List;
import java.util.Optional;

import com.schoolofnet.RestAPI.models.Product;

public interface IProductService {
	public List<Product> findAll();
	public Optional<Product> findById(Long id);
	public Product create(Product product);
	public Product update (Long id, Product product);
	public void delete(Long id);
}
