package com.bum.webstore.domain.repository.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.bum.webstore.domain.Product;
import com.bum.webstore.domain.repository.ProductRepository;

@Repository
public class MariaProductRepository implements ProductRepository {

	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;

	@Override
	public List<Product> getAllProducts() {
		String select = "Select * from products";
		List<Product> allProducts = jdbcTemplate.query(select, (rs, rowNum) -> {
			Product product = new Product();
			product.setProductId(rs.getString("ID"));
			product.setName(rs.getString("PROD_NAME"));
			product.setDescription(rs.getString("DESCRIPTION"));
			product.setUnitPrice(rs.getBigDecimal("UNIT_PRICE"));
			product.setManufacturer(rs.getString("MANUFACTURER"));
			product.setCategory(rs.getString("CATEGORY"));
			product.setCondition(rs.getString("PROD_CONDITION"));
			product.setUnitsInStock(rs.getLong("UNITS_IN_STOCK"));
			product.setUnitsInOrder(rs.getLong("UNITS_IN_ORDER"));
			product.setDiscontinued(rs.getBoolean("DISCONTINUED"));
			return product;
		});
		return allProducts;
	}

	/**
	 * Update stock of product by 1000 when it has stock lower than 500.
	 * @return number of updated row count
	 */
	@Override
	public int updateStock(String productId, long unitsInStock) {
		String update = "update products "
				+ "set units_in_stock = :unitsInStock "
				+ "where  units_in_stock < 500";
		Map<String, Object> params = new HashMap<>();
		params.put("unitsInStock", unitsInStock);
		int rowCount = jdbcTemplate.update(update, params);
		return rowCount;
	}

}