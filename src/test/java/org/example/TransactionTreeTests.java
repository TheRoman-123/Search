package org.example;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Currency;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TransactionTreeTests {

    @Test
    public void testAddTransaction() {
        Transaction transaction1 = new Transaction(1, "0000000000000001", "1111111111111111",
                ZonedDateTime.now(ZoneId.of("UTC")), new BigDecimal(100), Currency.getInstance("USD"));

        Transaction transaction2 = new Transaction(2, "0000000000000002", "1111111111111112",
                ZonedDateTime.now(ZoneId.of("UTC")), new BigDecimal(200), Currency.getInstance("USD"));

        TransactionTree transactionTree = new TransactionTree(transaction1);
        transactionTree.addTransaction(transaction2);

        assertEquals(transaction1, transactionTree.findTransactionById(1));
        assertEquals(transaction2, transactionTree.findTransactionById(2));
    }

    @Test
    public void testFindTransactionById() {
        Transaction transaction1 = new Transaction(1, "0000000000000001", "1111111111111111",
                ZonedDateTime.now(ZoneId.of("UTC")), new BigDecimal(100), Currency.getInstance("USD"));

        TransactionTree transactionTree = new TransactionTree(transaction1);

        assertEquals(transaction1, transactionTree.findTransactionById(1));
    }

    @Test
    public void testRemoveTransaction() {
        Transaction transaction1 = new Transaction(1, "0000000000000001", "1111111111111111",
                ZonedDateTime.now(ZoneId.of("UTC")), new BigDecimal(100), Currency.getInstance("USD"));

        Transaction transaction2 = new Transaction(2, "0000000000000002", "1111111111111112",
                ZonedDateTime.now(ZoneId.of("UTC")), new BigDecimal(200), Currency.getInstance("USD"));

        TransactionTree transactionTree = new TransactionTree(transaction1, transaction2);
        transactionTree.removeTransaction(2);

        assertThrows(IllegalStateException.class, () -> transactionTree.findTransactionById(2));
    }
}
