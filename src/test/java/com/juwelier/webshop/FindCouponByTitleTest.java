package com.juwelier.webshop;

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

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FindCouponByTitleTest {

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
        couponDTO.setStartDate(new Date(2023 - 1900, 5, 1)); // June 1, 2023
        couponDTO.setEndDate(new Date(2023 - 1900, 5, 30)); // June 30, 2023
        couponDTO.setType("%");
        couponDTO.setStatus(true);

        coupon = new Coupon();
        coupon.setTitle("Summer Sale");
        coupon.setMessage("Get 20% off on all items");
        coupon.setAmount(20);
        coupon.setPrice(100);
        coupon.setStartDate(new Date(2023 - 1900, 5, 1)); // June 1, 2023
        coupon.setEndDate(new Date(2023 - 1900, 5, 30)); // June 30, 2023
        coupon.setType("%");
        coupon.setStatus(true);
    }

    @Test
    void findCouponByTitle() {
        // Arrange
        when(couponRepository.findCouponByTitle(anyString())).thenReturn(Optional.of(coupon));

        // Act
        Optional<Coupon> result = couponDAO.findCouponByTitle("Summer Sale");

        // Assert
        assertTrue(result.isPresent());
        assertEquals("Summer Sale", result.get().getTitle());
        verify(couponRepository, times(1)).findCouponByTitle("Summer Sale");
    }
}
