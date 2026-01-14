package com.mse.models;

import com.mse.annotations.*;

@FileSource(delimiter = ",")
public class Customer {

    @Column(index = 0, name = "name")
    @NotNull(message = "Name is required")
    private String name;

    @Column(index = 1, name = "email")
    @Regex(pattern = ".+@.+\\..+", message = "Invalid email")
    private String email;

    @Column(index = 2, name = "age")
    @Range(min = 18, max = 120, message = "Age must be between 18 and 120")
    private int age;

    public String getName() { return name; }
    public String getEmail() { return email; }
    public int getAge() { return age; }
}
