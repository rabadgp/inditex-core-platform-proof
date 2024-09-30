package com.inditex.core.platform.dataaccess.repository;

import com.inditex.core.platform.dataaccess.model.ProductEntity;

import java.time.Instant;
import java.util.Optional;

public interface ProductPriceRepository {

    Optional<ProductEntity> findActivePrice(Integer brandId, Long productId, Instant fee);
}
