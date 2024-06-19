package com.juwelier.webshop.dao;

import com.juwelier.webshop.models.Customer;
import com.juwelier.webshop.models.Order;
import com.juwelier.webshop.models.OrderedProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface OrderedProductRepository extends JpaRepository<OrderedProduct, UUID> {
    List<OrderedProduct> findAllByOrder(Order order);
}
