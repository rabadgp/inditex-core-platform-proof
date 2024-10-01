package com.kairos.tech.proof.dataaccess.repository;

import com.kairos.tech.proof.dataaccess.model.PriceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface ProductPriceRepository extends JpaRepository<PriceEntity, Integer> {

    @Query("SELECT pr " +
            "FROM PriceEntity pr JOIN pr.product p " +
            "WHERE p.brandId = :brandId " +
            "AND p.productId = :productId " +
            "AND pr.startDate <= :fee " +
            "AND pr.endDate >= :fee " +
            "ORDER BY priority DESC")
    List<PriceEntity> findProductPrices(Integer brandId, Long productId, Timestamp fee);
}
