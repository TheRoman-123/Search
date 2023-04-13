package org.example;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Collections;
import java.util.List;

public class Main {
    private static List<Transaction> transactionList;

    public static void main(String[] args) throws IOException {
        Path filePath = Path.of("D:\\Documents\\Java\\ASD\\transactions.csv");

        transactionList = SerialTransaction.deserialize(filePath);
        Collections.shuffle(transactionList);
        Transaction[] transactions = transactionList.toArray(new Transaction[0]);

//        MyStack<Transaction> stack1 = new MyStack<>(transactions);
//        MyStack<Transaction> stack2 = new MyStack<>(Transaction.emptyTransaction());
//        MyStack<Transaction> stack3 = new MyStack<>();
//        MyStack<Transaction> stack4 = new MyStack<>(transactionList);
//
//        stack1.forEach(System.out::println);
//        System.out.println();
//        stack2.forEach(System.out::println);
//        System.out.println();
//        stack3.forEach(System.out::println);
//        System.out.println();
//        stack4.forEach(System.out::println);
//        System.out.println("\n\n");
//
//        System.out.println(stack1.pop() + "\n");
//        stack1.forEach(System.out::println);
//        System.out.println();
//        System.out.println(stack1.peek());
//        System.out.println();
//        stack1.push(Transaction.emptyTransaction());
//        stack1.push(Transaction.emptyTransaction());
//        stack1.forEach(System.out::println);
//        System.out.println();

        /*Tree<Transaction> transactionTree = new Tree<>(
                transactionList,
                Comparator.comparing(Transaction::getMoneyAmount)
        );
        transactionTree.printPreOrder();
        transactionTree.printPostOrder();
        transactionTree.printInOrder();

        transactionTree.add(Transaction.emptyTransaction());
        System.out.println(transactionTree.contains(Transaction.emptyTransaction()));
        transactionTree.remove(Transaction.emptyTransaction());
        System.out.println(transactionTree.contains(Transaction.emptyTransaction()));*/
        TransactionTree transactionTree = new TransactionTree(transactions);
        transactionTree.preorderTraversal();
        System.out.println();
        transactionTree.inorderTraversal();
        System.out.println();
        transactionTree.postorderTraversal();
        System.out.println();
    }
}