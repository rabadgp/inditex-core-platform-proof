package com.inditex.core.platform.dataaccess.repository;

import com.inditex.core.platform.dataaccess.model.PriceEntity;
import com.inditex.core.platform.dataaccess.model.ProductEntity;
import io.vavr.control.Try;
import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Collections;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class ProductPriceRepositoryImpl implements ProductPriceRepository {

    EntityManager entityManager;

    @Override
    public Optional<ProductEntity> findActivePrice(Integer brandId, Long productId, Instant fee) {
        LocalDateTime localDateTime = LocalDateTime.ofInstant(fee, ZoneOffset.UTC);
        Timestamp timestamp = Timestamp.valueOf(localDateTime);
        ProductEntity productEntity = Try.ofCallable(() -> entityManager.createQuery("SELECT pr " +
                                "FROM PriceEntity pr JOIN pr.product p " +
                                "WHERE p.brandId = :brandId " +
                                "AND p.productId = :productId " +
                                "AND startDate <= :fee " +
                                "AND endDate >= :fee " +
                                "ORDER BY priority DESC", PriceEntity.class)
                        .setParameter("brandId", brandId)
                        .setParameter("productId", productId)
                        .setParameter("fee", timestamp)
                        .setMaxResults(1)
                        .getSingleResult())
                .map(price -> {
                    ProductEntity product = price.getProduct();
                    return new ProductEntity(product.getProductId(), product.getBrandId(), Collections.singletonList(price));
                })
                .getOrNull();
        return Optional.ofNullable(productEntity);
    }
}
