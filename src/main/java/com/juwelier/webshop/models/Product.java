package com.juwelier.webshop.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;

@Entity(name = "product")
public class Product {
    @Id
    @GeneratedValue
    private long id;
    private String name;
    private String imagePath;
    private String description;

    @OneToMany(mappedBy = "product")
    @JsonManagedReference
    private List<ProductProperties> productProperties;
    private String brand;
    @ManyToOne
    private Category category;


    public Product() {}

    public Product(String name, String imagePath, String description, List<ProductProperties> productProperties, String brand, Category category) {
        this.name = name;
        this.imagePath = imagePath;
        this.description = description;
        this.productProperties = productProperties;
        this.brand = brand;
        this.category = category;
    }

    public Product(String name, String imagePath, String description, String brand, Category category) {
        this.name = name;
        this.imagePath = imagePath;
        this.description = description;
        this.brand = brand;
        this.category = category;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<ProductProperties> getProductProperties() {
        return productProperties;
    }

    public void setProductProperties(List<ProductProperties> productProperties) {
        this.productProperties = productProperties;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
