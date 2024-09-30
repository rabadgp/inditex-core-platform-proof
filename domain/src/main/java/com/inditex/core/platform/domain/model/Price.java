package com.inditex.core.platform.domain.model;

import com.inditex.core.platform.domain.util.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Price extends BaseEntity<PriceId> {

    private Short priority;
    private Float amount;
    private String currency;
    private Instant startDate;
    private Instant endDate;
}
