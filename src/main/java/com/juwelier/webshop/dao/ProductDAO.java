package com.juwelier.webshop.dao;

import com.juwelier.webshop.dto.ProductDTO;
import com.juwelier.webshop.dto.ProductPropertiesDTO;
import com.juwelier.webshop.models.Category;
import com.juwelier.webshop.models.Product;
import com.juwelier.webshop.models.ProductProperties;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Component
public class ProductDAO {
    private ProductRepository productRepository;
private ProductPropertiesRepository productPropertiesRepository;
    private CategoryRepository categoryRepository;

    public ProductDAO(ProductRepository productRepository, ProductPropertiesRepository productPropertiesRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.productPropertiesRepository = productPropertiesRepository;
        this.categoryRepository = categoryRepository;
    }

    public List<Product> getAllProducts(){
        return this.productRepository.findAll();
    }

    public Product getProductById(long productId) {
        Optional<Product> optionalProduct = this.productRepository.findById(productId);
        if (optionalProduct.isPresent()) {
            return optionalProduct.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Failed to get product: Product with ID '" + productId + "' does not exist.");
        }
    }

    public void createProduct(ProductDTO productDTO){
        Optional<Category> categoryOptional = this.categoryRepository.findById(productDTO.categoryId);
        if (categoryOptional.isPresent()){
            Category category = categoryOptional.get();
            Product newProduct = new Product(
                    productDTO.name,
                    productDTO.imagePath,
                    productDTO.description,
                    productDTO.brand,
                    category
            );
            this.productRepository.save(newProduct);
        } else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Failed to create product: Category with ID '" + productDTO.categoryId + "' does not exist.");
        }
    }

    public void updateProductById(long productId, ProductDTO productDTO){
        Optional<Product> productOptional = this.productRepository.findById(productId);
        Optional<Category> categoryOptional = this.categoryRepository.findById(productDTO.categoryId);
        if (productOptional.isPresent()){
            Product updatedProduct = productOptional.get();
            if (categoryOptional.isPresent()){
                Category category = categoryOptional.get();
                updatedProduct.setName(productDTO.name);
                updatedProduct.setImagePath(productDTO.imagePath);
                updatedProduct.setDescription(productDTO.description);
//                updatedProduct.setPrice(productDTO.price);
                updatedProduct.setCategory(category);
                this.productRepository.save(updatedProduct);
            } else{
                throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Failed to update product: Category with ID '" + productDTO.categoryId + "' does not exist.");
            }
        } else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Failed to update product: Product with ID '" + productId + "' does not exist.");
        }
    }

    public void createProductProperties(long productId, ProductPropertiesDTO productPropertiesDTO) {
        Optional<Product> productOptional = this.productRepository.findById(productId);
        if (productOptional.isPresent()){
            ProductProperties newProductProperties = new ProductProperties(
                    productPropertiesDTO.size,
                    productPropertiesDTO.color,
                    productPropertiesDTO.material,
                    productPropertiesDTO.price,
                    0
            );
            newProductProperties.setProduct(productOptional.get());
            this.productPropertiesRepository.save(newProductProperties);
        } else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Failed to create product properties: Product with ID '" + productId + "' does not exist.");
        }
    }

    public void deleteProductById(long productId){
        Optional<Product> productOptional = this.productRepository.findById(productId);
        Optional<List<ProductProperties>> productPropertiesOptional = this.productPropertiesRepository.findByProductId(productId);
        if (productPropertiesOptional.isPresent()){
            List<ProductProperties> productProperties = productPropertiesOptional.get();
            this.productPropertiesRepository.deleteAll(productProperties);
        }
        if (productOptional.isPresent()){
            Product deletedProduct = productOptional.get();
            this.productRepository.delete(deletedProduct);
        } else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Failed to delete product: Product with ID '" + productId + "' does not exist.");
        }
    }

    public void deleteVariantById(long variantId) {
        Optional<ProductProperties> productPropertiesOptional = this.productPropertiesRepository.findById(variantId);
        if (productPropertiesOptional.isPresent()){
            ProductProperties deletedProductProperties = productPropertiesOptional.get();
            this.productPropertiesRepository.delete(deletedProductProperties);
        } else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Failed to delete product properties: Product properties with ID '" + variantId + "' does not exist.");
        }
    }

    public void buyStockById(long propertyId) {
        Optional<ProductProperties> productPropertiesOptional = this.productPropertiesRepository.findById(propertyId);
        if (productPropertiesOptional.isPresent()){
            ProductProperties productProperties = productPropertiesOptional.get();
            productProperties.setStock(productProperties.getStock() + 1);
            this.productPropertiesRepository.save(productProperties);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Failed to buy stock: Product with ID '" + propertyId + "' does not exist.");
        }
    }

    public void buyStockAmountById(long propertyId, long amount) {
        Optional<ProductProperties> productPropertiesOptional = this.productPropertiesRepository.findById(propertyId);
        if (productPropertiesOptional.isPresent()){
            ProductProperties productProperties = productPropertiesOptional.get();
            productProperties.setStock(productProperties.getStock() + amount);
            this.productPropertiesRepository.save(productProperties);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Failed to buy stock: Product with ID '" + propertyId + "' does not exist.");
        }
    }
}
