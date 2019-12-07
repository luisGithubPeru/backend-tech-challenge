package com.talently.challenge.model;

import java.math.BigDecimal;

public class Product {

	private ProductCode code;
	private String name;
	private BigDecimal price;

	public Product(ProductCode code) {
		super();
		this.code = code;
		this.name = code.getName();
		this.price = code.getPrice();
	}

	@Override
	public String toString() {
		return "Product [code=" + code + ", name=" + name + ", price=" + price + "]";
	}

	public ProductCode getCode() {
		return code;
	}

	public void setCode(ProductCode code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

}
