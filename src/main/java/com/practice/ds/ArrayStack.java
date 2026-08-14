package com.practice.ds;

import java.util.NoSuchElementException;

/** Fixed-capacity generic stack implemented with an array. */
public final class ArrayStack<T> {
    private final Object[] elements;
    private int size;

    public ArrayStack(int capacity) {
        if (capacity < 1) {
            throw new IllegalArgumentException("Capacity must be positive");
        }
        this.elements = new Object[capacity];
    }

    public void push(T value) {
        if (size == elements.length) {
            throw new IllegalStateException("Stack is full");
        }
        elements[size++] = value;
    }

    @SuppressWarnings("unchecked")
    public T pop() {
        if (isEmpty()) {
            throw new NoSuchElementException("Stack is empty");
        }
        T value = (T) elements[--size];
        elements[size] = null;
        return value;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }
}
