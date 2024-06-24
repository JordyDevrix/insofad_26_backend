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
class GetAllCouponsTest {

    @Mock
    private CouponRepository couponRepository;

    @InjectMocks
    private CouponDAO couponDAO;

    private CouponDTO couponDTO;
    private Coupon coupon;

//    @BeforeEach
//    void setUp() {
//        couponDTO = new CouponDTO();
//        couponDTO.setTitle("Summer Sale");
//        couponDTO.setMessage("Get 20% off on all items");
//        couponDTO.setAmount(20);
//        couponDTO.setPrice(100);
//        couponDTO.setStartDate(new Date(2023 - 1900, 5, 1)); // June 1, 2023
//        couponDTO.setEndDate(new Date(2023 - 1900, 5, 30)); // June 30, 2023
//        couponDTO.setType("Percentage");
//        couponDTO.setStatus(true);
//
//        coupon = new Coupon();
//        coupon.setTitle("Summer Sale");
//        coupon.setMessage("Get 20% off on all items");
//        coupon.setAmount(20);
//        coupon.setPrice(100);
//        coupon.setStartDate(new Date(2023 - 1900, 5, 1)); // June 1, 2023
//        coupon.setEndDate(new Date(2023 - 1900, 5, 30)); // June 30, 2023
//        coupon.setType("Percentage");
//        coupon.setStatus(true);
//    }

    @Test
    void getAllCoupons() {
        // Arrange
        List<Coupon> coupons = Arrays.asList(coupon);
        when(couponRepository.findAll()).thenReturn(coupons);

        // Act
        List<Coupon> result = couponDAO.getAllCoupons();

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(coupon.getTitle(), result.get(0).getTitle());
        verify(couponRepository, times(1)).findAll();
    }
}
