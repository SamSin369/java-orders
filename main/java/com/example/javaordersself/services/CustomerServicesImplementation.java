package com.example.javaordersself.services;

import com.example.javaordersself.models.Customer;
import com.example.javaordersself.repositories.CustomerRepository;
import com.example.javaordersself.views.OrderCount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service(value = "customerService")
public class CustomerServicesImplementation implements CustomerServices{

    @Autowired
    private CustomerRepository customerRepository;

    @Transactional
    @Override
    public Customer save(Customer customer) {return customerRepository.save(customer); }

    @Override
    public List<Customer> findAllCustomers() {
        List<Customer> list = new ArrayList<>();
        customerRepository.findAll()
                .iterator()
                .forEachRemaining((list::add));

        return list;
    }
    @Override
    public Customer findCustomerById(long id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Restaurant " + id + " Not Found"));
    }
    @Override
    public List<Customer> findByCustnameLike(String likename) {
        List<Customer> rtnList = customerRepository.findByCustnameContainingIgnoringCase(likename);
        return rtnList;
    }

    @Override
    public List<OrderCount> findCustomerOrdersCount() {
        List<OrderCount> rtnList = customerRepository.findCustomerOrdersCount();
        return rtnList;
    }
}
