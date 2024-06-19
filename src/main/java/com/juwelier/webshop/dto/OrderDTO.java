package com.juwelier.webshop.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.juwelier.webshop.models.OrderedProduct;
import com.juwelier.webshop.models.Product;

import java.util.List;

public class OrderDTO {

    @JsonAlias("products")
    public List<OrderedProduct> products;

    @JsonAlias("totalPrice")
    public double totalPrice;

    public OrderDTO(List<OrderedProduct> products, double totalPrice) {
        this.products = products;
        this.totalPrice = totalPrice;
    }
}
