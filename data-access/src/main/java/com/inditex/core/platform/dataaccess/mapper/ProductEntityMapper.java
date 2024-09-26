package com.inditex.core.platform.dataaccess.mapper;

import com.inditex.core.platform.dataaccess.model.ProductEntity;
import com.inditex.core.platform.domain.model.BrandId;
import com.inditex.core.platform.domain.model.Product;
import com.inditex.core.platform.domain.model.ProductId;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

@Mapper(uses = {PriceEntityMapper.class},
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProductEntityMapper extends BaseIdMapper<ProductId, Long> {

    @Mapping(target = "id", source = "productId")
    @Mapping(target = "brandId", source = "brandId", qualifiedByName = "mapBrandId")
    Product toProduct(ProductEntity productEntity);

    @Named("mapBrandId")
    default BrandId mapBrandId(Integer brandId) {
        return new BrandId(brandId);
    }
}
