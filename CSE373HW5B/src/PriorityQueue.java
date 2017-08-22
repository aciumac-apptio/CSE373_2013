// CSE 373 Homework 5: The Even More Amazing Heap
// YOU DO NOT NEED TO MODIFY THIS INSTRUCTOR-PROVIDED FILE.
// Your code should work properly with an unmodified version of this file.

// This interface defines an ADT for priority queues of integers
// that allow fast access/removal of the minimum element.

import java.util.Iterator;

/**
 * Interface PriorityQueue represents an abstract data type (ADT) for a map storing
 * a queue of elements that can be accessed/removed in ascending order,
 * giving fast access to the minimum element.
 *
 * Classes implementing PriorityQueue are generally expected to provide the operations
 * below in O(1) or O(log N) average runtime, except for the clear, contains,
 * and toString operations, which are often O(N).
 */
public interface PriorityQueue<E> extends Iterable<E> {
    // (Comments are intentionally omitted from these abstract method headings,
    //  because we want you to write comments for these methods in your own
    //  words in your HeapPriorityQueue class.)

    /** @see Homework spec for details */
    void add(E value);

    /** @see Homework spec for details */
    void clear();

    /** @see Homework spec for details */
    boolean contains(E value);

    /** @see Homework spec for details */
    boolean isEmpty();

    /** @see Homework spec for details */
    Iterator<E> iterator();

    /** @see Homework spec for details */
    E peek();

    /** @see Homework spec for details */
    E remove();

    /** @see Homework spec for details */
    void remove(E value);

    /** @see Homework spec for details */
    int size();

    /** @see Homework spec for details */
    String toString();
}