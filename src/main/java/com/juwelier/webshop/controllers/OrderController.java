package com.juwelier.webshop.controllers;

import com.juwelier.webshop.dao.CustomerRepository;
import com.juwelier.webshop.dao.OrderDAO;
import com.juwelier.webshop.dto.OrderDTO;
import com.juwelier.webshop.models.Customer;
import com.juwelier.webshop.models.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin(origins = {"http://localhost:4200", "http://s1151166.student.inf-hsleiden.nl:11166"})
@RequestMapping("/orders")
public class OrderController {
    private OrderDAO orderDAO;

    private CustomerRepository customerRepository;

    public OrderController(OrderDAO orderDAO, CustomerRepository customerRepository) {
        this.orderDAO = orderDAO;
        this.customerRepository = customerRepository;
    }

    @GetMapping
    public ResponseEntity<List<Order>> getOrders(Principal principal) {
        String email = principal.getName();
        Customer customer = this.customerRepository.findByEmail(email);
        return ResponseEntity.ok(this.orderDAO.getOrdersByCustomer(customer));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Order>> getAllOrders() {
        List<Order> orders = this.orderDAO.getAllOrders();
        for (Order order : orders) {
            System.out.println(order);
        }
        return ResponseEntity.ok(orders);
    }

    @PostMapping
    public ResponseEntity<String> placeOrder(Principal principal, @RequestBody OrderDTO orderDTO) {
        Customer customer = this.customerRepository.findByEmail(principal.getName());
        this.orderDAO.placeOrder(customer, orderDTO);
        return ResponseEntity.ok("Order was successfully placed");
    }

    @PutMapping("/{orderId}")
    public ResponseEntity<String> updateOrder(@PathVariable UUID orderId, @RequestBody OrderDTO orderDTO) {
        this.orderDAO.updateOrderById(orderId, orderDTO);
        return ResponseEntity.ok("Order was successfully updated.");
    }

    @PutMapping("/complete/{orderId}")
    public ResponseEntity<String> completeOrder(@PathVariable UUID orderId) {
        this.orderDAO.completeOrder(orderId);
        return ResponseEntity.ok("Order was successfully completed.");
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<String> deleteOrder(@PathVariable UUID orderId) {
        this.orderDAO.deleteOrderById(orderId);
        return ResponseEntity.ok("Order was successfully deleted");
    }
}
