/**
 * 
 */
package com.talently.challenge.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.math.BigDecimal;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author Beto
 *
 */
class BasketTest {

	Basket cart;

	@BeforeEach
	void setUp() throws Exception {
		cart = new Basket();
	}

	@AfterEach
	void tearDown() throws Exception {
		cart = null;
	}

	/**
	 * Test method for {@link com.talently.challenge.model.Basket#Cart()}.
	 */
	@Test
	void testCart() {
		assertEquals(cart.getClass(), Basket.class);
	}

	/**
	 * Test method for
	 * {@link com.talently.challenge.model.Basket#getProductsSize()}.
	 */
	@Test
	void testGetNumberOfItems() {
		assertEquals(cart.getProductsSize(), 0);
	}

	/**
	 * Test method for
	 * {@link com.talently.challenge.model.Basket#addProduct(com.talently.challenge.model.Product)}.
	 */
	@Test
	void testAddItem() {
		cart.addProduct(ProductCode.VOUCHER);
		assertEquals(cart.getProductsSize(), 1);
	}

	/**
	 * Test method for {@link com.talently.challenge.model.Basket#getAmount()}.
	 */
	@Test
	void testGetAmountVoucher() {
		for (int i = 0; i < 5; i++) {
			cart.addProduct(ProductCode.VOUCHER);
		}
		BigDecimal regularAmount = new BigDecimal(cart.getProductsSize());
		regularAmount = regularAmount.multiply(ProductCode.VOUCHER.getPrice());
		assertNotEquals(regularAmount, cart.getAmount());

	}

	@Test
	void testGetAmountTshirt() {
		for (int i = 0; i < 5; i++) {
			cart.addProduct(ProductCode.TSHIRT);
		}

		BigDecimal regularAmount = new BigDecimal(cart.getProductsSize());
		regularAmount = regularAmount.multiply(ProductCode.TSHIRT.getPrice());
		assertNotEquals(regularAmount, cart.getAmount());
	}

	@Test
	void testGetAmountMug() {
		for (int i = 0; i < 5; i++) {
			cart.addProduct(ProductCode.MUG);
		}

		BigDecimal regularAmount = new BigDecimal(cart.getProductsSize());
		regularAmount = regularAmount.multiply(ProductCode.MUG.getPrice());
		assertEquals(regularAmount, cart.getAmount());
	}

}
