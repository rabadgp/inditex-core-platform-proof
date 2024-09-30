package com.kairos.tech.proof.dataaccess.repository;

import com.kairos.tech.proof.dataaccess.model.ProductEntity;

import java.time.Instant;
import java.util.Optional;

public interface ProductPriceRepository {

    Optional<ProductEntity> findActivePrice(Integer brandId, Long productId, Instant fee);

    Optional<ProductEntity> findByBrandIdAndProductId(Integer brandId, Long productId);
}
