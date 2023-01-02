package com.bum.webstore.service;

import java.util.List;

import com.bum.webstore.domain.Product;

public interface ProductService {
	public int updateProductStock(); 
	public List<Product> getAllProducts(); 
}
