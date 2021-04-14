package com.example.javaordersself.controllers;

import com.example.javaordersself.models.Customer;
import com.example.javaordersself.services.CustomerServices;
import com.example.javaordersself.views.OrderCount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerServices customerServices;
    @GetMapping(value = "/all", produces = "application/json")
    public ResponseEntity<?> listAllCustomers()
    {

        List<Customer> rtnList = customerServices.findAllCustomers();
        return new ResponseEntity<>(rtnList, HttpStatus.OK);
    }

    @GetMapping(value = "/customer/{restid}", produces = "application/json")
    public ResponseEntity<?> findCustomerById(@PathVariable long restid)
    {
        Customer rtnRest = customerServices.findCustomerById(restid);
        return new ResponseEntity<>(rtnRest, HttpStatus.OK);
    }
    @GetMapping(value = "/namelike/{likename}", produces = "application/json")
    public ResponseEntity<?> findCustomersByNameLike(@PathVariable String likename)
    {
        List<Customer> rtnList = customerServices.findByCustnameLike(likename);
                return new ResponseEntity<>(rtnList, HttpStatus.OK);
    }
    @GetMapping(value = "/orders/count", produces = "application/json")
    public ResponseEntity<?> findCustomerOrdersPlaced()
    {
        List<OrderCount> rtnList = customerServices.findCustomerOrdersCount();
        return new ResponseEntity<>(rtnList, HttpStatus.OK);
    }


}
