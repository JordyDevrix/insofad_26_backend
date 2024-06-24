package com.juwelier.webshop.dto;
public class ReturnRequestDTO {
    private Long id;
    private String orderId;

    private String customerId;
    private String reason;
    private String preferredHandling;
    private String status;

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

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    // Getters and setters
}