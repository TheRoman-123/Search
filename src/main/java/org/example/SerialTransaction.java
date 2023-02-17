package org.example;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Currency;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SerialTransaction {
    public void serialize(List<Transaction> transactions, Path filePath) {

    }

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
