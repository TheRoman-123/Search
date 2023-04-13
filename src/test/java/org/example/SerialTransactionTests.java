package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SerialTransactionTests {

    private static final String TEST_FILE_NAME = "test_transactions.txt";
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE_TIME.withZone(ZoneId.of("UTC"));

    private SerialTransaction serialTransaction;

    @BeforeEach
    void setUp() {
        serialTransaction = new SerialTransaction();
    }

    @Test
    void testSerializeAndDeserialize() throws IOException {
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(new Transaction(1L, "item1", "seller1", ZonedDateTime.parse("2022-12-31T23:59:59", DATE_TIME_FORMATTER), new BigDecimal("10.00"), Currency.getInstance("USD")));
        transactions.add(new Transaction(2L, "item2", "seller2", ZonedDateTime.parse("2023-01-01T00:00:00", DATE_TIME_FORMATTER), new BigDecimal("20.00"), Currency.getInstance("EUR")));
        transactions.add(new Transaction(3L, "item3", "seller3", ZonedDateTime.parse("2023-01-01T01:00:00", DATE_TIME_FORMATTER), new BigDecimal("30.00"), Currency.getInstance("JPY")));

        Path testFilePath = Path.of(TEST_FILE_NAME);
        serialTransaction.serialize(transactions, testFilePath);

        List<Transaction> deserializedTransactions = SerialTransaction.deserialize(testFilePath);

        assertEquals(transactions, deserializedTransactions);

        Files.deleteIfExists(testFilePath);
    }

    @Test
    void testDeserializeFileNotFoundException() {
        Path nonExistentFile = Path.of(TEST_FILE_NAME);
        assertThrows(IOException.class, () -> SerialTransaction.deserialize(nonExistentFile));
    }
}
