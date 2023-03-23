package org.example;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public class Main {
    private static List<Transaction> transactionList;
    private static int countComparisons = 0;
    private static int countSwaps = 0;

    public static void main(String[] args) throws IOException {
        Path filePath = Path.of("D:\\Documents\\Java\\ASD\\transactions.csv");

        transactionList = SerialTransaction.deserialize(filePath);


        Transaction[] transactions = transactionList.toArray(new Transaction[0]);
        System.out.println("Bubble sort: best - O(n), ~ O(n^2), worst - O(n^2) ");

        long nanos = System.nanoTime();
        bubbleSort(transactions);
        System.out.println("Time: " + (System.nanoTime() - nanos) + " nanos");

        System.out.println("Amount of comparisons: " + countComparisons);
//        transactions.length * transactions.length / 2
        System.out.println("Amount of swaps: " + countSwaps);
        printTransactionArray(transactions);
        System.out.println();


        countComparisons = 0;
        countSwaps = 0;
        transactions = transactionList.toArray(new Transaction[0]);
        System.out.println("Merge sort: best - O(n*log(n)), ~ O(n*log(n)), worst - O(n*log(n)) ");

        nanos = System.nanoTime();
        mergeSort(transactions, 0, transactions.length - 1);
        System.out.println("Time: " + (System.nanoTime() - nanos) + " nanos");

        System.out.println("Amount of comparisons: " + countComparisons);
        System.out.println("Amount of swaps: " + countSwaps);
        printTransactionArray(transactions);
        System.out.println();


        countComparisons = 0;
        countSwaps = 0;
        transactions = transactionList.toArray(new Transaction[0]);
        System.out.println("Insertion sort: best - O(n), ~ O(n^2), worst - O(n^2) ");

        nanos = System.nanoTime();
        insertionSort(transactions);
        System.out.println("Time: " + (System.nanoTime() - nanos) + " nanos");

        System.out.println("Amount of comparisons: " + countComparisons);
        System.out.println("Amount of swaps: " + countSwaps);
        printTransactionArray(transactions);
        System.out.println();


        System.out.println();
    }

    private static void printTransactionArray(Transaction[] transactions) {
        for (Transaction transaction : transactions) {
            System.out.println(transaction);
        }
    }


    // Bubble sort
    private static void bubbleSort(Transaction[] transactions) {
        for (int i = 0; i < transactions.length; i++) {
            for (int j = i + 1; j < transactions.length; j++) {
                if (transactions[i].getMoneyAmount().compareTo(transactions[j].getMoneyAmount()) > 0) {
                    Transaction temp = transactions[i];
                    transactions[i] = transactions[j];
                    transactions[j] = temp;
                    ++countSwaps;
                }
                ++countComparisons;
            }
        }
    }

    
    // Merge sort
    private static void merge(Transaction[] arr, int leftIndex, int middleIndex, int rightIndex) {
        // Find sizes of two subarrays to be merged
        int leftLength = middleIndex - leftIndex + 1;
        int rightLength = rightIndex - middleIndex;

        /* Create temp arrays */
        Transaction[] left = new Transaction[leftLength];
        Transaction[] right = new Transaction[rightLength];

        /*Copy data to temp arrays*/
        System.arraycopy(arr, leftIndex, left, 0, leftLength);
        for (int j = 0; j < rightLength; ++j) {
            right[j] = arr[middleIndex + 1 + j];
        }

        /* Merge the temp arrays */

        // Initial indexes of first and second subarrays
        int i = 0, j = 0;

        // Initial index of merged subarray array
        int k = leftIndex;
        while (i < leftLength && j < rightLength) {
            if (left[i].getMoneyAmount().compareTo(right[j].getMoneyAmount()) < 0) {
                arr[k] = left[i];
                i++;
            }
            else {
                arr[k] = right[j];
                j++;
            }
            ++countSwaps;
            ++countComparisons;
            k++;
        }

        /* Copy remaining elements of left[] if any */
        while (i < leftLength) {
            arr[k] = left[i];
            i++;
            k++;
        }

        /* Copy remaining elements of right[] if any */
        while (j < rightLength) {
            arr[k] = right[j];
            j++;
            k++;
        }
    }

    // Main function that sorts arr[leftIndex..rightIndex] using
    // merge()
    private static void mergeSort(Transaction[] arr, int leftIndex, int rightIndex)
    {
        if (leftIndex < rightIndex) {
            // Find the middle point
            int middleIndex = leftIndex + (rightIndex - leftIndex) / 2;

            // Sort first and second halves
            mergeSort(arr, leftIndex, middleIndex);
            mergeSort(arr, middleIndex + 1, rightIndex);

            // Merge the sorted halves
            merge(arr, leftIndex, middleIndex, rightIndex);
        }
    }


    // Insertion sort
    private static void insertionSort(Transaction[] arr) {
        int n = arr.length;
        for (int i = 1; i < n; ++i) {
            Transaction key = arr[i];
            int j = i - 1;

            /* Move elements of arr[0..i-1], that are
               greater than key, to one position ahead
               of their current position */
            ++countComparisons;
            while (j >= 0 && arr[j].getMoneyAmount().compareTo(key.getMoneyAmount()) > 0) {
                arr[j + 1] = arr[j];
                --j;
                ++countSwaps;
                ++countComparisons;
            }
            arr[j + 1] = key;
        }
    }
}