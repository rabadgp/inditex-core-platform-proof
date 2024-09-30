package com.kairos.tech.proof.dataaccess.mapper;

import com.kairos.tech.proof.dataaccess.model.PriceEntity;
import com.kairos.tech.proof.domain.model.Price;
import com.kairos.tech.proof.domain.model.PriceId;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PriceEntityMapper extends BaseIdMapper<PriceId, Integer> {

    @Mapping(target = "id", source = "priceId")
    @Mapping(target = "startDate", qualifiedByName = "toInstant")
    @Mapping(target = "endDate", qualifiedByName = "toInstant")
    @Mapping(target = "amount", source = "amount")
    @Mapping(target = "currency", source = "currency")
    Price toPrice(PriceEntity priceEntity);

    @Named("toInstant")
    default Instant toInstant(Timestamp timestamp) {
        return OffsetDateTime.of(timestamp.toLocalDateTime(), ZoneOffset.UTC).toInstant();
    }
}
