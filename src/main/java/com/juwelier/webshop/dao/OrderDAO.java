package com.juwelier.webshop.dao;

import com.juwelier.webshop.dto.OrderDTO;
import com.juwelier.webshop.models.*;
import com.juwelier.webshop.services.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class OrderDAO {
    private OrderRepository orderRepository;
    private CustomerRepository customerRepository;
    private CustomerService customerService;
    private OrderedProductRepository orderedProductRepository;
    private ProductRepository productRepository;

    public OrderDAO(OrderRepository orderRepository, CustomerRepository customerRepository, CustomerService customerService, OrderedProductRepository orderedProductRepository, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
        this.customerService = customerService;
        this.orderedProductRepository = orderedProductRepository;
        this.productRepository = productRepository;
    }

    public List<Order> getOrdersByCustomer(Customer customer) {
        List<Order> orders = this.orderRepository.findAllByCustomer(customer);
        for (Order order : orders) {
            System.out.println(this.orderedProductRepository.findAllByOrder(order));
            order.setProducts(this.orderedProductRepository.findAllByOrder(order));
        }
        return orders;
    }

    public void placeOrder(Customer customer, OrderDTO orderDTO) {
        Order newOrder = new Order(
                customer,
                orderDTO.totalPrice,
                "Succes"
        );
        this.orderRepository.save(newOrder);

        for (OrderedProduct orderedProduct : orderDTO.products) {
            orderedProduct.setOrder(newOrder);
            OrderedProduct orderedProduct1 = new OrderedProduct();

            orderedProduct1.setOrder(newOrder);
            orderedProduct1.setBrand(orderedProduct.getBrand());
            orderedProduct1.setName(orderedProduct.getName());
            orderedProduct1.setPrice(orderedProduct.getPrice());
            orderedProduct1.setColor(orderedProduct.getColor());
            orderedProduct1.setMaterial(orderedProduct.getMaterial());
            orderedProduct1.setSize(orderedProduct.getSize());
            orderedProduct1.setImagePath(orderedProduct.getImagePath());
            Optional<Product> oproduct = this.productRepository.findById(orderedProduct.getId());
            if (oproduct.isPresent()) {
                Product product = oproduct.get();
                List<ProductProperties> productProperties = product.getProductProperties();
                for (ProductProperties productProperty : productProperties) {
                    if (productProperty.getColor().equals(orderedProduct.getColor()) &&
                            productProperty.getMaterial().equals(orderedProduct.getMaterial()) &&
                            productProperty.getSize().equals(orderedProduct.getSize())) {
                        productProperty.setStock(productProperty.getStock() - 1);
                    }
                }

            }

            this.orderedProductRepository.save(orderedProduct1);
        }
    }

    public void updateOrderById(UUID orderId, OrderDTO orderDTO) {
        Optional<Order> optionalOrder = this.orderRepository.findById(orderId);
        if (optionalOrder.isPresent()) {
            Order updatedOrder = optionalOrder.get();
            updatedOrder.setProducts(orderDTO.products);
            updatedOrder.setTotalPrice(orderDTO.totalPrice);
            this.orderRepository.save(updatedOrder);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Failed to update order: Order with ID: '" + orderId + "' does not exist.");
        }
    }

    public void completeOrder(UUID orderId) {
        Optional<Order> optionalOrder = this.orderRepository.findById(orderId);
        if (optionalOrder.isPresent()) {
            Order completedOrder = optionalOrder.get();
            completedOrder.setOrderStatus("Completed");
            this.orderRepository.save(completedOrder);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Failed to complete order: Order with ID: '" + orderId + "'does not exist.");
        }
    }

    public void deleteOrderById(UUID orderId) {
        Optional<Order> optionalOrder = this.orderRepository.findById(orderId);
        if (optionalOrder.isPresent()) {
            this.orderRepository.delete(optionalOrder.get());
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Failed to delete order: Order with ID: '" + orderId + "' does not exist.");
        }
    }

    public List<Order> getAllOrders() {
        return this.orderRepository.findAll();
    }
}
