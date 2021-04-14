package com.example.javaordersself.controllers;

import com.example.javaordersself.models.Order;
import com.example.javaordersself.services.OrderServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private OrderServices orderServices;
    @GetMapping(value = "/order/{orderid}", produces = "application/json")
    public ResponseEntity<?> listOrderById(@PathVariable long orderid)
    {
        Order rtnRest = orderServices.findOrderById(orderid);
        return new ResponseEntity<>(rtnRest, HttpStatus.OK);
    }
}
