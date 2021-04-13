package com.example.javaordersself.repositories;

import com.example.javaordersself.models.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Long> {
}
