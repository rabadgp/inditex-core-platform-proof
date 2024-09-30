package com.inditex.core.platform.dataaccess;

import com.inditex.core.platform.application.port.output.FindProductPriceService;
import com.inditex.core.platform.dataaccess.mapper.ProductEntityMapper;
import com.inditex.core.platform.dataaccess.repository.ProductPriceRepository;
import com.inditex.core.platform.domain.model.BrandId;
import com.inditex.core.platform.domain.model.Product;
import com.inditex.core.platform.domain.model.ProductId;
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
