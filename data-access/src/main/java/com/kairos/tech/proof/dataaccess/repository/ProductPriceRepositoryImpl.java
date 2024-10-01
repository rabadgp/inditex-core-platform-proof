package com.kairos.tech.proof.dataaccess.repository;

import com.kairos.tech.proof.dataaccess.model.PriceEntity;
import com.kairos.tech.proof.dataaccess.model.ProductEntity;
import io.vavr.control.Try;
import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Optional;
import java.util.function.Predicate;

@Repository
@AllArgsConstructor
public class ProductPriceRepositoryImpl implements ProductPriceRepository {

    EntityManager entityManager;

    @Override
    public Optional<ProductEntity> findProductPrices(Integer brandId, Long productId, Instant fee) {
        LocalDateTime localDateTime = LocalDateTime.ofInstant(fee, ZoneOffset.UTC);
        Timestamp timestamp = Timestamp.valueOf(localDateTime);
        ProductEntity result = Try.ofCallable(() -> entityManager.createQuery("SELECT pr " +
                                "FROM PriceEntity pr JOIN pr.product p " +
                                "WHERE p.brandId = :brandId " +
                                "AND p.productId = :productId " +
                                "AND pr.startDate <= :fee " +
                                "AND pr.endDate >= :fee " +
                                "ORDER BY priority DESC", PriceEntity.class)
                        .setParameter("brandId", brandId)
                        .setParameter("productId", productId)
                        .setParameter("fee", timestamp)
                        .getResultList())
                .filter(Predicate.not(CollectionUtils::isEmpty))
                .map(priceEntities -> new ProductEntity(productId, brandId, priceEntities))
                .getOrNull();
        return Optional.ofNullable(result);
    }
}
