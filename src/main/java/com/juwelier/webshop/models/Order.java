package com.juwelier.webshop.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonMerge;
import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity(name = "order_entity")
public class Order {
    @Id
    @GeneratedValue
    private UUID id;
    @ManyToOne
    private Customer customer;
    @OneToMany
    @JsonManagedReference
    private List<OrderedProduct> products;
    private double totalPrice;
    private String orderStatus = "Processing...";

    public Order() {}

    public Order(Customer customer, List<OrderedProduct> products, double totalPrice, String orderStatus) {
        this.customer = customer;
        this.products = products;
        this.totalPrice = totalPrice;
        this.orderStatus = orderStatus;
    }

    public Order(Customer customer, double totalPrice, String orderStatus) {
        this.customer = customer;
        this.totalPrice = totalPrice;
        this.orderStatus = orderStatus;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<OrderedProduct> getProducts() {
        return products;
    }

    public void setProducts(List<OrderedProduct> products) {
        this.products = products;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }
}
