package com.inditex.core.platform.dataaccess.repository;

import com.inditex.core.platform.dataaccess.model.PriceEntity;
import com.inditex.core.platform.dataaccess.model.ProductEntity;
import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.Collections;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class ProductRepositoryImpl implements ProductRepository {

    EntityManager entityManager;

    @Override
    public Optional<ProductEntity> findActivePrice(Integer brandId, Long productId, Timestamp fee) {
        return Optional.ofNullable(entityManager.createQuery("SELECT pr " +
                        "FROM PriceEntity pr JOIN pr.product p " +
                        "WHERE p.brandId = :brandId " +
                        "AND p.productId = :productId " +
                        "AND startDate <= :fee " +
                        "AND endDate >= :fee " +
                        "ORDER BY priority DESC", PriceEntity.class)
                .setParameter("brandId", brandId)
                .setParameter("productId", productId)
                .setParameter("fee", fee)
                .setMaxResults(1)
                .getSingleResult()).map(price -> {
            ProductEntity product = price.getProduct();
            return new ProductEntity(product.getProductId(), product.getBrandId(), Collections.singletonList(price));
        });

    }
}
