package com.bum.webstore.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DecimalFormat;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@EqualsAndHashCode
public class Product implements Serializable {
	private static final long serialVersionUID = 1L;
	private String productId;
	private String name;
	private BigDecimal unitPrice;
	private String unitPriceStr;
	private String description;
	private String manufacturer;
	private String category;
	private long unitsInStock;
	private String unitsInStockStr;
	private long unitsInOrder;
	private boolean discontinued;
	private String condition;

	public Product(String productId, String name, BigDecimal unitPrice) {
		this.productId = productId;
		this.name = name;
		this.setUnitPrice(unitPrice);
	}
	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
		DecimalFormat df = new DecimalFormat("#,###");
		this.unitPriceStr = df.format(unitPrice);
	}
	public void setUnitsInStock(long unitsInStock) {
		this.unitsInStock = unitsInStock;
		DecimalFormat df = new DecimalFormat("#,###");
		this.unitsInStockStr = df.format(unitsInStock);
	}


}
