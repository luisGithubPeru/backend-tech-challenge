/**
 * 
 */
package com.talently.challenge.model;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author Beto
 *
 */
class CartTest {

	/**
	 * @throws java.lang.Exception
	 */
	Cart cart;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		cart = new Cart();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
		cart = null;
	}

	/**
	 * Test method for {@link com.talently.challenge.model.Cart#Cart()}.
	 */
	@Test
	void testCart() {
		assertEquals(cart.getClass(), Cart.class);
	}

	/**
	 * Test method for {@link com.talently.challenge.model.Cart#getNumberOfItems()}.
	 */
	@Test
	void testGetNumberOfItems() {
		assertEquals(cart.getNumberOfItems(), 0);
	}

	/**
	 * Test method for
	 * {@link com.talently.challenge.model.Cart#addItem(com.talently.challenge.model.Product)}.
	 */
	@Test
	void testAddItem() {
		cart.addItem(new Product(ProductCode.VOUCHER));
		assertEquals(cart.getNumberOfItems(), 1);
	}

	/**
	 * Test method for {@link com.talently.challenge.model.Cart#getAmount()}.
	 */
	@Test
	void testGetAmountVoucher() {
		for (int i = 0; i < 5; i++) {
			cart.addItem(new Product(ProductCode.VOUCHER));
		}
		BigDecimal regularAmount = new BigDecimal(cart.getNumberOfItems());
		regularAmount = regularAmount.multiply(ProductCode.VOUCHER.getPrice());
		assertNotEquals(regularAmount, cart.getAmount());
		
	}

	@Test
	void testGetAmountTshirt() {
		for (int i = 0; i < 5; i++) {
			cart.addItem(new Product(ProductCode.TSHIRT));
		}
		
		BigDecimal regularAmount = new BigDecimal(cart.getNumberOfItems());
		regularAmount = regularAmount.multiply(ProductCode.TSHIRT.getPrice());
		assertNotEquals(regularAmount, cart.getAmount());
	}

	@Test
	void testGetAmountMug() {
		for (int i = 0; i < 5; i++) {
			cart.addItem(new Product(ProductCode.MUG));
		}

		BigDecimal regularAmount = new BigDecimal(cart.getNumberOfItems());
		regularAmount =  regularAmount.multiply(ProductCode.MUG.getPrice());
		assertEquals(regularAmount, cart.getAmount());
	}

}
