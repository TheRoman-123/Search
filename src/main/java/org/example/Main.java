package org.example;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public class Main {
    private static List<Transaction> transactionList;

    public static void main(String[] args) throws IOException {
        Path filePath = Path.of("D:\\Documents\\Java\\ASD\\transactions.csv");

        transactionList = SerialTransaction.deserialize(filePath);

        transactionList.forEach(System.out::println);

        System.out.println(Transaction.emptyTransaction());

        Transaction[] transactions = transactionList.toArray(new Transaction[0]);
    }
}