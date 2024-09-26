package com.inditex.core.platform.dataaccess.mapper;

import com.inditex.core.platform.domain.BaseId;
import org.mapstruct.Mapping;


public interface BaseIdMapper<ID extends BaseId<T>, T> {

    @Mapping(target = "value", source = ".")
    ID toBaseId(T id);
}
