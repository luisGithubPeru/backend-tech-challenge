package com.talently.challenge.controller;

import java.net.URI;
import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.talently.challenge.dto.BasketDTO;
import com.talently.challenge.mapper.BasketMapper;
import com.talently.challenge.model.Basket;
import com.talently.challenge.model.ProductCode;
import com.talently.challenge.repository.BasketRepository;

@RestController
@RequestMapping("v1/basket")
public class BasketController {

	private BasketRepository repository;

	public BasketController(BasketRepository repository) {
		this.repository = repository;
	}

	/**
	 * POST a Basket
	 * 
	 * @return
	 */
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public ResponseEntity<?> postBasket() {
		Basket basket = repository.save(new Basket());
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").build(basket.getId());
		return ResponseEntity.created(location).build();
	}

	/**
	 * GET a Basket by ID
	 * 
	 * @param basketId
	 * @return
	 */
	@GetMapping("{basketId}")
	public ResponseEntity<BasketDTO> getBasket(@PathVariable Long basketId) {
		Basket basket = repository.findById(basketId)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Basket not found"));
		BasketDTO dto = BasketMapper.basketToDto(basket);
		return ResponseEntity.ok(dto);
	}

	/**
	 * POST a Product (by code) into a Basket (by ID)
	 * 
	 * @param basketID
	 * @param productCode
	 * @return
	 */
	@PostMapping("{basketID}/products")
	public ResponseEntity<?> postBasketProduct(@PathVariable Long basketID, @RequestParam ProductCode productCode) {
		try {
			Basket basket = repository.findById(basketID).get();
			basket.addProduct(productCode);
			repository.save(basket);
			return ResponseEntity.ok().build();
		} catch (NoSuchElementException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Basket not found");
		} catch (NullPointerException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
		}
	}

	/**
	 * GET a Basket's amount
	 * 
	 * @return
	 */
	@GetMapping("{basketID}/amount")
	public ResponseEntity<BasketDTO> getAmount(@PathVariable Long basketID) {
		Basket basket = repository.findById(basketID)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Basket not found"));
		BasketDTO dto = BasketMapper.basketToAmount(basket);
		return ResponseEntity.ok(dto);
	}

	/**
	 * Delete a resource
	 * 
	 * @param basketId
	 * @return
	 */
	@DeleteMapping("{basketId}")
	public String deleteMethodName(@PathVariable Long basketId) {
		repository.deleteById(basketId);
		return "Success";
	}

}