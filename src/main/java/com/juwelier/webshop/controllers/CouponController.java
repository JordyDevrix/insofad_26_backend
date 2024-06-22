package com.juwelier.webshop.controllers;
//
//
import com.juwelier.webshop.dao.CouponDAO;
import com.juwelier.webshop.dao.CouponRepository;
import com.juwelier.webshop.dto.CouponDTO;
import com.juwelier.webshop.models.Coupon;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@CrossOrigin(origins = {"http://localhost:4200"})
@RequestMapping("/coupons")
@Transactional
public class CouponController {

    @Autowired
    private CouponRepository couponRepository;
    private final CouponDAO couponDAO;

    public CouponController(CouponDAO couponDAO) {
        this.couponDAO = couponDAO;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/getAllCoupons")
    public ResponseEntity<List<Coupon>> getAllCoupons() {
        return ResponseEntity.ok(this.couponDAO.getAllCoupons());
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/createCoupon")
    public ResponseEntity<Map<String, String>> createCoupon(@RequestBody CouponDTO couponDTO) {
        this.couponDAO.createCoupon(couponDTO);
        Map<String, String> response = new HashMap<>();
        response.put("message", "New coupon has been created.");
        return ResponseEntity.ok(response);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/applyCoupon")
    public ResponseEntity<?> applyCoupon(@RequestBody ApplyCouponRequest applyCouponRequest) {
        try {
            System.out.println("Applying coupon with title: " + applyCouponRequest.getTitle());
            System.out.println("Total price before applying coupon: " + applyCouponRequest.getTotalPrice());


            Optional<Coupon> coupon = couponDAO.findCouponByTitle(applyCouponRequest.title);
            if (coupon.isPresent() && coupon.get().isStatus()) {
                double discount = coupon.get().getPrice();
                double newTotal = applyCouponRequest.totalPrice - discount;

                if ("$".equals(coupon.get().getType())) {
                    discount = coupon.get().getPrice();
                } else if ("%".equals(coupon.get().getType())) {
                    discount = applyCouponRequest.totalPrice * (coupon.get().getPrice() / 100);
                }

                System.out.println("Discount: " + discount);
                System.out.println("New total after applying coupon: " + newTotal);

                return ResponseEntity.ok(new ApplyCouponResponse(newTotal, discount,"Coupon applied successfully"));
            } else {
                return ResponseEntity.badRequest().body("Invalid or inactive coupon");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Internal server error" + e.getMessage());
            }
    }

    @GetMapping("/getByTitle/{title}")
    public ResponseEntity<Coupon> getCouponByTitle(@PathVariable String title) {
        Optional<Coupon> couponOptional = couponRepository.findCouponByTitle(title);
        return couponOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PutMapping("/updateCoupon/{title}")
    public ResponseEntity<Map<String, String>> updateCoupon(@PathVariable String title, @RequestBody Map<String, Boolean> request) {
        Optional<Coupon> couponOptional = couponRepository.findCouponByTitle(title);

        if (couponOptional.isPresent()) {
            Coupon coupon = couponOptional.get();
            boolean newStatus = request.get("status");
            coupon.setStatus(newStatus);
            couponRepository.save(coupon);
            Map<String, String> response = new HashMap<>();
            response.put("message", "Coupon status updated successfully.");
            return ResponseEntity.ok(response);
        } else {
            Map<String, String> response = new HashMap<>();
            response.put("message", "Coupon not found.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    @DeleteMapping("/{title}")
    public ResponseEntity<Map<String, String>> deleteCouponByTitle(@PathVariable String title) {
        try {
            Optional<Coupon> couponOptional = couponRepository.findCouponByTitle(title);

            if (couponOptional.isPresent()) {
                couponRepository.deleteCouponByTitle(title);
                Map<String, String> response = new HashMap<>();
                response.put("message", "Coupon deleted successfully.");
                return ResponseEntity.ok(response);
            } else {
                Map<String, String> response = new HashMap<>();
                response.put("message", "Coupon not found.");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
        } catch (Exception e) {
            Map<String, String> response = new HashMap<>();
            response.put("message", "Failed to delete coupon: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }


    public static class CouponStatusUpdateRequest {
        private boolean status;

        public boolean getStatus() {
            return status;
        }

        public void setStatus(boolean status) {
            this.status = status;
        }
    }

    public static class ApplyCouponRequest {
        private String title;
        private double totalPrice;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public double getTotalPrice() {
            return totalPrice;
        }

        public void setTotalPrice(double totalPrice) {
            this.totalPrice = totalPrice;
        }
    }

    public static class ApplyCouponResponse {
        private double newTotal;
        private double discount;
        private String message;

        public ApplyCouponResponse(double newTotal, double discount, String message) {
            this.newTotal = newTotal;
            this.discount = discount;
            this.message = message;
        }

        public double getNewTotal() {
            return newTotal;
        }

        public void setNewTotal(double newTotal) {
            this.newTotal = newTotal;
        }

        public double getDiscount() {
            return discount;
        }

        public void setDiscount(double discount) {
            this.discount = discount;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
}
