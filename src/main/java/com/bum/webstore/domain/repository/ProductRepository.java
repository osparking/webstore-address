package com.bum.webstore.domain.repository;

import java.util.List;

import com.bum.webstore.domain.Product;

public interface ProductRepository {
	List<Product> getAllProducts();

	int updateStock(String productId, long unitInStock);
}
