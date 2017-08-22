// TODO: Remove each 'todo' comment once I implement each part!

// TODO: class comment header

import java.util.Iterator;

public class TreeSet<E extends Comparable<E>> implements Set<E> {
    private TreeMap<E, Boolean> data;

    // Constructs a tree set
    public TreeSet() {
        this.data = new TreeMap<>();
    }

    // Adds a new element to the set
    // Throws NullPointerException if element is null
    public void add(E element) {
        data.put(element, true);
    }

    // Removes all values from the set
    public void clear() {
        this.data = new TreeMap<>();
    }

    // Returns true if element is in the set
    // Throws NullPointerException if element is null
    public boolean contains(E element) {
        return data.containsKey(element);
    }

    // Returns true if set has no elements
    public boolean isEmpty() {
        return data.size() == 0;
    }

    // Returns an iterator object for the Set
    public Iterator<E> iterator() {
        return data.keySet().iterator();
    }

    // Removes a given element from our set, if present
    // Throws NullPointerException if element is null
    public void remove(E element) {
        data.remove(element);
    }

    // Returns number of elements in the set
    public int size() {
        return data.size();
    }

    // Returns a string representation of the set
    public String toString() {
        java.util.Set<E> keys = data.keySet();
        StringBuilder str = new StringBuilder("[");
        for(E elem : keys) {
            str.append(elem + ", ");
        }

        if (str.length() > 1) {
            str.setLength(str.length() - 2);
        }

        return str.append("]").toString();
    }
}