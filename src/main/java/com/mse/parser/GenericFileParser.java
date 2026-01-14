package com.mse.parser;

import com.mse.annotations.Column;
import com.mse.annotations.FileSource;

import java.io.*;
import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;



public class GenericFileParser {

    public <T> List<T> parse(String filePath, Class<T> clazz) throws IOException , ReflectiveOperationException {


        FileSource fileSource = clazz.getAnnotation(FileSource.class);

        if (fileSource == null) {
            throw new ReflectiveOperationException("FileSource not found");
        }

        String delimeter = fileSource.delimiter();
        List<T> list = new ArrayList<>();


        try(BufferedReader reader = new BufferedReader(new FileReader(filePath))){
            String line = reader.readLine();
            while (line != null){
                if(!line.isEmpty()) {
                    String[] parts = line.split(delimeter);
                    T object = clazz.getDeclaredConstructor().newInstance();

                    for (Field field : clazz.getDeclaredFields()) {



                        Column column = field.getAnnotation(Column.class);
                        if (column == null) {
                            continue;
                        }

                        int index = column.index();
                        if (index >= parts.length) {
                            continue;
                        }

                        String value = parts[index];
                        Object converted = convertToFieldType(value, field.getType());

                        field.setAccessible(true);
                        field.set(object, converted);
                    }
                        list.add(object);
                }
                line = reader.readLine();
            }
        }
        return list;
    }


    private Object convertToFieldType(String value, Class<?> fieldType) throws ReflectiveOperationException {
        try {
            if (fieldType.equals(String.class)) {
                return value;
            }else if (fieldType.equals(Integer.class) || fieldType.equals(int.class)) {
                return Integer.parseInt(value);
            }else if (fieldType.equals(Double.class) || fieldType.equals(double.class)) {
                return Double.parseDouble(value);
            }else if (fieldType.equals(Boolean.class) || fieldType.equals(boolean.class)) {
                return Boolean.parseBoolean(value);
            }else if (fieldType.equals(LocalDate.class)) {
                return LocalDate.parse(value);
            }else {
                throw new RuntimeException("Unsupported field type: " + fieldType.getName());
            }
        }catch(Exception e){
            throw new RuntimeException("Failed to convert field type: " + value + " to " + fieldType.getName());
        }
    }




}




