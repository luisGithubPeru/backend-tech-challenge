/**
 * 
 */
package com.talently.challenge.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;

import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.Mock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.talently.challenge.dto.BasketDTO;
import com.talently.challenge.model.Basket;
import com.talently.challenge.model.ProductCode;
import com.talently.challenge.repository.BasketRepository;

/**
 * @author Beto
 *
 */
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class BasketControllerTest {

	private static final Logger LOG = LoggerFactory.getLogger(BasketControllerTest.class);

	BasketController controller;
	Basket basket;

	@Mock
	private BasketRepository repository;

	@BeforeAll
	static void testName() throws Exception {
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(new MockHttpServletRequest()));
	}

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		controller = new BasketController(repository);
		basket = new Basket();
		basket.setId(1L);
		basket.addProduct(ProductCode.MUG);
		basket.addProduct(ProductCode.TSHIRT);
		basket.addProduct(ProductCode.VOUCHER);
		when(repository.save(any(Basket.class))).thenReturn(new Basket());
		when(repository.findById(1L)).thenReturn(Optional.of(basket));
	}

	/**
	 * Test method for
	 * {@link com.talently.challenge.controller.BasketController#postBasket()}.
	 */
	@Test
	@Order(1)
	void postBasket() {
		ResponseEntity<?> response = controller.postBasket();
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
		assertThat(response.getHeaders().getLocation()).isNotNull();
		LOG.debug(response.toString());
	}

	@Test
	@Order(2)
	void getBasket() {
		ResponseEntity<BasketDTO> response = controller.getBasket(Long.parseLong("1"));
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(response.getBody().getId()).isEqualByComparingTo(1L);
		LOG.debug(response.toString());
	}

	/**
	 * Test method for
	 * {@link com.talently.challenge.controller.BasketController#postBasketProduct(long, com.talently.challenge.model.ProductCode)}.
	 */
	@Test
	@Order(3)
	void addMugProductToBasket() {
		ResponseEntity<?> response;

		response = controller.postBasketProduct(1L, ProductCode.MUG);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		LOG.debug(response.toString());

		response = controller.postBasketProduct(1L, ProductCode.TSHIRT);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		LOG.debug(response.toString());

		response = controller.postBasketProduct(1L, ProductCode.VOUCHER);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		LOG.debug(response.toString());

		response = controller.postBasketProduct(1L, null);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
		LOG.debug(response.toString());

		response = controller.postBasketProduct(null, null);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
		LOG.debug(response.toString());
	}

	/**
	 * Test method for
	 * {@link com.talently.challenge.controller.BasketController#getAmount(long)}.
	 */
	@Test
	@Order(4)
	void testGetAmount() {
		basket = new Basket();
		basket.addProduct(ProductCode.MUG);
		basket.addProduct(ProductCode.TSHIRT);
		basket.addProduct(ProductCode.VOUCHER);
		when(repository.findById(1L)).thenReturn(Optional.of(basket));
		ResponseEntity<BasketDTO> response = controller.getAmount(1L);
		LOG.debug(response.toString());
		assertThat(response.getBody().getAmount()).isEqualByComparingTo("32.50");
	}

	@Test
	@Order(4)
	void testGetAmount2() {
		basket = new Basket();
		basket.addProduct(ProductCode.VOUCHER);
		basket.addProduct(ProductCode.TSHIRT);
		basket.addProduct(ProductCode.VOUCHER);
		when(repository.findById(1L)).thenReturn(Optional.of(basket));
		ResponseEntity<BasketDTO> response = controller.getAmount(1L);
		LOG.debug(response.toString());
		assertThat(response.getBody().getAmount()).isEqualByComparingTo("25.00");
	}

	@Test
	@Order(4)
	void testGetAmount3() {
		basket = new Basket();
		basket.addProduct(ProductCode.TSHIRT);
		basket.addProduct(ProductCode.TSHIRT);
		basket.addProduct(ProductCode.TSHIRT);
		basket.addProduct(ProductCode.VOUCHER);
		basket.addProduct(ProductCode.TSHIRT);
		when(repository.findById(1L)).thenReturn(Optional.of(basket));
		ResponseEntity<BasketDTO> response = controller.getAmount(1L);
		LOG.debug(response.toString());
		assertThat(response.getBody().getAmount()).isEqualByComparingTo("81.00");
	}

	@Test
	@Order(4)
	void testGetAmount4() {
		basket = new Basket();
		basket.addProduct(ProductCode.VOUCHER);
		basket.addProduct(ProductCode.TSHIRT);
		basket.addProduct(ProductCode.VOUCHER);
		basket.addProduct(ProductCode.VOUCHER);
		basket.addProduct(ProductCode.MUG);
		basket.addProduct(ProductCode.TSHIRT);
		basket.addProduct(ProductCode.TSHIRT);
		when(repository.findById(1L)).thenReturn(Optional.of(basket));
		ResponseEntity<BasketDTO> response = controller.getAmount(1L);
		LOG.debug(response.toString());
		assertThat(response.getBody().getAmount()).isEqualByComparingTo("74.50");
	}
	
	@Test
	@Order(5)
	void deleteBasket() throws Exception {
		String response = controller.deleteMethodName(1L);
		assertThat(response).isEqualTo("Success");
	}
}
