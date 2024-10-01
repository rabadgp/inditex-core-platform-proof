package com.kairos.tech.proof.dataaccess.adapter;

import com.kairos.tech.proof.application.port.output.FindProductPriceService;
import com.kairos.tech.proof.dataaccess.mapper.ProductEntityMapper;
import com.kairos.tech.proof.dataaccess.repository.ProductPriceRepository;
import com.kairos.tech.proof.domain.model.BrandId;
import com.kairos.tech.proof.domain.model.Product;
import com.kairos.tech.proof.domain.model.ProductId;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;

@Service
@AllArgsConstructor
public class FindProductPriceServiceImpl implements FindProductPriceService {

    private final ProductPriceRepository productPriceRepository;
    private final ProductEntityMapper productEntityMapper;

    @Override
    public Optional<Product> findActivePrice(BrandId brandId, ProductId productId, Instant feeDateTime) {
        return productPriceRepository.findActivePrice(brandId.getValue(), productId.getValue(), feeDateTime)
                .map(productEntityMapper::toProduct);
    }
}
