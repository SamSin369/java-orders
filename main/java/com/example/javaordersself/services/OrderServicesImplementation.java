package com.example.javaordersself.services;

import com.example.javaordersself.models.Order;
import com.example.javaordersself.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

@Transactional
@Service(value = "restaurantService")
public class OrderServicesImplementation implements OrderServices{

    @Autowired
    private OrderRepository restrepos;


    @Override
    public Order findOrderById(long id) {
        return restrepos.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Restaurant " + id + " Not Found"));
    }

}
