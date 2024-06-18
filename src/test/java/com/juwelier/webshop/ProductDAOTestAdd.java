package com.juwelier.webshop.dao;

import com.juwelier.webshop.dto.CategoryDTO;
import com.juwelier.webshop.dto.ProductDTO;
import com.juwelier.webshop.models.Category;
import com.juwelier.webshop.models.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class ProductDAOTestAdd {

    private ProductDAO productDAO;
    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;

    @BeforeEach
    public void setUp() {
        productRepository = mock(ProductRepository.class);
        categoryRepository = mock(CategoryRepository.class);
        productDAO = new ProductDAO(productRepository, null, categoryRepository); // Passing null for ProductPropertiesRepository
    }

    @Test
    public void testCreateProduct() {
        // Arrange
        long categoryId = 0;
        ProductDTO productDTO = new ProductDTO("Test Product", "test/image/path", "Test Description", "Test Brand", categoryId);

        Category category = new Category();
        category.setId(categoryId);

        // Mock the behavior of categoryRepository.findById()
        when(categoryRepository.findById(categoryId)).thenReturn(Optional.of(category));

        // Act
        productDAO.createProduct(productDTO);

        // Assert
        verify(productRepository, times(1)).save(any(Product.class)); // Verify that productRepository.save() is called once
    }

    @Test
    public void testCreateProduct_InvalidCategory() {
        // Arrange
        long invalidCategoryId = 100L; // Assuming an invalid category ID
        ProductDTO productDTO = new ProductDTO("Test Product", "test/image/path", "Test Description", "Test Brand", invalidCategoryId);

        // Mock the behavior of categoryRepository.findById() to return Optional.empty()
        when(categoryRepository.findById(invalidCategoryId)).thenReturn(Optional.empty());

        // Act & Assert
        // Verify that calling createProduct with an invalid category ID throws a ResponseStatusException
        assertThrows(ResponseStatusException.class, () -> productDAO.createProduct(productDTO));
        verify(productRepository, never()).save(any(Product.class)); // Verify that productRepository.save() is never called
    }
}
