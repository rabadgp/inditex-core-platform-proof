package com.kairos.tech.proof.dataaccess.adapter;

import com.kairos.tech.proof.application.exception.FindProductException;
import com.kairos.tech.proof.application.port.output.FindProductPriceService;
import com.kairos.tech.proof.dataaccess.mapper.ProductEntityMapper;
import com.kairos.tech.proof.dataaccess.repository.ProductPriceRepository;
import com.kairos.tech.proof.domain.model.BrandId;
import com.kairos.tech.proof.domain.model.Product;
import com.kairos.tech.proof.domain.model.ProductId;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.text.MessageFormat;
import java.time.Instant;
import java.util.Optional;
import java.util.function.Predicate;

@Service
@AllArgsConstructor
public class FindProductPriceServiceImpl implements FindProductPriceService {

    private final ProductPriceRepository productPriceRepository;
    private final ProductEntityMapper productEntityMapper;

    @Override
    public Optional<Product> findActivePrice(BrandId brandId, ProductId productId, Instant feeDateTime) {
        return productPriceRepository.findActivePrice(brandId.getValue(), productId.getValue(), feeDateTime)
                .or(() -> Optional.ofNullable(productPriceRepository.findByBrandIdAndProductId(brandId.getValue(), productId.getValue())
                        .orElseThrow(() -> new FindProductException(
                                MessageFormat.format("Cannot retrieve product: {0} related to brand: {1}", productId.getValue(), brandId.getValue()),
                                new EntityNotFoundException())
                        ))
                )
                .filter(Predicate.not(productEntity -> CollectionUtils.isEmpty(productEntity.getPrices())))
                .map(productEntityMapper::toProduct);
    }
}
