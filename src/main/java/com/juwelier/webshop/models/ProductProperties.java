package com.juwelier.webshop.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;

@Entity(name = "productproperties")
public class ProductProperties {

    @Id
    @GeneratedValue
    private long id;
    private String size;

    private String color;

    private String material;

    private long stock;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JsonBackReference
    private Product product;
    private double price;

    public ProductProperties() {}

public ProductProperties(String size, String color, String material, Product product, double price, long stock) {
        this.size = size;
        this.color = color;
        this.material = material;
        this.product = product;
        this.price = price;
        this.stock = stock;
    }

    public ProductProperties(String size, String color, String material, double price, long stock) {
        this.size = size;
        this.color = color;
        this.material = material;
        this.price = price;
        this.stock = stock;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public long getStock() {
        return stock;
    }

    public void setStock(long stock) {
        this.stock = stock;
    }
}
