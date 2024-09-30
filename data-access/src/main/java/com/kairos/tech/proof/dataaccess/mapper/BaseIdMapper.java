package com.kairos.tech.proof.dataaccess.mapper;

import com.kairos.tech.proof.domain.util.BaseId;
import org.mapstruct.Mapping;


public interface BaseIdMapper<ID extends BaseId<T>, T> {

    @Mapping(target = "value", source = ".")
    ID toBaseId(T id);
}
