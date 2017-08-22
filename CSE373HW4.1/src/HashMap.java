// TODO: Remove each 'todo' comment once I implement each part!

// TODO: class comment header
//package A;

import java.util.HashSet;
import java.util.Set;

public class HashMap<K, V> implements Map<K, V> {
    // TODO: declare my private fields here
    private static final double MAX_LOAD = 0.75;

    private Node[] elements;
    private int size;

    // TODO: comment header
    public HashMap() {
        // TODO: implement the constructor
        this.elements = (Node[]) new HashMap.Node[10];
        this.size = 0;
    }

    public void clear() {
        // TODO: implement this method
        this.elements = (Node[]) new HashMap.Node[10];
        this.size = 0;
    }

    // done
    public boolean containsKey(K key) {
        // TODO: implement this method
        return containsKey(key, elements);
    }

    private boolean containsKey(K key, Node[] arr) {
        if (key == null) {
            throw new NullPointerException();
        }

        int index = hash(key, arr);
        if (arr[index] != null) {
            Node current = arr[index];
            while(current != null) {
                if (current.key.equals(key)) {
                    return true;
                }
                current = current.next;
            }
        }
        return false;
    }

    // done
    public V get(K key) {
        // TODO: implement this method
        if (key == null) {
            throw new NullPointerException();
        }

        int index = hash(key, elements);
        Node current = elements[index];
        while (current != null) {
            if (current.key.equals(key)) {
                return current.value;
            }
            current = current.next;
        }

        return null;
    }

    public boolean isEmpty() {
        // TODO: implement this method
        return size == 0;
    }

    public Set<K> keySet() {
        // TODO: implement this method
        HashSet<K> keys = new HashSet<K>();
        for (Node n: elements) {
            Node current = n;
            while (current != null) {
                keys.add(current.key);
                current = current.next;
            }
        }

        return keys;
    }

    // dublicate key: done
    public void put(K key, V value) {
        // TODO: implement this method
        put(key, value, elements);

        if ((double) size/elements.length > MAX_LOAD) {
            rehash();
        }
    }

    private void put(K key, V value, Node[] arr){
        if (key == null || value == null) {
            throw new NullPointerException();
        }

        int index = hash(key, arr);
        if (arr[index] == null) {
            arr[index] = new Node(key, value);
            size++;
        } else if (containsKey(key,arr)) {
            Node current = arr[index];
            while(!current.key.equals(key)){
                current = current.next;
            }

            current.value = value;
        }
        else {
            Node temp = arr[index];
            arr[index] = new Node(key, value);
            arr[index].next = temp;
            size++;
        }
    }

    private int hash(K value, Node[] arr) {
        if (value == null) {
            throw new NullPointerException();
        }

        return Math.abs(value.hashCode()) % arr.length;
    }

    public void remove(K key) {
        // TODO: implement this method
        // just front of list
        if (key == null) {
            throw new NullPointerException();
        }

        int index = hash(key, elements);
        if (elements[index] != null) {
            // Front of the list
            if (elements[index].key.equals(key)) {
                elements[index] = elements[index].next;
                size--;
                // End and middle of list
            } else if(containsKey(key)) {
                Node current = elements[index];
                while (current != null && !current.next.key.equals(key)) {
                    current = current.next;
                }

                // Special situation?
                current.next = current.next.next;
                size--;
            }
        }
    }

    private void rehash() {
        Node[] temporary = (Node[]) new HashMap.Node[elements.length*2];
        this.size = 0;

        for (Node n : elements) {
            if (n != null) {
                Node current = n;
                while (current != null) {
                    put(current.key, current.value, temporary);
                    current = current.next;
                }
            }
        }
        elements = temporary;
    }

    public int size() {
        // TODO: implement this method
        return size;
    }

    public String toString() {
        // TODO: implement this method
        StringBuilder str = new StringBuilder("{");
        for (Node n : elements) {
            if (n != null) {
                Node current = n;
                while(current != null) {
                    str.append(current.key).append("=").append(current.value).append(", ");
                    current = current.next;
                }
            }
        }

        if (str.length() > 1) {
           str.setLength(str.length()-2);
        }
        //return "TODO";
        return str.append("}").toString();
    }

    // TODO: declare any private helpers, inner classes, etc. here

    private class Node{
        public K key;
        public V value;
        public Node next;

        public Node(K key, V value) {
            this(key, value, null);
        }

        public Node(K key, V value, Node next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }

        public String toString() {
            System.out.println();
            if (next != null) {
                return " Key: " + key + ", Value:" + value + next.toString();
            } else {
                return " Key: " + key + ", Value:" + value;
            }

        }

    }

}