package com.juwelier.webshop.dao;

import com.juwelier.webshop.dto.ReturnRequestDTO;
import com.juwelier.webshop.models.ReturnRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import com.juwelier.webshop.dao.ReturnRequestRepository;

import java.util.List;
import java.util.Optional;

@Component
public class ReturnRequestDAO {

    public final ReturnRequestRepository returnRequestRepository;

    @Autowired
    public ReturnRequestDAO(ReturnRequestRepository returnRequestRepository) {
        this.returnRequestRepository = returnRequestRepository;
    }

    public ReturnRequest save(ReturnRequest returnRequest) {
        return returnRequestRepository.save(returnRequest);
    }

    public List<ReturnRequest> findByOrderId(String orderId) {
        return returnRequestRepository.findByOrderId(orderId);
    }

    public ReturnRequest getReturnRequestById(Long id) {
        Optional<ReturnRequest> optionalReturnRequest = returnRequestRepository.findById(id);
        if (optionalReturnRequest.isPresent()) {
            return optionalReturnRequest.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Failed to get return request: ReturnRequest with ID '" + id + "' does not exist.");
        }
    }

    public void updateReturnRequestStatus(Long id, String status) {
        Optional<ReturnRequest> optionalReturnRequest = returnRequestRepository.findById(id);
        if (optionalReturnRequest.isPresent()) {
            ReturnRequest returnRequest = optionalReturnRequest.get();
            returnRequest.setStatus(status);
            returnRequestRepository.save(returnRequest);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Failed to update return request: ReturnRequest with ID '" + id + "' does not exist.");
        }
    }

    public void deleteReturnRequestById(Long id) {
        Optional<ReturnRequest> optionalReturnRequest = returnRequestRepository.findById(id);
        if (optionalReturnRequest.isPresent()) {
            ReturnRequest returnRequest = optionalReturnRequest.get();
            returnRequestRepository.delete(returnRequest);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Failed to delete return request: ReturnRequest with ID '" + id + "' does not exist.");
        }
    }
}