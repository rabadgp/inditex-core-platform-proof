package com.inditex.core.platform.dataaccess.mapper;

import com.inditex.core.platform.dataaccess.model.PriceEntity;
import com.inditex.core.platform.domain.model.Price;
import com.inditex.core.platform.domain.model.PriceId;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import java.sql.Timestamp;
import java.time.OffsetDateTime;
import java.time.ZoneId;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PriceEntityMapper extends BaseIdMapper<PriceId, Integer> {

    @Mapping(target = "id", source = "priceId")
    @Mapping(target = "startDate", qualifiedByName = "toOffsetDateTIme")
    @Mapping(target = "endDate", qualifiedByName = "toOffsetDateTIme")
    @Mapping(target = "amount", source = "amount")
    @Mapping(target = "currency", source = "currency")
    Price toPrice(PriceEntity priceEntity);

    @Named("toOffsetDateTIme")
    default OffsetDateTime toOffsetDateTIme(Timestamp timestamp) {
        return OffsetDateTime.ofInstant(timestamp.toInstant(), ZoneId.of("UTC"));
    }
}
