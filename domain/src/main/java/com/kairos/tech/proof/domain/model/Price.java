package com.kairos.tech.proof.domain.model;

import com.kairos.tech.proof.domain.util.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@NoArgsConstructor
@Getter
@Setter
public class Price extends BaseEntity<PriceId> {

    private Short priority;
    private Float amount;
    private String currency;
    private Instant startDate;
    private Instant endDate;

    public Price(PriceId id, Short priority, Float amount, String currency, Instant startDate, Instant endDate) {
        super(id);
        this.priority = priority;
        this.amount = amount;
        this.currency = currency;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    private Price(Builder builder) {
        id = (PriceId) builder.id;
        setPriority(builder.priority);
        setAmount(builder.amount);
        setCurrency(builder.currency);
        setStartDate(builder.startDate);
        setEndDate(builder.endDate);
    }

    public static Builder builder() {
        return new Builder();
    }


    public static final class Builder<PriceId> {
        private PriceId id;
        private Short priority;
        private Float amount;
        private String currency;
        private Instant startDate;
        private Instant endDate;

        private Builder() {
        }

        public Builder id(PriceId val) {
            id = val;
            return this;
        }

        public Builder priority(Short val) {
            priority = val;
            return this;
        }

        public Builder amount(Float val) {
            amount = val;
            return this;
        }

        public Builder currency(String val) {
            currency = val;
            return this;
        }

        public Builder startDate(Instant val) {
            startDate = val;
            return this;
        }

        public Builder endDate(Instant val) {
            endDate = val;
            return this;
        }

        public Price build() {
            return new Price(this);
        }
    }
}
