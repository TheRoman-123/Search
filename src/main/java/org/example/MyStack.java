package org.example;

import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A generic stack implementation with push, pop, peek, size, isEmpty,
 * and iterable features. The MyStack class uses a linked list to implement
 * the stack data structure.
 * <p>Please see the {@link org.example.MyStack} class for true identity
 *
 * @param <T> the type of elements in this stack
 * @author Roman Rudi
 */
public class MyStack<T> implements Iterable<T> {
    /**
     * Pointer to last node.
     */
    private Node<T> tail;
    /**
     * Size of stack.
     */
    private int size;

    /**
     * Constructs a new stack with the specified objects. The objects are pushed onto
     * the stack in the order they are passed to the constructor.
     *
     * @param objects the objects to be added to this stack
     */
    @SafeVarargs
    public MyStack(T... objects) {
        for (T object : objects) {
            push(object);
        }
    }

    /**
     * Constructs a new stack with the specified collection. The elements of the collection
     * are added to the stack in the order returned by the collection's iterator.
     *
     * @param collection the collection whose elements are to be added to this stack
     */
    public MyStack(Collection<? extends T> collection) {
        collection.forEach(this::push);
    }

    /**
     * Returns the number of elements in this stack.
     *
     * @return the number of elements in this stack
     */
    public int size() {
        return size;
    }

    /**
     * Returns true if this stack contains no elements.
     *
     * @return true if this stack contains no elements
     */
    public boolean isEmpty() {
        return size <= 0;
    }

    /**
     * Returns the element at the top of this stack without removing it.
     *
     * @return the element at the top of this stack
     * @throws NoSuchElementException if this stack is empty
     */
    public T peek() {
        if (size <= 0)
            throw new NoSuchElementException("Stack is empty");
        return tail.element;
    }

    /**
     * Pushes the specified element onto the top of this stack.
     *
     * @param newElem the element to be pushed onto this stack
     */
    public void push(T newElem) {
        Node<T> newTail = new Node<>(newElem);
        if (tail != null) {
            tail.next = newTail;
            newTail.previous = tail;
        }
        tail = newTail;
        ++size;
    }

    /**
     * Removes and returns the element at the top of this stack.
     *
     * @return the element at the top of this stack
     * @throws NoSuchElementException if this stack is empty
     */
    public T pop() {
        if (size <= 0)
            throw new NoSuchElementException("Stack is empty");
        T retElement = tail.element;
        if (tail.previous != null) {
            tail.previous.next = null;
        }
        tail = tail.previous;
        --size;
        return retElement;
    }

    /**
     * Adds all the elements in the specified collection to the top of this stack.
     *
     * @param collection the collection whose elements are to be added to this stack.
     */
    public void addAll(Collection<? extends T> collection) {
        collection.forEach(this::push);
    }

    /**
     * Adds all the elements in the specified array to the top of this stack.
     *
     * @param objects the array whose elements are to be added to this stack.
     */
    public void addAll(T[] objects) {
        for (T object : objects) {
            push(object);
        }
    }

    /**
     * Returns an iterator over the elements in this stack.
     * The iterator iterates over the elements in LIFO order, starting from the top of the stack.
     *
     * @return an iterator over the elements in this stack.
     */
    @Override
    public Iterator<T> iterator() {
        return new StackIterator();
    }

    /**
     * A private inner Node class for the linked list that stores the element
     * in the stack. Each node contains a reference to the previous and next nodes,
     * as well as an element of type T.
     *
     * @param <T> the type of elements in this node
     */
    private static class Node<T> {
        /**
         * The element stored in this node.
         */
        private final T element;
        /**
         * The previous node in the linked list.
         */
        private Node<T> previous;
        /**
         * The next node in the linked list.
         */
        private Node<T> next;

        /**
         * Constructs a node with the given element and null previous and next references.
         *
         * @param element the element to be stored in this node
         */
        public Node(T element) {
            this.element = element;
        }
    }

    /**
     * A private inner class that represents an iterator over the elements of the stack.
     */
    private class StackIterator implements Iterator<T> {
        /**
         * The number of elements remaining to be iterated over.
         */
        private int remaining = size();
        /**
         * The node that will be returned by the next call to next().
         */
        private Node<T> next = tail;

        /**
         * Returns true if there are more elements to be iterated over.
         *
         * @return true if there are more elements to be iterated over, false otherwise.
         */
        @Override
        public boolean hasNext() {
            return remaining > 0;
        }

        /**
         * Returns the next element in the iteration.
         *
         * @return the next element in the iteration.
         * @throws NoSuchElementException if there are no more elements to be iterated over.
         */
        @Override
        public T next() {
            if (remaining <= 0)
                throw new NoSuchElementException();
            T current = next.element;
            next = next.previous;
            --remaining;
            return current;
        }

        /**
         * Removes the current element from the iteration.
         */
        @Override
        public void remove() {

        }
    }
}
