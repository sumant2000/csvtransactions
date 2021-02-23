package com.assignment.csv.model;

import java.util.Date;
import java.util.Objects;

public class Transaction {

    public static final String PAYMENT ="PAYMENT" ;
    public static final String REVERSAL ="REVERSAL" ;
    String id;
    Date date;
    double amount;
    String merchant;
    String type;
    String relatedTransaction;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getMerchant() {
        return merchant;
    }

    public void setMerchant(String merchant) {
        this.merchant = merchant;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRelatedTransaction() {
        return relatedTransaction;
    }

    public void setRelatedTransaction(String relatedTransaction) {
        this.relatedTransaction = relatedTransaction;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Transaction)) return false;
        Transaction that = (Transaction) o;
        return Double.compare(that.getAmount(), getAmount()) == 0 &&
                getId().equals(that.getId()) &&
                getDate().equals(that.getDate()) &&
                getMerchant().equals(that.getMerchant()) &&
                getType().equals(that.getType()) &&
                getRelatedTransaction().equals(that.getRelatedTransaction());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getDate(), getAmount(), getMerchant(), getType(), getRelatedTransaction());
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id='" + id + '\'' +
                ", date=" + date +
                ", amount=" + amount +
                ", merchant='" + merchant + '\'' +
                ", type='" + type + '\'' +
                ", relatedTransaction='" + relatedTransaction + '\'' +
                '}';
    }
}
