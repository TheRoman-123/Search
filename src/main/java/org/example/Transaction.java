package org.example;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Currency;
import java.util.Objects;


/**
 * Represents a financial transaction between two parties, consisting of an ID, sender and recipient card numbers,
 * date and time, amount of money, and currency. This class provides methods for creating new transactions,
 * copying them, and retrieving transaction details.
 */
public class Transaction {

    /** Inner class providing an empty transaction, used as a default value. */
    private static class EmptyTransaction {
        public static final Transaction emptyTransaction = new Transaction();
    }
    /** The unique identifier for this transaction. */
    private final long id;
    /** The card number of the sender for this transaction. */
    private final String senderCardNumber;
    /** The card number of the recipient for this transaction. */
    private final String recipientCardNumber;
    /** The date and time this transaction occurred, represented as a ZonedDateTime object. */
    private final ZonedDateTime dateTime; // in format "2011-12-03T10:15:30"
    /** The amount of money transferred in this transaction, represented as a BigDecimal object. */
    private final BigDecimal moneyAmount;
    /** The currency of the money transferred in this transaction, represented as a Currency object. */
    private final Currency currency;

    /**
     * Creates a new Transaction object with the specified values.
     * @param id                    The unique identifier for this transaction.
     * @param senderCardNumber      The card number of the sender for this transaction.
     * @param recipientCardNumber   The card number of the recipient for this transaction.
     * @param dateTime              The date and time this transaction occurred, represented as a ZonedDateTime object.
     * @param moneyAmount           The amount of money transferred in this transaction, represented as a BigDecimal object.
     * @param currency              The currency of the money transferred in this transaction, represented as a Currency object.
     */
    public Transaction(long id,
                       String senderCardNumber,
                       String recipientCardNumber,
                       ZonedDateTime dateTime,
                       BigDecimal moneyAmount,
                       Currency currency) {
        this.id = id;
        this.senderCardNumber = senderCardNumber;
        this.recipientCardNumber = recipientCardNumber;
        this.dateTime = dateTime;
        this.moneyAmount = moneyAmount;
        this.currency = currency;
    }

    /**
     * Creates a new Transaction object that is a copy of an existing Transaction.
     * @param transaction The Transaction to be copied.
     */
    public Transaction(Transaction transaction) {
        this.id = transaction.getId();
        this.senderCardNumber = transaction.getSenderCardNumber();
        this.recipientCardNumber = transaction.getRecipientCardNumber();
        this.dateTime = transaction.getDateTime();
        this.moneyAmount = transaction.getMoneyAmount();
        this.currency = transaction.getCurrency();
    }

    /** Private constructor used to create an empty Transaction object. */
    private Transaction() {
        this.id = 0;
        this.senderCardNumber = "0000000000000000";
        this.recipientCardNumber = "111111111111111111";
        this.dateTime = ZonedDateTime.ofInstant(Instant.ofEpochSecond(0), ZoneId.of("UTC"));
        this.moneyAmount = new BigDecimal(1);
        this.currency = Currency.getInstance("USD");
    }

    /**
     * Returns an empty Transaction object.
     * @return An empty Transaction object.
     */
    public static Transaction emptyTransaction() {
        return EmptyTransaction.emptyTransaction;
    }

    /**
     * Returns the unique identifier for this transaction.
     * @return The unique identifier for this transaction.
     */
    public long getId() {
        return id;
    }

    /**
     * Returns the card number of the sender for this transaction.
     * @return The card number of the sender for this transaction.
     * */
    public String getSenderCardNumber() {
        return senderCardNumber;
    }

    /**
     * Returns the card number of the recipient for this transaction.
     * @return The card number of the recipient for this transaction.
     * */
    public String getRecipientCardNumber() {
        return recipientCardNumber;
    }

    /**
     * Returns the date and time of this transaction.
     * @return the date and time of this transaction.
     */
    public ZonedDateTime getDateTime() {
        return dateTime;
    }

    /**
     * Returns the amount of money involved in this transaction.
     * @return the amount of money involved in this transaction
     */
    public BigDecimal getMoneyAmount() {
        return moneyAmount;
    }

    /**
     * Returns the currency used in this transaction.
     * @return the currency used in this transaction
     */
    public Currency getCurrency() {
        return currency;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return id == that.id &&
                senderCardNumber.equals(that.senderCardNumber) &&
                recipientCardNumber.equals(that.recipientCardNumber) &&
                dateTime.equals(that.dateTime) &&
                moneyAmount.equals(that.moneyAmount) &&
                currency.equals(that.currency);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return String.format(
                "%s,%s,%s,%s,%s,%s",
                id,
                senderCardNumber,
                recipientCardNumber,
                dateTime.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME.withZone(ZoneId.of("UTC"))),
                moneyAmount,
                currency
        );
    }
}
