package com.example.javaordersself.repositories;

import com.example.javaordersself.models.Customer;
import com.example.javaordersself.views.OrderCount;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CustomerRepository extends CrudRepository<Customer, Long> {


List<Customer> findByCustnameContainingIgnoringCase(String likename);


    @Query(value = "SELECT c.custname,  count(o.custcode) count " +
            "FROM CUSTOMERS c " +
            "JOIN ORDERS o " +
            "ON  c.custcode = o.custcode " +
            "GROUP BY c.custname",
    nativeQuery = true
    )
    List<OrderCount> findCustomerOrdersCount();
}
