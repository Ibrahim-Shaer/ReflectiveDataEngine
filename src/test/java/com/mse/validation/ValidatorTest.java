package com.mse.validation;

import com.mse.models.Customer;
import com.mse.parser.GenericFileParser;
import org.junit.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.Assert.*;

public class ValidatorTest {

    @Test
    public void shouldValidateCustomers() throws Exception {

        GenericFileParser parser = new GenericFileParser();
        String path = getClass()
                .getClassLoader()
                .getResource("customers.txt")
                .getPath();

        List<Customer> customers = parser.parse(path, Customer.class);


        Validator validator = new Validator();
        Map<Customer, Set<String>> errors = validator.validate(customers);

        Customer first = customers.get(0);
        Customer second = customers.get(1);


        for (var entry : errors.entrySet()) {
            System.out.println("Customer: " + entry.getKey());
            System.out.println("Errors: " + entry.getValue());
        }


        assertTrue(errors.containsKey(first));
        Set<String> firstErrors = errors.get(first);
        assertNotNull(firstErrors);
        assertTrue(firstErrors.contains("Name is required"));
        assertTrue(firstErrors.contains("Invalid email"));
        assertTrue(firstErrors.contains("Age must be between 18 and 120"));


        Set<String> secondErrors = errors.get(second);
        assertTrue(secondErrors == null || secondErrors.isEmpty());
    }
}
