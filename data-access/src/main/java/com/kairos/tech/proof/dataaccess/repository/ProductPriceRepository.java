package com.kairos.tech.proof.dataaccess.repository;

import com.kairos.tech.proof.dataaccess.model.ProductEntity;

import java.time.Instant;
import java.util.Optional;

public interface ProductPriceRepository {

    Optional<ProductEntity> findProductPrices(Integer brandId, Long productId, Instant fee);
}
