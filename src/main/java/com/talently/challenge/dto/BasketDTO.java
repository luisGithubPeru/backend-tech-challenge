package com.talently.challenge.dto;

import java.math.BigDecimal;
import java.util.List;
import com.talently.challenge.model.Product;
import lombok.Data;

@Data
public class BasketDTO {
	private long id;
	private BigDecimal amount;
	private List<Product> products;
}
