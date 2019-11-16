package com.schoolofnet.RestAPI.services;

import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.schoolofnet.RestAPI.models.Product;
import com.schoolofnet.RestAPI.repository.ProductRepository;

@Service
public class ProductService implements IProductService {
	
	@Autowired
	private ProductRepository productRepository;
	
	public ProductService(@Autowired ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	public ProductService() {}

	@Override
	public List<Product> findAll() {
		return this.productRepository.findAll();
	}

	@Override
	public Optional<Product> findById(Long id) {
		return this.productRepository.findById(id);
	}

	@Override
	public Product create(Product product) {
		return this.productRepository.save(product);
	}

	@Override
	public Product update(Long id, Product product) {
		Optional<Product> productExists = this.productRepository.findById(id);
		if(productExists != null) {
			product.setId(productExists.get().getId());
			this.productRepository.save(product);
			return product;
		}
		return null;
	}

	@Override
	public void delete(Long id) {
		this.productRepository.deleteById(id);
	}
}
