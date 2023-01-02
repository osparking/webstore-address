package com.bum.webstore.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bum.webstore.domain.Product;
import com.bum.webstore.domain.repository.ProductRepository;
import com.bum.webstore.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;

	/**
	 * @return count of products whose stock been increased.
	 */
	@Override
	public int updateProductStock() {
		var allProducts = productRepository.getAllProducts();
		int count = 0;
		for (Product product : allProducts) {
			if (product.getUnitsInStock() < 500) {
				count += productRepository.updateStock(product.getProductId(),
						product.getUnitsInStock() + 1000);
			}
		}
		return count;
	}

	@Override
	public List<Product> getAllProducts() {
		return productRepository.getAllProducts();
	}

}
