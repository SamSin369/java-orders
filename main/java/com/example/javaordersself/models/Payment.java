package com.example.javaordersself.models;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "payments")
public class Payment {

    @Id
    @GeneratedValue
    private long paymentid;

    @Column(unique = true, nullable = false)
    private String type;

    @ManyToMany(mappedBy = "payments")
    private Set<Order> ordersPayments = new HashSet<>();


    public Payment() {

    }

    public Payment(java.lang.String type) {

        this.type = type;
    }

    public long getPaymentid() {
        return paymentid;
    }

    public void setPaymentid(long paymentid) {
        this.paymentid = paymentid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
