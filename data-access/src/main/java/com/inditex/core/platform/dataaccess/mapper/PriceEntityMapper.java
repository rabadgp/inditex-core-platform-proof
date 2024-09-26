package com.inditex.core.platform.dataaccess.mapper;

import com.inditex.core.platform.dataaccess.model.PriceEntity;
import com.inditex.core.platform.domain.model.Price;
import com.inditex.core.platform.domain.model.PriceId;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PriceEntityMapper {

    @Mapping(target = "priceId.id", qualifiedByName = "buildPriceId")
    @Mapping(target = "startDate", ignore = true)
    @Mapping(target = "endDate", ignore = true)
    Price toPrice(PriceEntity priceEntity);

    @Named("buildPriceId")
    default PriceId buildPriceId(PriceEntity priceEntity) {
        return new PriceId(priceEntity.getPriceId());
    }
}
