package org.lotusbank.common.pattern.observer;

import org.lotusbank.framework.domain.AccountEntry;

public interface Subject {
    void registerObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObserver(AccountEntry accountEntry);
}
