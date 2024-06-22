package com.juwelier.webshop.controllers;

import com.juwelier.webshop.dto.ReturnRequestDTO;
import com.juwelier.webshop.services.ReturnRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/returns")
public class ReturnRequestController {

    private final ReturnRequestService service;

    @Autowired
    public ReturnRequestController(ReturnRequestService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ReturnRequestDTO> createReturnRequest(@RequestBody ReturnRequestDTO requestDto) {
        ReturnRequestDTO createdRequest = service.createReturnRequest(requestDto);
        return ResponseEntity.ok(createdRequest);
    }

    @GetMapping("/order/{orderId}")
    public ResponseEntity<List<ReturnRequestDTO>> getReturnRequestsByOrderId(@PathVariable String orderId) {
        List<ReturnRequestDTO> returnRequests = service.getReturnRequestsByOrderId(orderId);
        return ResponseEntity.ok(returnRequests);
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<ReturnRequestDTO>> getReturnRequestsByCustomerId(@PathVariable String customerId) {
        List<ReturnRequestDTO> returnRequests = service.getReturnRequestsByCustomerId(customerId);
        return ResponseEntity.ok(returnRequests);
    }
}
