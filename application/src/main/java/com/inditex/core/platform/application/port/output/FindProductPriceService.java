package com.inditex.core.platform.application.port.output;

import com.inditex.core.platform.domain.model.BrandId;
import com.inditex.core.platform.domain.model.Product;
import com.inditex.core.platform.domain.model.ProductId;

import java.time.Instant;
import java.util.Optional;


public interface FindProductPriceService {
    Optional<Product> findActivePrice(BrandId brandId, ProductId productId, Instant feeDateTime);
}
