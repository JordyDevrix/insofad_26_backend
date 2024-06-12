package com.juwelier.webshop.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.juwelier.webshop.models.ProductProperties;

import java.util.List;

public class ProductDTO {
    public String name;
    public String imagePath;
    public String description;

    public List<ProductProperties>  productProperties;
    public String brand;
    @JsonAlias("category_id")
    public long categoryId;

    public ProductDTO(String name, String imagePath, String description, List<ProductProperties> productProperties, String brand, long categoryId) {
        this.name = name;
        this.imagePath = imagePath;
        this.description = description;
        this.productProperties = productProperties;
        this.brand = brand;
        this.categoryId = categoryId;
    }

    public ProductDTO(String testProduct, String imagePath, String testDescription, String testBrand, long categoryId) {
    }
}
