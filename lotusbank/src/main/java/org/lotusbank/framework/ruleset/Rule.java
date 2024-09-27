package org.lotusbank.framework.ruleset;

public interface Rule<T> {
    boolean isSatisfied(T object);
}
