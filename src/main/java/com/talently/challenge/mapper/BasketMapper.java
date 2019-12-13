package com.talently.challenge.mapper;

import com.talently.challenge.dto.BasketDTO;
import com.talently.challenge.model.Basket;

public class BasketMapper {

	public static BasketDTO basketToDto(Basket basket) {
		BasketDTO dto = new BasketDTO();
		dto.setId(basket.getId());
		dto.setAmount(basket.getAmount());
		dto.setProducts(basket.getProducts());
		return dto;
	}

	public static BasketDTO basketToAmount(Basket basket) {
		BasketDTO dto = new BasketDTO();
		dto.setId(basket.getId());
		dto.setAmount(basket.getAmount());
		return dto;
	}
}
