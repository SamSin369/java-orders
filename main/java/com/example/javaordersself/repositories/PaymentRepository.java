package com.example.javaordersself.repositories;

import com.example.javaordersself.models.Payment;
import org.springframework.data.repository.CrudRepository;

public interface PaymentRepository extends CrudRepository<Payment, Long> {
}
