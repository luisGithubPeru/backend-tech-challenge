/**
 * 
 */
package com.talently.challenge.model;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

/**
 * @author Beto
 *
 */
class ProductTest {

	Product product = null;

	@Test
	void createVoucher() {
		product = new Product(ProductCode.VOUCHER);
		assertEquals(product.getCode(), ProductCode.VOUCHER);
	}

	@Test
	void createTshirt() {
		product = new Product(ProductCode.TSHIRT);
		assertEquals(product.getName(), "Cabify T-Shirt");
	}

	@Test
	void createMug() {
		product = new Product(ProductCode.MUG);
		assertEquals(product.getPrice().toString(), new BigDecimal(7.5).toString());
	}

}
