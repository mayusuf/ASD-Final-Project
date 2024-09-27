package org.lotusbank.framework.ruleset;

import java.util.List;

public class RuleEngine<T> {
    private List<Rule<T>> rules;

    public RuleEngine(List<Rule<T>> rules) {
        this.rules = rules;
    }

    public boolean evaluate(T object) {
        for (Rule<T> rule : rules) {
            if (!rule.isSatisfied(object)) {
                return false;
            }
        }
        return true;
    }
}
