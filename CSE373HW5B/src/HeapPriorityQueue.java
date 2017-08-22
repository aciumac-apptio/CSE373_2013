// TODO: Remove each 'todo' comment once I implement each part!

// TODO: class comment header

import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class HeapPriorityQueue<E> implements PriorityQueue<E> {
    private E[] elementData;
    private Comparator<E> comparator;
    private int size;

    // Creates an empty heap with a default capacity of 10
    public HeapPriorityQueue() {
        this(10, null);
    }

    // Finds integer of parent of an object located at a given index
    private int parent(int index) {
        return index / 2;
    }

    // Finds integer of left child of an object located at a given index
    private int leftChild(int index) {
        return 2 * index;
    }

    // Finds integer of right child of an object located at a given index
    private int rightChild(int index) {
        return 2 * index + 1;
    }

    // Returns true if an object located at a given index has a parent
    // Only root does not have a parent
    private boolean hasParent(int index) {
        return index > 1;
    }

    // Returns true if an object located at a given index has a left child
    private boolean hasLeftChild(int index) {
        return 2 * index <= size;
    }

    // Returns true if an object located at a given index has a left child
    private boolean hasRightChild(int index) {
        return 2 * index + 1 <= size;
    }

    // Swaps objects located at a given indices
    private void swap(E[] array, int index1, int index2) {
        E temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;
    }

    // Creates an empty heap with a given capacity and a comparator for objects passed in
    public HeapPriorityQueue(int capacity, Comparator<E> comparator) {
        if (capacity < 2) {
            throw new IllegalArgumentException();
        }

        this.comparator = comparator;
        elementData = (E[]) (new Object[capacity]);
        size = 0;
    }

    // Adds another object to the heap
    public void add(E value) {
        if (value == null) {
            throw new NullPointerException();
        }

        // check if resize is necessary
        if (size + 1 >= elementData.length) {
            elementData = Arrays.copyOf(elementData, elementData.length * 2);
        }
        elementData[size + 1] = value;

        int index = size + 1;
        boolean found = false;
        while (!found && hasParent(index)) {
            int parent = parent(index);

            // Check if child's value is less than that of its parent. If so percolate child up.
            if ((comparator != null && comparator.compare(elementData[index], elementData[parent]) < 0) ||
                    (comparator == null && ((Comparable<E>) elementData[index]).compareTo(elementData[parent]) < 0)) {
                swap(elementData, index, parent);
                index = parent(index);
            } else {
                found = true;
            }

        }
        size++;
    }

    // Removes all values from the heap
    public void clear() {
        elementData = (E[]) (new Object[10]);
        size = 0;
    }

    // Returns true if a given object is present in the heap
    public boolean contains(E value) {
        if (value == null) {
            throw new NullPointerException();
        }

        for (E elem : elementData) {
            if (elem != null && elem.equals(value)) {
                return true;
            }
        }

        return false;
    }

    // Returns true if there are no elements in the heap
    public boolean isEmpty() {
        return size == 0;
    }

    // Creates a new iterator object for our heap
    public Iterator<E> iterator() {
        return new HeapPriorityQueueIterator();
    }

    // Returns the first value (min) from the heap without removing it
    public E peek() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        return elementData[1];
    }

    // Removes the first value (min) from the heap
    public E remove() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        E result = elementData[1];
        elementData[1] = null;
        // Puts rightmost leaf into the root
        swap(elementData, 1, size);
        size--;

        int index = 1;
        boolean found = false;

        // Percolates down the new root until the heap stabilizes (parents have lower values than children)
        while (!found && hasLeftChild(index)) {
            int left = leftChild(index);
            int right = rightChild(index);
            int child = left;

            // Checking if right child has the least value out of the two children
            if (hasRightChild(index)) {
                if ((comparator != null && comparator.compare(elementData[right], elementData[left]) < 0)
                        || (comparator == null && ((Comparable<E>) elementData[right]).compareTo(elementData[left]) < 0)) {
                    child = right;
                }
            }

            // Checking if the child's value is less than that of a parent
            if ((comparator != null && comparator.compare(elementData[index], elementData[child]) > 0) ||
                    (comparator == null && ((Comparable<E>) elementData[index]).compareTo(elementData[child]) > 0)) {
                swap(elementData, index, child);
                index = child;
            } else {
                found = true;
            }
        }

        return result;
    }

    // Removes given value from the heap
    public void remove(E value) {
        if (value == null) {
            throw new NullPointerException();
        }

        if (contains(value)) {
            // find our value
            boolean found = false;
            int i = 1;
            while (!found) {
                if (elementData[i].equals(value)) {
                    found = true;
                } else {
                    i++;
                }
            }

            // bubble up to the root
            int index = i;
            while (index != 1) {
                int parent = parent(index);
                swap(elementData, index, parent);
                index = parent;
            }

            // remove from the root
            remove();
        }
    }

    // Returns number of elements in the heap
    public int size() {
        return size;
    }

    // Returns a text representation of the heap
    public String toString() {
        StringBuilder str = new StringBuilder("[");
        for (E elem : elementData) {
            if (elem != null) {
                str.append(elem + ", ");
            }
        }

        if (str.length() > 1) {
            str.setLength(str.length() - 2);
        }

        return str.append("]").toString();
    }

    // Traverses the elements of the heap starting from the root
    private class HeapPriorityQueueIterator implements Iterator<E> {
        private int index;

        public HeapPriorityQueueIterator() {
            this.index = 1;
        }

        // Returns true if there we have not reached the last element yet
        public boolean hasNext() {
            return index <= size;
        }

        // Returns next element of the heap
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            E result = elementData[index];
            index++;
            return result;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

    }

}