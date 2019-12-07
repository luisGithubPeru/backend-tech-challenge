package com.talently.challenge.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Cart {

	private List<Product> items;
	private BigDecimal amount;

	public Cart() {
		amount = new BigDecimal(0);
		items = new ArrayList<Product>();
	}

	public int getNumberOfItems() {
		return items.size();
	}

	public void addItem(Product product) {
		items.add(product);
	}

	/**
	 * Gets Cart amount based on DISCOUNTS
	 * 
	 * @return
	 */
	public BigDecimal getAmount() {
		amount = new BigDecimal(0);
		int voucherCount = 0;
		int tshirtCount = 0;
		int mugCount = 0;
		for (Product product : items) {
			if (product.getCode().equals(ProductCode.VOUCHER)) {
				voucherCount++;
			} else if (product.getCode().equals(ProductCode.TSHIRT)) {
				tshirtCount++;
			} else {
				mugCount++;
			}
		}

		int voucherDiscounts = voucherCount / 3;
		boolean tshirtDiscounts = tshirtCount >= 3;

		BigDecimal voucherAmount = new BigDecimal(voucherCount - voucherDiscounts);
		voucherAmount.multiply(ProductCode.VOUCHER.getPrice());

		BigDecimal tshirtAmount;
		if (tshirtDiscounts)
			tshirtAmount = new BigDecimal(tshirtCount * 19.00);
		else {
			tshirtAmount = new BigDecimal(tshirtCount);
			tshirtAmount.multiply(ProductCode.TSHIRT.getPrice());
		}
		
		BigDecimal mugAmount = new BigDecimal(mugCount).multiply(ProductCode.MUG.getPrice());

		return amount.add(voucherAmount).add(tshirtAmount).add(mugAmount);
	}
}
