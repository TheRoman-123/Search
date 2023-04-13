package org.example;

public class TransactionTree {
    private Transaction transaction;

    private TransactionTree left;

    private TransactionTree right;

    public TransactionTree(Transaction... transactions) {
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
                this.right = new TransactionTree(transaction);
            } else {
                this.right.addTransaction(transaction);
            }
        } else if (transaction.getId() < this.transaction.getId()) {
            if (this.left == null) {
                this.left = new TransactionTree(transaction);
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

    public void removeTransaction(long id) {
        if (this.transaction == null) {
            throw new IllegalStateException("There is no Transaction with such id");
        }
        if (id > this.transaction.getId()) {
            if (this.right != null) {
                this.right.removeTransaction(id);
            }
        } else if (id < this.transaction.getId()) {
            if (this.left != null) {
                this.left.removeTransaction(id);
            }
        } else {
            if (this.left == null && this.right == null) {
                this.transaction = null;
            } else if (this.left == null) {
                this.transaction = this.right.transaction;
                this.left = this.right.left;
                this.right = this.right.right;
            } else if (this.right == null) {
                this.transaction = this.left.transaction;
                this.right = this.left.right;
                this.left = this.left.left;
            } else {
                TransactionTree leftMost = this.right.getLeftmost();
                this.transaction = leftMost.transaction;
                this.right.removeTransaction(leftMost.transaction.getId());
            }
        }
    }

    public TransactionTree getLeftmost() {
        if (this.left == null) {
            return this;
        }
        return this.left.getLeftmost();
    }

    public void preorderTraversal() {
        if (this.transaction == null) {
            return;
        }
        System.out.println(this.transaction);
        if (this.left != null) {
            this.left.preorderTraversal();
        }
        if (this.right != null) {
            this.right.preorderTraversal();
        }
    }

    public void inorderTraversal() {
        if (this.transaction == null) {
            return;
        }
        if (this.left != null) {
            this.left.inorderTraversal();
        }
        System.out.println(this.transaction);
        if (this.right != null) {
            this.right.inorderTraversal();
        }
    }

    public void postorderTraversal() {
        if (this.transaction == null) {
            return;
        }
        if (this.left != null) {
            this.left.postorderTraversal();
        }
        if (this.right != null) {
            this.right.postorderTraversal();
        }
        System.out.println(this.transaction);
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public TransactionTree getLeft() {
        return left;
    }

    public TransactionTree getRight() {
        return right;
    }
}

