package com.talently.challenge.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@ToString
public class Basket {

	@Id
	@GeneratedValue
	private long id;
	private BigDecimal amount;
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Product> products;

	public Basket() {
		amount = new BigDecimal(0);
		products = new ArrayList<Product>();
	}

	public int getProductsSize() {
		return products.size();
	}

	public void addProduct(ProductCode productCode) {
		products.add(new Product(productCode));
	}

	public BigDecimal getAmount() {
		Basket basket = this;
		int voucherCount = 0;
		int tshirtCount = 0;
		int mugCount = 0;

		for (Product product : basket.getProducts()) {
			if (product.getCode().equals(ProductCode.VOUCHER)) {
				voucherCount++;
			} else if (product.getCode().equals(ProductCode.TSHIRT)) {
				tshirtCount++;
			} else {
				mugCount++;
			}
		}

		int voucherDiscounts = voucherCount / 2;
		boolean tshirtDiscounts = tshirtCount >= 3;

		BigDecimal voucherAmount = new BigDecimal(voucherCount - voucherDiscounts);
		voucherAmount = voucherAmount.multiply(ProductCode.VOUCHER.getPrice());

		BigDecimal tshirtAmount;
		if (tshirtDiscounts)
			tshirtAmount = new BigDecimal(tshirtCount * 19.00);
		else {
			tshirtAmount = new BigDecimal(tshirtCount);
			tshirtAmount = tshirtAmount.multiply(ProductCode.TSHIRT.getPrice());
		}

		BigDecimal mugAmount = new BigDecimal(mugCount).multiply(ProductCode.MUG.getPrice());

		BigDecimal amount = new BigDecimal(0).add(voucherAmount).add(tshirtAmount).add(mugAmount);
		basket.setAmount(amount);
		return this.amount;
	}

}
