package com.example.javaordersself.controllers;

import com.example.javaordersself.models.Customer;
import com.example.javaordersself.services.CustomerServices;
import com.example.javaordersself.views.OrderCount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerServices customerServices;

    @GetMapping(value = "/all", produces = "application/json")
    public ResponseEntity<?> listAllCustomers() {

        List<Customer> rtnList = customerServices.findAllCustomers();
        return new ResponseEntity<>(rtnList, HttpStatus.OK);
    }

    @GetMapping(value = "/customer/{restid}", produces = "application/json")
    public ResponseEntity<?> findCustomerById(@PathVariable long restid) {
        Customer rtnRest = customerServices.findCustomerById(restid);
        return new ResponseEntity<>(rtnRest, HttpStatus.OK);
    }

    @GetMapping(value = "/namelike/{likename}", produces = "application/json")
    public ResponseEntity<?> findCustomersByNameLike(@PathVariable String likename) {
        List<Customer> rtnList = customerServices.findByCustnameLike(likename);
        return new ResponseEntity<>(rtnList, HttpStatus.OK);
    }

    @GetMapping(value = "/orders/count", produces = "application/json")
    public ResponseEntity<?> findCustomerOrdersPlaced() {
        List<OrderCount> rtnList = customerServices.findCustomerOrdersCount();
        return new ResponseEntity<>(rtnList, HttpStatus.OK);
    }

    @PostMapping(value = "/customer", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> addNewCustomer(
            @Validated
            @RequestBody Customer newCustomer
            ) {
        newCustomer.setCustcode(0);
        newCustomer = customerServices.save(newCustomer);
        HttpHeaders responseHeaders = new HttpHeaders();
        URI newCustomerURI = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{restaurantid}")
                .buildAndExpand(newCustomer.getCustcode())
                .toUri();
        responseHeaders.setLocation(newCustomerURI);
        return new ResponseEntity<>(newCustomer, responseHeaders, HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/customer/{custid}")
    public ResponseEntity<?> deleteCustomerById(@PathVariable long custid) {
        customerServices.delete(custid);
        return new ResponseEntity<>(HttpStatus.GONE);
    }

    @PatchMapping(value = "/customer/{custid}", produces = "application/json", consumes = "application/json")
    public ResponseEntity<?> patchCustomerById(@RequestBody Customer updateCustomer,
                                               @PathVariable long custid) {
        updateCustomer = customerServices.patch(updateCustomer, custid);
        return new ResponseEntity<>(updateCustomer, HttpStatus.ACCEPTED);
    }
    @PutMapping(value = "/customer/{custid}", produces = "application/json", consumes = "application/json")
    public ResponseEntity<?> putCustomerById(@RequestBody Customer updateCustomer,
                                             @PathVariable long custid)
    {
        updateCustomer.setCustcode(custid);
        updateCustomer = customerServices.save(updateCustomer);
        return new ResponseEntity<>(updateCustomer, HttpStatus.OK);
    }


}
