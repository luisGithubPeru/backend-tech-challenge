package com.talently.challenge.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.talently.challenge.model.Basket;

@Repository
public interface BasketRepository extends JpaRepository<Basket, Long> {

}
