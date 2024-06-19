package com.juwelier.webshop.dao;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;
import java.util.UUID;
import java.util.List;
import java.util.ArrayList;

import com.juwelier.webshop.dto.OrderDTO;
import com.juwelier.webshop.models.*;
import com.juwelier.webshop.services.CustomerService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class OrderDAOTest {

    private OrderDAO orderDAO;
    private OrderRepository orderRepository;
    private CustomerRepository customerRepository;
    private CustomerService customerService;
    private OrderedProductRepository orderedProductRepository;
    private ProductRepository productRepository;

    private Customer customer;
    private OrderDTO orderDTO;
    private OrderedProduct orderedProduct;
    private Product product;
    private ProductProperties productProperty;

    @BeforeEach
    public void setUp() {
        orderRepository = mock(OrderRepository.class);
        customerRepository = mock(CustomerRepository.class);
        customerService = mock(CustomerService.class);
        orderedProductRepository = mock(OrderedProductRepository.class);
        productRepository = mock(ProductRepository.class);

        orderDAO = new OrderDAO(orderRepository, customerRepository, customerService, orderedProductRepository, productRepository);

        customer = new Customer();
        customer.setId(UUID.randomUUID());

        orderedProduct = new OrderedProduct();
        orderedProduct.setBrand("Brand");
        orderedProduct.setName("Product Name");
        orderedProduct.setPrice(100.0);
        orderedProduct.setColor("Red");
        orderedProduct.setMaterial("Gold");
        orderedProduct.setSize("Medium");
        orderedProduct.setImagePath("path/to/image");

        List<OrderedProduct> orderedProducts = new ArrayList<>();
        orderedProducts.add(orderedProduct);

        orderDTO = new OrderDTO(orderedProducts, 100.0);

        product = new Product();
        product.setId(orderedProduct.getId());

        productProperty = new ProductProperties();
        productProperty.setColor("Red");
        productProperty.setMaterial("Gold");
        productProperty.setSize("Medium");
        productProperty.setStock(10);

        List<ProductProperties> productProperties = new ArrayList<>();
        productProperties.add(productProperty);

        product.setProductProperties(productProperties);
    }

    @Test
    public void testPlaceOrder() {
        // Arrange
        when(productRepository.findById(orderedProduct.getId())).thenReturn(Optional.of(product));

        // Act
        orderDAO.placeOrder(customer, orderDTO);

        // Assert
        verify(orderRepository, times(1)).save(any(Order.class));
        verify(orderedProductRepository, times(1)).save(any(OrderedProduct.class));
        assertEquals(9, productProperty.getStock());
    }
}
