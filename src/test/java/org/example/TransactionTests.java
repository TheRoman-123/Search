package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Currency;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class TransactionTests {

    private Transaction transaction;

    @BeforeEach
    void setUp() {
        transaction = new Transaction(
                123456789L,
                "1111222233334444",
                "5555666677778888",
                ZonedDateTime.of(2023, 4, 13, 9, 30, 0, 0, ZoneId.of("UTC")),
                new BigDecimal("100.00"),
                Currency.getInstance("USD")
        );
    }

    @Test
    void testGetters() {
        assertEquals(123456789L, transaction.getId());
        assertEquals("1111222233334444", transaction.getSenderCardNumber());
        assertEquals("5555666677778888", transaction.getRecipientCardNumber());
        assertEquals(
                ZonedDateTime.of(2023, 4, 13, 9, 30, 0, 0, ZoneId.of("UTC")),
                transaction.getDateTime()
        );
        assertEquals(new BigDecimal("100.00"), transaction.getMoneyAmount());
        assertEquals(Currency.getInstance("USD"), transaction.getCurrency());
    }

    @Test
    void testEqualsAndHashCode() {
        Transaction other = new Transaction(
                123456789L,
                "1111222233334444",
                "5555666677778888",
                ZonedDateTime.of(2023, 4, 13, 9, 30, 0, 0, ZoneId.of("UTC")),
                new BigDecimal("100.00"),
                Currency.getInstance("USD")
        );

        assertEquals(transaction, other);
        assertEquals(transaction.hashCode(), other.hashCode());

        other = new Transaction(
                987654321L,
                "2222333344445555",
                "9999000011112222",
                ZonedDateTime.of(2023, 4, 13, 9, 30, 0, 0, ZoneId.of("UTC")),
                new BigDecimal("50.00"),
                Currency.getInstance("EUR")
        );

        assertNotEquals(transaction, other);
        assertNotEquals(transaction.hashCode(), other.hashCode());
    }

    @Test
    void testToString() {
        String expected = "123456789\t1111222233334444\t5555666677778888\t2023-04-13T09:30Z[UTC]\t100.00\tUSD";
        assertEquals(expected, transaction.toString());
    }

    @Test
    void testEmptyTransaction() {
        Transaction empty = Transaction.emptyTransaction();

        assertEquals(0L, empty.getId());
        assertEquals("0000000000000000", empty.getSenderCardNumber());
        assertEquals("111111111111111111", empty.getRecipientCardNumber());
        assertEquals(
                ZonedDateTime.of(1970, 1, 1, 0, 0, 0, 0, ZoneId.of("UTC")),
                empty.getDateTime()
        );
        assertEquals(new BigDecimal(1), empty.getMoneyAmount());
        assertEquals(Currency.getInstance("USD"), empty.getCurrency());
    }

    @Test
    void testCopyConstructor() {
        Transaction copy = new Transaction(transaction);

        assertEquals(transaction, copy);
        assertEquals(transaction.hashCode(), copy.hashCode());
    }

}



