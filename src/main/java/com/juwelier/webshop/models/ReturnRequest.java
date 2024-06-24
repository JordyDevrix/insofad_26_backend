package com.juwelier.webshop.models;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "return_requests")
public class ReturnRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String orderId;

    @Column(nullable = false)
    private String reason;

    @Column(nullable = false)
    private String preferredHandling;

    @Column(nullable = false)
    private String status;

    @Column(nullable = false)
    private String customerId;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getPreferredHandling() {
        return preferredHandling;
    }

    public void setPreferredHandling(String preferredHandling) {
        this.preferredHandling = preferredHandling;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    // Getters and setters
}