package com.inditex.core.platform.dataaccess;

import com.inditex.core.platform.application.port.output.FindProductPriceService;
import com.inditex.core.platform.dataaccess.mapper.ProductEntityMapper;
import com.inditex.core.platform.dataaccess.repository.ProductRepository;
import com.inditex.core.platform.domain.model.BrandId;
import com.inditex.core.platform.domain.model.Product;
import com.inditex.core.platform.domain.model.ProductId;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.OffsetDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
public class FindProductPriceServiceImpl implements FindProductPriceService {

    private final ProductRepository productRepository;
    private final ProductEntityMapper productEntityMapper;

    @Override
    public Optional<Product> findActivePrice(BrandId brandId, ProductId productId, OffsetDateTime fareDate) {
        return productRepository.findActivePrice(brandId.getValue(), productId.getValue(), Timestamp.from(fareDate.toInstant()))
                .map(productEntityMapper::toProduct);
    }
}
