package com.juwelier.webshop.services;

import com.juwelier.webshop.dao.ReturnRequestRepository;
import com.juwelier.webshop.dto.ReturnRequestDTO;
import com.juwelier.webshop.models.ReturnRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReturnRequestService {

    public final ReturnRequestRepository repository;

    @Autowired
    public ReturnRequestService(ReturnRequestRepository repository) {
        this.repository = repository;
    }

    public ReturnRequestDTO createReturnRequest(ReturnRequestDTO dto) {
        ReturnRequest entity = new ReturnRequest();
        entity.setOrderId(dto.getOrderId());
        entity.setCustomerId(dto.getCustomerId());
        entity.setReason(dto.getReason());
        entity.setPreferredHandling(dto.getPreferredHandling());
        entity.setStatus("PENDING");
        entity.setCreatedAt(LocalDateTime.now());
        ReturnRequest savedEntity = repository.save(entity);
        return convertToDto(savedEntity);
    }

    public List<ReturnRequestDTO> getReturnRequestsByCustomerId(String customerId) {
        return repository.findByCustomerId(customerId).stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public List<ReturnRequestDTO> getReturnRequestsByOrderId(String orderId) {
        return repository.findByOrderId(orderId).stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private ReturnRequestDTO convertToDto(ReturnRequest entity) {
        ReturnRequestDTO dto = new ReturnRequestDTO();
        dto.setId(entity.getId());
        dto.setCustomerId(entity.getCustomerId());
        dto.setOrderId(entity.getOrderId());
        dto.setReason(entity.getReason());
        dto.setPreferredHandling(entity.getPreferredHandling());
        dto.setStatus(entity.getStatus());
        return dto;
    }
}