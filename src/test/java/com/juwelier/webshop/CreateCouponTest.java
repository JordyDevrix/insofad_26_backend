package com.juwelier.webshop.dao;

import com.juwelier.webshop.dao.CouponDAO;
import com.juwelier.webshop.dto.CouponDTO;
import com.juwelier.webshop.models.Coupon;
import com.juwelier.webshop.dao.CouponRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CreateCouponTest {

    @Mock
    private CouponRepository couponRepository;

    @InjectMocks
    private CouponDAO couponDAO;

    private CouponDTO couponDTO;
    private Coupon coupon;

    @BeforeEach
    void setUp() {
        couponDTO = new CouponDTO();
        couponDTO.setTitle("Summer Sale");
        couponDTO.setMessage("Get 20% off on all items");
        couponDTO.setAmount(20);
        couponDTO.setPrice(100);
        couponDTO.setStartDate(new Date(2023 - 1900, 5, 1));
        couponDTO.setEndDate(new Date(2023 - 1900, 5, 30));
        couponDTO.setType("%");
        couponDTO.setStatus(true);

        coupon = new Coupon();
        coupon.setTitle("Summer Sale");
        coupon.setMessage("Get 20% off on all items");
        coupon.setAmount(20);
        coupon.setPrice(100);
        coupon.setStartDate(new Date(2023 - 1900, 5, 1));
        coupon.setEndDate(new Date(2023 - 1900, 5, 30));
        coupon.setType("%");
        coupon.setStatus(true);
    }

    @Test
    void createCoupon() {
        // Arrange
        when(couponRepository.save(any(Coupon.class))).thenReturn(coupon);

        // Act
        couponDAO.createCoupon(couponDTO);

        // Assert
        verify(couponRepository, times(1)).save(any(Coupon.class));
    }
}