package com.example.demo.services;

import java.util.List;

import com.example.demo.models.Product;

public interface ProductService {
	List<Product> getAll();
	Boolean create(Product product);
	Boolean update(Product product);
	Product findById(Integer id);
	Boolean delete(Integer id);
}
