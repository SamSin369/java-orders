package com.example.javaordersself.services;

import com.example.javaordersself.models.Order;

public interface OrderServices {
    Order findOrderById(long id);
}
