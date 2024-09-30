package com.inditex.core.platform.dataaccess.mapper;

import com.inditex.core.platform.domain.util.BaseId;
import org.mapstruct.Mapping;


public interface BaseIdMapper<ID extends BaseId<T>, T> {

    @Mapping(target = "value", source = ".")
    ID toBaseId(T id);
}
