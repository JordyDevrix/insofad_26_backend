package com.juwelier.webshop.controllers;

import com.juwelier.webshop.dao.ProductDAO;
import com.juwelier.webshop.dto.ProductDTO;
import com.juwelier.webshop.dto.ProductPropertiesDTO;
import com.juwelier.webshop.models.Product;
import com.juwelier.webshop.utils.Seeder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:4200", "http://s1151166.student.inf-hsleiden.nl:11166"})
@RequestMapping("/products")
public class ProductController {
    private ProductDAO productDAO;

    public ProductController(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts(){
        return ResponseEntity.ok(this.productDAO.getAllProducts());
    }

    @GetMapping("/{productId}")
    public ResponseEntity<Product> getProduct(@PathVariable long productId) {
        return ResponseEntity.ok(this.productDAO.getProductById(productId));
    }

    @PostMapping
    public ResponseEntity<String> createProduct(@RequestBody ProductDTO productDTO){
        this.productDAO.createProduct(productDTO);
        return ResponseEntity.ok("Product was successfully created.");
    }

    @PostMapping("/properties/{productId}")
    public ResponseEntity<String> createVariant(@PathVariable long productId, @RequestBody ProductPropertiesDTO productPropertiesDTO){
        this.productDAO.createProductProperties(productId, productPropertiesDTO);
        return ResponseEntity.ok("Product was successfully created.");
    }

    @PutMapping("/{productId}")
    public ResponseEntity<String> updateProduct(@PathVariable long productId, @RequestBody ProductDTO productDTO){
        this.productDAO.updateProductById(productId, productDTO);
        return ResponseEntity.ok("Product was successfully updated.");
    }

    @PutMapping("properties/{propertyId}")
    public ResponseEntity<String> buyStock(@PathVariable long propertyId, @RequestBody ProductPropertiesDTO productPropertiesDTO){
        System.out.println(propertyId);
        this.productDAO.buyStockById(propertyId);
        return ResponseEntity.ok("Product was bought");
    }

    @PutMapping("properties/{propertyId}/{amount}")
    public ResponseEntity<String> buyStockAmount(@PathVariable long propertyId, @RequestBody ProductPropertiesDTO productPropertiesDTO, @PathVariable long amount){
        System.out.println(propertyId);
        this.productDAO.buyStockAmountById(propertyId, amount);
        return ResponseEntity.ok("Product was bought");
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<String> deleteProduct(@PathVariable long productId){
        this.productDAO.deleteProductById(productId);
        return ResponseEntity.ok("Product was successfully deleted.");
    }

    @DeleteMapping("/variant/{variantId}")
    public ResponseEntity<String> deleteVariant(@PathVariable long variantId){
        this.productDAO.deleteVariantById(variantId);
        return ResponseEntity.ok("Product was successfully deleted.");
    }
}
