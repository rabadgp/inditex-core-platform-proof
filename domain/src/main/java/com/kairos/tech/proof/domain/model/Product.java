package com.kairos.tech.proof.domain.model;

import com.kairos.tech.proof.domain.util.AggregateRoot;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product extends AggregateRoot<ProductId> {

    private BrandId brandId;
    private List<Price> prices;

}
