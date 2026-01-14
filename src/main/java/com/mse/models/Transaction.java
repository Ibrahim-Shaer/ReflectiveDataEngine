package com.mse.models;

import com.mse.annotations.Column;
import com.mse.annotations.FileSource;

import java.time.LocalDate;

@FileSource(delimiter = "\\|")
public class Transaction {

    @Column(index = 0, name = "id")
    private String id;

    @Column(index = 1, name = "amount")
    private double amount;

    @Column(index = 2, name = "date")
    private LocalDate date;

    public String getId() { return id; }
    public double getAmount() { return amount; }
    public LocalDate getDate() { return date; }
}
