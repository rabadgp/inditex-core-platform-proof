package com.kairos.tech.proof.domain.model;

import com.kairos.tech.proof.domain.util.AggregateRoot;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product extends AggregateRoot<ProductId> {

    private BrandId brandId;
    private List<Price> prices;

    public Product getProductWithActivePrice() {
        setPrices(Collections.singletonList(getHighestPriority()));
        return this;
    }

    private Price getHighestPriority() {
        return Collections.max(prices, Comparator.comparingInt(Price::getPriority));
    }
}
