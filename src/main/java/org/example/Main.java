package org.example;

// Требуется создать текстовый файл который содержит 50 записей.
// Каждая запись имеет минимум 5 полей с минимум 2-мя типами данных.
// Ключевое поле должно быть уникальным. Записи не упорядочены.
// Тематика структуры данных определяется каждым студентом совместно с преподавателем на лабораторных занятиях.

// Требуется реализовать в C++ или Java программу в которой внедрены 4 метода поиска
// в отсортированных и неотсортированных массивах по ключевому полю в текстовом файле
// созданном ранее. Для каждого метода поиска требуется провести анализ среднего теоретического
// времени работы и вывод на экран практического времени поиска. Описать по шагам алгоритмы методов поиска.

import java.io.IOException;
import java.nio.file.Path;
import java.time.Instant;
import java.util.List;

public class Main {
    private static List<Transaction> transactionList;

    public static void main(String[] args) throws IOException, InterruptedException {
        Path filePath = Path.of("D:\\Documents\\Java\\ASD\\transactions.csv");

        transactionList = SerialTransaction.deserialize(filePath);

        transactionList.forEach(System.out::println);

        Transaction[] transactions = transactionList.toArray(new Transaction[0]);

        long millis = System.nanoTime();
        System.out.println(linearSearch(transactions, 44));
        System.out.println(System.nanoTime() - millis);
        System.out.println();

        millis = System.nanoTime();
        System.out.println(binarySearch(transactions, 44));
        System.out.println(System.nanoTime() - millis);
        System.out.println();

        millis = System.nanoTime();
        System.out.println(recursiveBinarySearch(transactions, 0, transactions.length, 44));
        System.out.println(System.nanoTime() - millis);
        System.out.println();

        millis = System.nanoTime();
        System.out.println(interpolationSearch(transactions, 44));
        System.out.println(System.nanoTime() - millis);
        System.out.println();

        millis = System.nanoTime();
        System.out.println(fibbonacciSearch(transactions, 44));
        System.out.println(System.nanoTime() - millis);
        System.out.println();
    }

    // Returns index of found element
    public static int linearSearch(Transaction[] array, long id) {
        for (int i = 0; i < array.length; i++) {
            if (array[i].getId() == id) {
                return i;
            }
        }
        return -1;
    }

    // The array must be sorted after id
    public static int binarySearch(Transaction[] array, long id) {

        int firstIndex = 0;
        int lastIndex = array.length - 1;

        // условие прекращения (элемент не представлен)
        while (firstIndex <= lastIndex) {
            int middleIndex = (firstIndex + lastIndex) / 2;
            // если средний элемент - целевой элемент, вернуть его индекс
            if (array[middleIndex].getId() == id) {
                return middleIndex;
            }

            // если средний элемент меньше
            // направляем наш индекс в middle+1, убирая первую часть из рассмотрения
            else if (array[middleIndex].getId() < id) {
                firstIndex = middleIndex + 1;
            }

            // если средний элемент больше
            // направляем наш индекс в middle-1, убирая вторую часть из рассмотрения
            else if (array[middleIndex].getId() > id) {
                lastIndex = middleIndex - 1;
            }
        }
        return -1;
    }

    // The array must be sorted after id
    public static int recursiveBinarySearch(Transaction[] array, int firstElement, int lastElement, long id) {

        // условие прекращения
        if (lastElement >= firstElement) {
            int mid = firstElement + (lastElement - firstElement) / 2;

            // если средний элемент - целевой элемент, вернуть его индекс
            if (array[mid].getId() == id) {
                return mid;
            }

            // если средний элемент больше целевого
            // вызываем метод рекурсивно по суженным данным
            if (array[mid].getId() > id) {
                return recursiveBinarySearch(array, firstElement, mid - 1, id);
            }

            // также, вызываем метод рекурсивно по суженным данным
            return recursiveBinarySearch(array, mid + 1, lastElement, id);
        }

        return -1;
    }

    // Array must be sorted after id
    public static int interpolationSearch(Transaction[] array, long id) {

        int startIndex = 0;
        int lastIndex = (array.length - 1);

        while ((startIndex <= lastIndex) && (id >= array[startIndex].getId()) &&
                (id <= array[lastIndex].getId())) {
            // используем формулу интерполяции для поиска возможной лучшей позиции для существующего элемента
            int pos = (int) (startIndex + (((lastIndex - startIndex) /
                    (array[lastIndex].getId() - array[startIndex].getId())) *
                    (id - array[startIndex].getId())));

            if (array[pos].getId() == id) {
                return pos;
            }

            if (array[pos].getId() < id) {
                startIndex = pos + 1;
            } else {
                lastIndex = pos - 1;
            }
        }
        return -1;
    }

    public static int fibbonacciSearch(Transaction[] array, long id) throws InterruptedException {
        int firstIndex = 0;
        int lastIndex = array.length - 1;
        double fibIndex = 1.618;

        // условие прекращения (элемент не представлен)
        while (firstIndex <= lastIndex) {
            int middleIndex = (int) ((firstIndex + lastIndex) / fibIndex);
            if (middleIndex >= lastIndex) {
                fibIndex = 2;
            }

            if (array[middleIndex].getId() == id) {
                return middleIndex;
            }

            else if (array[middleIndex].getId() < id) {
                firstIndex = middleIndex;
            }

            else if (array[middleIndex].getId() > id) {
                lastIndex = middleIndex;
            }
        }
        return -1;
    }
}