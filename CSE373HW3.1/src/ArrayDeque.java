// CSE 373 HW3 Part B

import java.util.*;

public class ArrayDeque<E> implements Deque<E> {
    private E[] deque;
    private int front;
    private int size;

    // Constructs a deque object
    public ArrayDeque() {
        this.deque = (E[]) (new Object[10]);
        this.front = 0;
        this.size = 0;
    }

    // Adds an element at to the front of the deque
    public void addFirst(E element) {
        if (element == null) {
            throw new NullPointerException();
        }

        if (size == deque.length) {
            resize();
        }

        if (!isEmpty()) {
            if (front == 0) {
                front = deque.length - 1;
            } else {
                front--;
            }
        }

        this.deque[front] = element;
        size++;
    }

    // Adds an element at the end of the deque
    public void addLast(E element) {
        if (element == null) {
            throw new NullPointerException();
        }

        if (size == deque.length) {
            resize();
        }

        this.deque[(front + size) % deque.length] = element;
        size++;
    }

    // Erases all elements (makes deque empty)
    public void clear() {
        this.front = 0;
        this.size = 0;
        this.deque = (E[]) (new Object[10]);
    }

    // Returns true if there are no elements in the deque left
    public boolean isEmpty() {
        return size == 0;
    }

    // Creates an iterator object for deque
    public Iterator<E> iterator() {
        return new ArrayDequeIterator();
    }

    // Returns front element of deque without removing it
    public E peekFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        return this.deque[front];
    }

    // Returns element from the end of deque without removing it
    public E peekLast() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        return this.deque[(front + size) % deque.length - 1];
    }

    // Removes and returns front element of deque
    public E removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        if (front >= deque.length) {
            front = 0;
        }

        E elem = this.deque[front];
        this.deque[front] = null;
        front++;
        size--;

        return elem;
    }

    // Resizes the deque when it is full
    private void resize() {
        E[] temp = (E[]) (new Object[2 * size]);

        for (int i = front; i < front + size; i++) {
            temp[i - front] = deque[i % deque.length];
        }

        this.deque = temp;
        this.front = 0;
    }

    // Removes and returns last element of deque
    public E removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        E elem = this.deque[(front + size) % deque.length - 1];
        this.deque[(front + size) % deque.length - 1] = null;
        size--;
        return elem;
    }

    // Returns the number of elements in deque
    public int size() {
        return this.size;
    }

    // Returns a text representation of elements of deque
    public String toString() {
        String sum = "[";
        for (int i = front; i < front + size; i++) {
            if (deque[i % deque.length] != null) {
                sum += deque[i % deque.length] + ", ";
            }
        }

        if (sum.equals("[")) {
            return "[]";
        } else {
            return sum.substring(0, sum.length() - 2) + "]";
        }

    }

    // Traverses elements of deque from front to back
    private class ArrayDequeIterator implements Iterator<E> {
        private int index;

        public ArrayDequeIterator() {
            index = front;
        }

        public boolean hasNext() {
            if (index >= deque.length) {
                index = index % deque.length;
            }

            return deque[index] != null;
        }

        public E next() {
            if (index >= deque.length) {
                index = index % deque.length;
            }

            E result = deque[index];
            index++;
            return result;
        }

        /**
         * Removes the most recently returned element.
         * Not supported. Throws an UnsupportedOperationException when called.
         */
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}