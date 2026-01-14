package com.mse.validation;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface ValidationStrategy {

    public <T> Map<T, Set<String>> validate(List<T> objects);
}
