// CSE 373 Homework 6: The Rotating Dead
// YOU DO NOT NEED TO MODIFY THIS INSTRUCTOR-PROVIDED FILE.
// Your code should work properly with an unmodified version of this file.

/**
 * Interface Setrepresents an abstract data type (ADT) for a collection
 * of unique elements with no duplicates allowed.
 *
 * Classes implementing Set are generally expected to provide the core
 * operations of add, contains, and remove in O(1) or O(log N) average runtime.
 */
import java.util.Iterator;

public interface Set<E> extends Iterable<E> {
    /** @see Homework spec for details */
    void add(E element);

    /** @see Homework spec for details */
    void clear();

    /** @see Homework spec for details */
    boolean contains(E element);

    /** @see Homework spec for details */
    boolean isEmpty();

    /** @see Homework spec for details */
    Iterator<E> iterator();

    /** @see Homework spec for details */
    void remove(E element);

    /** @see Homework spec for details */
    int size();
}