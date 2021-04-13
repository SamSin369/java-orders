package com.example.javaordersself.repositories;

import com.example.javaordersself.models.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, Long> {
}
