package com.mse.validation;

import com.mse.annotations.Regex;

import java.lang.reflect.Field;
import java.util.*;
import java.util.regex.Pattern;

public class RegexStrategy implements ValidationStrategy {

    @Override
    public <T> Map<T, Set<String>> validate(List<T> objects) {
        Map<T, Set<String>> errorsMap = new HashMap<T, Set<String>>();

        for (T object : objects) {
            Set<String> errors = new HashSet<String>();

            for(Field field : object.getClass().getDeclaredFields()) {
                Regex annotation = field.getAnnotation(Regex.class);
                if (annotation == null) {continue;}

                field.setAccessible(true);
                try{
                    Object value = field.get(object);
                    if (value == null || !Pattern.matches(annotation.pattern(), value.toString())) {

                        errors.add(annotation.message());
                    }
                }catch(IllegalAccessException ignored){}
            }

            if(!errors.isEmpty()){
                errorsMap.put(object, errors);
            }
        }
        return errorsMap;
    }

}
