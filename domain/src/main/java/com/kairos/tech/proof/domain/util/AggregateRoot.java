package com.kairos.tech.proof.domain.util;

public class AggregateRoot<ID extends BaseId<?>> extends BaseEntity<ID> {

    protected AggregateRoot() {
        super();
    }

    protected AggregateRoot(ID value) {
        super(value);
    }

}