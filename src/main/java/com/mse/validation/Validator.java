package com.mse.validation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Validator {

    private final List<ValidationStrategy> strategies = List.of(
            new NotNullStrategy(),
            new RegexStrategy(),
            new RangeStartegy()
    );


    public <T> Map<T, Set<String>> validate(List<T> objects) {
        Map<T, Set<String>> finalErrors = new HashMap<T, Set<String>>();

        for (ValidationStrategy strategy : strategies) {
            Map<T, Set<String>> errors = strategy.validate(objects);
            finalErrors.putAll(errors);
        }
        return finalErrors;
    }

}
