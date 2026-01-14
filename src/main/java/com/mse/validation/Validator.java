package com.mse.validation;

import java.util.*;

public class Validator {

    private final List<ValidationStrategy> strategies = List.of(
            new NotNullStrategy(),
            new RegexStrategy(),
            new RangeStartegy()
    );


    public <T> Map<T, Set<String>> validate(List<T> objects) {
        Map<T, Set<String>> finalErrors = new HashMap<T, Set<String>>();

        for(T object : objects) {
            finalErrors.put(object, new HashSet<>());
        }

        for (ValidationStrategy strategy : strategies) {
            Map<T, Set<String>> errors = strategy.validate(objects);
            for(Map.Entry<T, Set<String>> entry : errors.entrySet()) {
                finalErrors.get(entry.getKey()).addAll(entry.getValue());
            }
        }
        return finalErrors;
    }

}
