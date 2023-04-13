package org.example;

import java.util.Collection;
import java.util.Comparator;

public class Tree<T> {
    private final Comparator<T> comparator;
    private Node<T> root;

    public Tree(Comparator<T> comparator) {
        this.root = null;
        this.comparator = comparator;
    }

    public Tree(Collection<T> collection, Comparator<T> comparator) {
        this.comparator = comparator;
        collection.forEach(this::add);
    }

    public void add(T data) {
        root = addRecursive(root, data);
    }

    private Node<T> addRecursive(Node<T> current, T data) {
        if (current == null) {
            return new Node<>(data);
        }

        if (compare(current.data, data) > 0) {
            current.left = addRecursive(current.left, data);
        } else if (compare(current.data, data) < 0) {
            current.right = addRecursive(current.right, data);
        } else {
            // Data already exists in the tree.
            return current;
        }

        return current;
    }

    public void remove(T data) {
        root = removeRecursive(root, data);
    }

    private Node<T> removeRecursive(Node<T> current, T data) {
        if (current == null) {
            return null;
        }

        if (compare(current.data, data) == 0) {
            // Node to be deleted has no children.
            if (current.left == null && current.right == null) {
                return null;
            }

            // Node to be deleted has only one child.
            if (current.right == null) {
                return current.left;
            }

            if (current.left == null) {
                return current.right;
            }

            // Node to be deleted has two children.
            T smallestValue = findSmallestValue(current.right);
            current.data = smallestValue;
            current.right = removeRecursive(current.right, smallestValue);
            return current;
        }

        if (compare(current.data, data) > 0) {
            current.left = removeRecursive(current.left, data);
            return current;
        }

        current.right = removeRecursive(current.right, data);
        return current;
    }

//    public Transaction findBy() {
//        comparator.
//        return findByRecursive(root, supplier);
//    }
//
//    private <E> Transaction findByRecursive(Node<T> current, Supplier<E> supplier) {
//        if (current == null) {
//            return null;
//        }
//
//        return compare(current.data, data) > 0
//                ? containsRecursive(current.left, data)
//                : containsRecursive(current.right, data);
//    }

    public boolean contains(T data) {
        return containsRecursive(root, data);
    }

    private boolean containsRecursive(Node<T> current, T data) {
        if (current == null) {
            return false;
        }

        if (compare(current.data, data) == 0) {
            return true;
        }

        return compare(current.data, data) > 0
                ? containsRecursive(current.left, data)
                : containsRecursive(current.right, data);
    }

    private int compare(T a, T b) {
        if (a == null && b == null) {
            return 0;
        }

        if (a == null) {
            return -1;
        }

        if (b == null) {
            return 1;
        }

        return comparator.compare(a, b);
    }

    private T findSmallestValue(Node<T> root) {
        return root.left == null ? root.data : findSmallestValue(root.left);
    }

    public void printPreOrder() {
        System.out.println("PreOrder Traversal:");
        printPreOrderRecursive(root);
        System.out.println();
    }

    private void printPreOrderRecursive(Node<T> node) {
        if (node != null) {
            System.out.println(node.data);
            printPreOrderRecursive(node.left);
            printPreOrderRecursive(node.right);
        }
    }

    public void printInOrder() {
        System.out.println("InOrder Traversal:");
        printInOrderRecursive(root);
        System.out.println();
    }

    private void printInOrderRecursive(Node<T> node) {
        if (node != null) {
            printInOrderRecursive(node.left);
            System.out.println(node.data);
            printInOrderRecursive(node.right);
        }
    }

    public void printPostOrder() {
        System.out.println("PostOrder Traversal:");
        printPostOrderRecursive(root);
        System.out.println();
    }

    private void printPostOrderRecursive(Node<T> node) {
        if (node != null) {
            printPostOrderRecursive(node.left);
            printPostOrderRecursive(node.right);
            System.out.println(node.data);
        }
    }

    private static class Node<T> {
        private T data;
        private Node<T> left, right;

        private Node(T data) {
            this.data = data;
            left = null;
            right = null;
        }
    }
}
