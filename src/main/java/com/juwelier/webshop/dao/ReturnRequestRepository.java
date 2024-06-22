package com.juwelier.webshop.dao;

import com.juwelier.webshop.models.ReturnRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReturnRequestRepository extends JpaRepository<ReturnRequest, Long> {
    List<ReturnRequest> findByOrderId(String orderId);
    List<ReturnRequest> findByCustomerId(String customerId);
}