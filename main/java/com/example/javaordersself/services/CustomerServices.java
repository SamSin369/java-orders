package com.example.javaordersself.services;

import com.example.javaordersself.models.Customer;
import com.example.javaordersself.views.OrderCount;

import java.util.List;

public interface CustomerServices {

    Customer save(Customer customer);
    List<Customer> findAllCustomers();

    Customer findCustomerById(long id);
//
    List<Customer> findByCustnameLike(String likename);
//
//    List<Customer> findByNameLike(String subname);

    List<OrderCount> findCustomerOrdersCount();
    void delete(long custid);
    Customer update(Customer customer, long custid);
    Customer patch(Customer customer, long custid);
}
