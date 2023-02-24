package org.example;

public class Tree {
    private Transaction transaction;

    private Tree left;

    private Tree right;

    public Tree(Transaction... transactions) {
        for (Transaction transaction : transactions) {
            this.addTransaction(transaction);
        }
    }

    public void addTransaction(Transaction transaction) {
        if (this.transaction == null) {
            this.transaction = transaction;
            return;
        }

        if (transaction.getId() > this.transaction.getId()) {
            if (this.right == null) {
                this.right = new Tree(transaction);
            } else {
                this.right.addTransaction(transaction);
            }
        }
        else if (transaction.getId() < this.transaction.getId()) {
            if (this.left == null) {
                this.left = new Tree(transaction);
            } else {
                this.left.addTransaction(transaction);
            }
        }
    }

    public Transaction findTransactionById(long id) {
        if (this.transaction == null) {
            throw new IllegalStateException("There is no Transaction with such id");
        }
        if (id > this.transaction.getId()) {
            return this.right.findTransactionById(id);
        } else if (id < this.transaction.getId()) {
            return this.left.findTransactionById(id);
        } else {
            return this.transaction;
        }
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public Tree getLeft() {
        return left;
    }

    public Tree getRight() {
        return right;
    }
}
