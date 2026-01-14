package com.mse.parser;

import com.mse.models.Transaction;
import org.junit.Test;


import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

public class GenericFileParserTest {

    @Test
    public void shouldParseTransactionsFile() throws Exception {
        GenericFileParser parser = new GenericFileParser();

        String path = getClass()
                .getClassLoader()
                .getResource("transactions.txt")
                .getPath();

        List<Transaction> result = parser.parse(path, Transaction.class);

        assertEquals(3, result.size());

        Transaction first = result.get(0);
        assertEquals("TX1", first.getId());
        assertEquals(100.50, first.getAmount(), 0.00000000001);
        assertEquals(2026, first.getDate().getYear());
    }
}
