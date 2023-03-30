/**
 * The SerialTransaction class provides methods for serializing and deserializing
 * collections of transactions to and from a file. The file is assumed to contain
 * comma-separated values for each transaction.
 */

package org.example;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Currency;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SerialTransaction {
    /**
     * Serializes a collection of transactions to a file.
     * @param transactions a collection of Transaction objects to serialize
     * @param filePath the file path where the transactions will be written
     */
    public void serialize(Collection<Transaction> transactions, Path filePath) {
        try {
            Collection<? extends CharSequence> transactionsToSerialize = transactions
                    .stream()
                    .map(Transaction::toString)
                    .collect(Collectors.toCollection(ArrayList::new));
            Files.write(filePath, transactionsToSerialize, Charset.defaultCharset());
        }
        catch (IOException e) {
            System.err.println("Something went wrong when serializing transactions");
        }
    }

    /**
     * Deserializes a file of transactions and returns them as a list of Transaction objects.
     * @param filePath the file path of the file containing the transactions
     * @return a list of Transaction objects read from the file
     * @throws IOException if the file does not exist or cannot be read
     */
    public static List<Transaction> deserialize(Path filePath)
            throws IOException {
        if (Files.notExists(filePath)) {
            throw new FileNotFoundException("File with filePath = " + filePath + "is not found");
        }
        DateTimeFormatter dtf = DateTimeFormatter.ISO_LOCAL_DATE_TIME.withZone(ZoneId.of("UTC"));
        try (Stream<String> stream = Files.lines(filePath)) {
            return stream
                    .map(line -> {
                            String[] fields = line.split(",");
                            return new Transaction(
                                    Long.parseLong(fields[0].trim()),
                                    fields[1].trim(),
                                    fields[2].trim(),
                                    ZonedDateTime.parse(fields[3].trim(), dtf),
                                    new BigDecimal(fields[4].trim()),
                                    Currency.getInstance(fields[5].trim())
                            );
                    })
                    .collect(Collectors.toList());
        }
    }
}
