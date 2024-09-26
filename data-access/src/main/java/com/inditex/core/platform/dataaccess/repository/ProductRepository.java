package com.inditex.core.platform.dataaccess.repository;

import com.inditex.core.platform.dataaccess.model.ProductEntity;

import java.sql.Timestamp;
import java.util.Optional;

public interface ProductRepository {

    //    @Query(value =
//            "select p.brand_id, pr.* " +
//                    "from products p, prices pr " +
//                    "where p.product_id = pr.product_id " +
//                    "and p.brand_id = :brandId " +
//                    "and p.product_id = :productId " +
//                    "and datediff('SECOND',start_date,:fee) >= 0 " +
//                    "and datediff('SECOND',end_date,:fee) <= 0 " +
//                    "order by priority desc " +
//                    "limit 1", nativeQuery = true)
    Optional<ProductEntity> findActivePrice(Integer brandId, Long productId, Timestamp fee);
}
