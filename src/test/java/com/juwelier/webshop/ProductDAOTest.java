package com.juwelier.webshop.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.Optional;

import com.juwelier.webshop.models.ProductProperties;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ProductDAOTest {

    private ProductDAO productDAO;
    private ProductPropertiesRepository productPropertiesRepository;

    @BeforeEach
    public void setUp() {
        productPropertiesRepository = mock(ProductPropertiesRepository.class);
        productDAO = new ProductDAO(null, productPropertiesRepository, null); // Passing null for other dependencies
    }

    @Test
    public void testBuyStockById() {
        // Arrange
        long propertyId = 1L;
        ProductProperties productProperties = new ProductProperties();
        productProperties.setId(propertyId);
        productProperties.setStock(10);

        // Mock the behavior of productPropertiesRepository.findById()
        when(productPropertiesRepository.findById(propertyId)).thenReturn(Optional.of(productProperties));

        // Act
        productDAO.buyStockById(propertyId);

        // Assert
        verify(productPropertiesRepository, times(1)).save(productProperties);
        assertEquals(11, productProperties.getStock());
    }
}
