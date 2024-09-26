package com.inditex.core.platform.domain;

import com.inditex.core.platform.domain.exception.IdentityException;
import lombok.Getter;

import java.util.Objects;

@Getter
public abstract class BaseId<T> {

    final protected T value;

    protected BaseId(final T value) {
        if (value == null) {
            throw new IdentityException("Domain entity identifier cannot be null");
        }
        this.value = value;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final BaseId<?> that = (BaseId<?>) o;
        return value.equals(that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
