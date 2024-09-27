package com.inditex.core.platform.rest.mapper;

import com.inditex.core.platform.domain.model.Product;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.openapitools.model.ProductPriceResponse;

@Mapper
public interface ProductResponseMapper {

    @Mapping(target = "productId", source = "id.value")
    @Mapping(target = "brandId", source = "brandId.value")
    ProductPriceResponse toProductPriceResponse(Product product);

    @AfterMapping
    default void setPrice(Product product, @MappingTarget ProductPriceResponse response) {
        product.getPrices().stream().limit(1).forEach(price -> {
            response.setPriceId(price.getId().getValue());
            response.setStartDate(price.getStartDate());
            response.setEndDate(price.getEndDate());
            response.setCurrency(price.getCurrency());
            response.setAmount(price.getAmount());
        });
    }
}
