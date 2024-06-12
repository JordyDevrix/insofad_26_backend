package com.juwelier.webshop.models;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
public class OrderedProduct {

    @Id
    @GeneratedValue
    private long id;
    private String name;
    private String imagePath;
    private String brand;
    @JsonAlias("size")
    private String size;
    private String color;
    private String material;
    private double price;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JsonBackReference
    private Order order;

    public OrderedProduct(String name, String imagePath, String brand, String size, String color, String material, double price, Order order) {
        this.name = name;
        this.imagePath = imagePath;
        this.brand = brand;
        this.size = size;
        this.color = color;
        this.material = material;
        this.price = price;
        this.order = order;
    }

    public OrderedProduct() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
