package com.juwelier.webshop.dao;

import com.juwelier.webshop.models.ProductProperties;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductPropertiesRepository extends JpaRepository<ProductProperties, Long> {
    Optional<List<ProductProperties>> findByProductId(long productId);

}
