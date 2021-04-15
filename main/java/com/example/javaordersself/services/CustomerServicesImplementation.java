package com.example.javaordersself.services;

import com.example.javaordersself.models.Customer;
import com.example.javaordersself.models.Order;
import com.example.javaordersself.models.Payment;
import com.example.javaordersself.repositories.CustomerRepository;
import com.example.javaordersself.repositories.PaymentRepository;
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

    @Autowired
    private PaymentRepository paymentRepository;


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
    @Transactional
    @Override
    public void delete(long custid)
    {
        if (customerRepository.findById(custid).isPresent())
        {
            customerRepository.deleteById(custid);
        } else
        {
            throw new EntityNotFoundException("Restaurant " + custid + " Not Found");
        }
    }

    @Override
    public Customer update(Customer customer, long id) {
        return null;
    }

    @Transactional
    @Override
    public Customer save(Customer customer)
    {
        Customer newCustomer = new Customer();
        newCustomer.setCustname(customer.getCustname());
        newCustomer.setCustcity(customer.getCustcity());
        newCustomer.setWorkingarea(customer.getWorkingarea());
        newCustomer.setCustcountry(customer.getCustcountry());
        newCustomer.setGrade(customer.getGrade());
        newCustomer.setOpeningamt(customer.getOpeningamt());
        newCustomer.setReceiveamt(customer.getReceiveamt());
        newCustomer.setPaymentamt(customer.getPaymentamt());
        newCustomer.setOutstandingamt(customer.getOutstandingamt());
        newCustomer.setPhone(customer.getPhone());
        newCustomer.setAgent(customer.getAgent());

        newCustomer.getOrders().clear();
        for (Order o : customer.getOrders())
        {
            Order newOrder = new Order();
            newOrder.setOrdamount(o.getOrdamount());
            newOrder.setOrderdescription(o.getOrderdescription());
            newOrder.setCustomer(newCustomer);
            for (Payment p : o.getPayments())
            {
                Payment newPay = paymentRepository.findById(p.getPaymentid())
                        .orElseThrow(() -> new EntityNotFoundException("Payment " + p.getPaymentid() + " Not Found"));
                newOrder.getPayments().add(newPay);
            }

            newCustomer.getOrders().add(newOrder);
        }


    return customerRepository.save(newCustomer);
    }

    @Transactional
    @Override
    public Customer patch(Customer customer, long custid)
    {
        Customer updatedCustomer = findCustomerById((custid));

        if (customer.getCustname() != null)
        {
            updatedCustomer.setCustname(customer.getCustname());
        }
        if (customer.getCustcity() != null)
        {
            updatedCustomer.setCustcity(customer.getCustcity());
        }
        if (customer.getWorkingarea() != null)
        {
            updatedCustomer.setWorkingarea(customer.getWorkingarea());
        }
        if (customer.getCustcountry() != null)
        {
            updatedCustomer.setCustcountry(customer.getCustcountry());
        }
        if (customer.getGrade() != null)
        {
            updatedCustomer.setGrade(customer.getGrade());
        }
        if (customer.getOpeningamt() != 0)
        {
            updatedCustomer.setOpeningamt(customer.getOpeningamt());
        }
        if (customer.getReceiveamt() != 0)
        {
            updatedCustomer.setReceiveamt(customer.getReceiveamt());
        }
        if (customer.getPaymentamt() != 0)
        {
            updatedCustomer.setPaymentamt(customer.getPaymentamt());
        }
        if (customer.getOutstandingamt() != 0)
        {
            updatedCustomer.setOutstandingamt(customer.getOutstandingamt());
        }
        if (customer.getPhone() != null)
        {
            updatedCustomer.setPhone(customer.getPhone());
        }
        if (customer.getAgent() != null)
        {
            updatedCustomer.setAgent(customer.getAgent());
        }
        if(customer.getOrders().size() > 0)
        {
            updatedCustomer.getOrders()
                .clear();
            for (Order o : customer.getOrders())
            {
                Order newOrder = new Order();
                newOrder.setOrdnum(o.getOrdnum());
                newOrder.setOrderdescription(o.getOrderdescription());
                newOrder.setOrdamount(o.getOrdamount());

                updatedCustomer.getOrders()
                        .add(newOrder);
               for (Payment p : o.getPayments())
               {
                   Payment newPayment = new Payment();
                   newPayment.setType(p.getType());
               }
            }
        }

        return customerRepository.save(updatedCustomer);
    }



}
