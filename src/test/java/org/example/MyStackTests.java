package org.example;

import org.junit.Before;
import org.junit.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public class MyStackTests {
    private MyStack<Integer> stack;

    @Before
    public void setUp() {
        stack = new MyStack<>(1, 2, 3, 4, 5);
    }

    @Test
    public void testSize() {
        assertEquals(5, stack.size());
        stack.pop();
        assertEquals(4, stack.size());
    }

    @Test
    public void testIsEmpty() {
        assertFalse(stack.isEmpty());
        stack.pop();
        stack.pop();
        stack.pop();
        stack.pop();
        stack.pop();
        assertTrue(stack.isEmpty());
    }

    @Test
    public void testPeek() {
        assertEquals(Integer.valueOf(5), stack.peek());
        stack.pop();
        assertEquals(Integer.valueOf(4), stack.peek());
        stack.pop();
        assertEquals(Integer.valueOf(3), stack.peek());
        stack.pop();
        assertEquals(Integer.valueOf(2), stack.peek());
        stack.pop();
        assertEquals(Integer.valueOf(1), stack.peek());
        stack.pop();
        assertThrows(
                NoSuchElementException.class, () -> stack.peek(),
                "Expected NoSuchElementException to be thrown"
        );
    }

    @Test
    public void testPush() {
        stack.push(6);
        assertEquals(6, stack.size());
        assertEquals(Integer.valueOf(6), stack.peek());
    }

    @Test
    public void testPop() {
        assertEquals(Integer.valueOf(5), stack.pop());
        assertEquals(Integer.valueOf(4), stack.pop());
        assertEquals(Integer.valueOf(3), stack.pop());
        assertEquals(Integer.valueOf(2), stack.pop());
        assertEquals(Integer.valueOf(1), stack.pop());
        assertThrows(
                NoSuchElementException.class, () -> stack.pop(),
                "Expected NoSuchElementException to be thrown"
        );
    }

    @Test
    public void testIterator() {
        int expectedValue = 5;
        for (int value : stack) {
            assertEquals(Integer.valueOf(expectedValue), Integer.valueOf(value));
            --expectedValue;
        }
    }
}
