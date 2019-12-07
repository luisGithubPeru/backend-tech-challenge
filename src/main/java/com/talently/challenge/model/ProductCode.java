package com.talently.challenge.model;

import java.math.BigDecimal;

public enum ProductCode {
	VOUCHER(5.00,"Cabify Voucher"), TSHIRT(20.00,"Cabify T-Shirt"), MUG(7.5,"Cabify Coffee Mug");

	private final BigDecimal price;
	private String name;

	ProductCode(double price, String name) {
		this.price = new BigDecimal(price);
		this.name = name;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public String getName() {
		return name;
	}
	
	
	
	
}
