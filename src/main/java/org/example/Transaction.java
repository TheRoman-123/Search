package org.example;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.Currency;
import java.util.Objects;


public class Transaction {
    private long id;
    private String senderCardNumber;
    private String recipientCardNumber;
    private ZonedDateTime dateTime; // in format "2011-12-03T10:15:30+01:00"
    private BigDecimal moneyAmount;
    private Currency currency;

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

    public long getId() {
        return id;
    }

    public String getSenderCardNumber() {
        return senderCardNumber;
    }

    public String getRecipientCardNumber() {
        return recipientCardNumber;
    }

    public ZonedDateTime getDateTime() {
        return dateTime;
    }

    public BigDecimal getMoneyAmount() {
        return moneyAmount;
    }

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
                "%s\t%s\t%s\t%s\t%s\t%s",
                id,
                senderCardNumber,
                recipientCardNumber,
                dateTime,
                moneyAmount,
                currency
        );
    }
}
