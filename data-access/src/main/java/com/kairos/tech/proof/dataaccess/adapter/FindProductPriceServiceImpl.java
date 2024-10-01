package com.kairos.tech.proof.dataaccess.adapter;

import com.kairos.tech.proof.application.port.output.FindProductPriceService;
import com.kairos.tech.proof.dataaccess.mapper.ProductEntityMapper;
import com.kairos.tech.proof.dataaccess.model.ProductEntity;
import com.kairos.tech.proof.dataaccess.repository.ProductPriceRepository;
import com.kairos.tech.proof.domain.model.BrandId;
import com.kairos.tech.proof.domain.model.Product;
import com.kairos.tech.proof.domain.model.ProductId;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.ZoneOffset;
import java.util.Optional;
import java.util.function.Predicate;

@Service
@AllArgsConstructor
public class FindProductPriceServiceImpl implements FindProductPriceService {

    private final ProductPriceRepository productPriceRepository;
    private final ProductEntityMapper productEntityMapper;

    @Override
    public Optional<Product> findProductPrices(BrandId brandId, ProductId productId, Instant feeDateTime) {
        Timestamp feeTimestamp = Timestamp.valueOf(feeDateTime.atOffset(ZoneOffset.UTC).toLocalDateTime());
        return Optional.of(productPriceRepository.findProductPrices(brandId.getValue(), productId.getValue(), feeTimestamp))
                .filter(Predicate.not(CollectionUtils::isEmpty))
                .map(prices -> new ProductEntity(productId.getValue(), brandId.getValue(), prices))
                .map(productEntityMapper::toProduct);
    }
}

