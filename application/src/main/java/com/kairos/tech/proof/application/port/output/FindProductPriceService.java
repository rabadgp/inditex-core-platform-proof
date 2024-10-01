package com.kairos.tech.proof.application.port.output;

import com.kairos.tech.proof.domain.model.BrandId;
import com.kairos.tech.proof.domain.model.Product;
import com.kairos.tech.proof.domain.model.ProductId;

import java.time.Instant;
import java.util.Optional;


public interface FindProductPriceService {
    Optional<Product> findProductPrices(BrandId brandId, ProductId productId, Instant feeDateTime);

}
