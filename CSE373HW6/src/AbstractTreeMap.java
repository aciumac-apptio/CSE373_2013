// CSE 373 Homework 6: The Rotating Dead
// YOU DO NOT NEED TO MODIFY THIS INSTRUCTOR-PROVIDED FILE.
// Your code should work properly with an unmodified version of this file.

import java.util.LinkedList;
import java.util.Collection;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.TreeSet;

/**
 * Each AbstractTreeMap object represents a Map storing a collection of key/value pairs as
 * its elements.  A Map does not allow duplicate keys to be added.
 * The map is implemented using an binary search tree, ordered by the keys' natural ordering.
 * Class invariant: nodes are always kept in proper BST (left-to-right) sorted order.
 */
public abstract class AbstractTreeMap<K extends Comparable<K>, V> implements Map<K, V> {
    protected TreeNode root;   // top node of the tree (null if empty)
    protected int size;        // number of key/value pairs in the tree

    /**
     * Constructs an empty map.
     */
    public AbstractTreeMap() {
        clear();
    }

    /** Removes all key/value pairs from this map. */
    public void clear() {
        root = null;
        size = 0;
    }

    /**
     * Returns true if this set contains the given key.
     * Throws a NullPointerException if the key is null.
     */
    public boolean containsKey(K key) {
        return get(key) != null;
    }

    /**
     * Returns the value associated with the given key,
     * or null if the given key is not found in this map.
     * Throws a NullPointerException if the key is null.
     */
    public V get(K key) {
        ensureNotNull(key);
        return get(root, key);
    }

    /**
     * Returns the minimum key in this map.
     * Throws a NoSuchElementException if this set is empty.
     */
    public K getMaxKey() {
        ensureNotEmpty();
        return getMaxKey(root);
    }

    /**
     * Returns the minimum value in this set.
     * Throws a NoSuchElementException if this set is empty.
     */
    public K getMinKey() {
        ensureNotEmpty();
        return getMinKey(root);
    }

    /** Returns true if this set does not contain any elements. */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Returns a Set of all keys in this map.
     * This is a deep copy and is not "backed" by the original map.
     */
    public Set<K> keySet() {
        Set<K> set = new TreeSet<K>();
        keySet(root, set);
        return set;
    }

    /** Prints all elements of this tree in a left-to-right order (in-order) traversal. */
    public void print() {
        System.out.println("    +-----------------------------------------------------------");
        print(root, "    |");
        System.out.println("    +-----------------------------------------------------------");
    }

    /**
     * Adds the given key/value to this map, if it was not already in the map.
     * If the given key is already in this map, its value is replaced.
     * Throws a NullPointerException if the key or value is null.
     */
    //
    public void put(K key, V value) {
        ensureNotNull(key, value);
        root = put(root, key, value);
    }

    /**
     * Removes the given key and its associated value from this map,
     * if it is found in this map.
     * If the given key is not found, calling this method has no effect.
     * Throws a NullPointerException if the key is null.
     */
    public void remove(K key) {
        ensureNotNull(key);
        root = remove(root, key);
    }

    /**
     * Returns the number of key/value pairs in this map.
     */
    public int size() {
        return size;
    }

    /**
     * Returns a collection of all values in this map.
     * This is a deep copy and is not "backed" by the original map.
     */
    public Collection<V> values() {
        Collection<V> list = new LinkedList<V>();
        values(root, list);
        return list;
    }


    /**
     * Returns the current height of the given subtree.
     */
    protected int computeHeight(TreeNode node) {
        if (node == null) {
            return 0;
        } else {
            // larger of L/R + 1
            int left = 0;
            if (node.left != null) {
                left = node.left.height;
            }
            int right = 0;
            if (node.right != null) {
                right = node.right.height;
            }
            int height = Math.max(left, right) + 1;
            return height;
        }
    }

    /**
     * Throws a NoSuchElementException if the tree is empty.
     */
    protected void ensureNotEmpty() {
        if (root == null) {
            throw new NoSuchElementException("empty tree");
        }
    }

    /**
     * Throws a NullPointerException if any of the arguments passed is null.
     */
    protected void ensureNotNull(Object... args) {
        for (Object o : args) {
            if (o == null) {
                throw new NullPointerException("null argument was passed");
            }
        }
    }

    /**
     * A private recursive helper for the get method.
     * Finds and returns the value associated with the given key in the given subtree,
     * or null if not found.
     */
    protected V get(TreeNode node, K key) {
        if (node == null) {
            return null;
        } else if (key.compareTo(node.key) > 0) {
            return get(node.right, key);
        } else if (key.compareTo(node.key) < 0) {
            return get(node.left, key);
        } else if (key.equals(node.key)) {
            return node.value;   // found it!
        } else {
            return null;
        }
    }

    /**
     * A private recursive helper for the getMaxKey method.
     * Returns the maximum (rightmost) key in the given subtree.
     */
    protected K getMaxKey(TreeNode node) {
        if (node.right == null) {
            return node.key;
        } else {
            return getMaxKey(node.right);
        }
    }

    /**
     * A private recursive helper for the getMinKey method.
     * Returns the maximum (leftmost) key in the given subtree.
     */
    protected K getMinKey(TreeNode node) {
        if (node.left == null) {
            return node.key;
        } else {
            return getMinKey(node.left);
        }
    }

    /**
     * A private recursive helper for the keySet method.
     * Adds the keys in the given subtree to the given set.
     */
    protected void keySet(TreeNode node, Set<K> set) {
        if (node != null) {
            keySet(node.left, set);
            set.add(node.key);
            keySet(node.right, set);
        }
    }

    /**
     * A private recursive helper for the print method.
     * Prints the given subtree.
     */
    protected void print(TreeNode node, String indent) {
        if (node != null) {
            print(node.right, indent + "    ");
            System.out.println(indent + node.key + "=" + node.value + ", height=" + node.height);
            print(node.left, indent + "    ");
        } // implicit base case: else do nothing
    }

    /**
     * A private recursive helper for the put method.
     * Adds the key/value pair to the given subtree and returns the new subtree state.
     * Uses the "x = change(x)" pattern:
     *   pass in current state of node,
     *   return out desired new state of node.
     */
    protected TreeNode put(TreeNode node, K key, V value) {
        if (node == null) {
            node = new TreeNode(key, value);   // reached dead end; put new node here
            size++;
        } else if (key.compareTo(node.key) > 0) {
            node.right = put(node.right, key, value);
        } else if (key.compareTo(node.key) < 0) {
            node.left = put(node.left, key, value);
        } else if (key.equals(node.key)) {
            // a duplicate; replace the value
            node.value = value;
        }

        node.height = computeHeight(node);
        return node;
    }

    /**
     * A private recursive helper for the remove method.
     * Removes from the given subtree and returns the new subtree state.
     */
    protected TreeNode remove(TreeNode node, K key) {
        if (node == null) {
            // nothing here
        } else if (key.compareTo(node.key) < 0) {
            node.left = remove(node.left, key);
        } else if (key.compareTo(node.key) > 0) {
            node.right = remove(node.right, key);
        } else if (key.equals(node.key)) { // (value == root.data)
            // found it! remove now.
            if (node.left == null && node.right == null) {
                // leaf
                node = null;
                size--;
            } else if (node.right == null) {
                // only left child
                node = node.left;
                size--;
            } else if (node.left == null) {
                // only right child
                node = node.right;
                size--;
            } else {
                // hard case: two children; replace me with min from my right
                K rightMinKey = getMinKey(node.right);
                V rightMinValue = get(node.right, rightMinKey);
                node.right = remove(node.right, rightMinKey);   // does a size--
                node.key = rightMinKey;
                node.value = rightMinValue;
            }
        }

        if (node != null) {
            node.height = computeHeight(node);
        }
        return node;
    }

    /**
     * A private recursive helper for the values method.
     * Adds the values in the given subtree to the given collection.
     */
    protected void values(TreeNode node, Collection<V> list) {
        if (node != null) {
            values(node.left, list);
            list.add(node.value);
            values(node.right, list);
        }
    }


    /**
     * Each TreeNode object stores a single node of a binary tree.
     * The class has just public data fields and constructors.
     */
    protected class TreeNode {
        protected K key;          // key stored at this node
        protected V value;        // value stored at this node
        protected TreeNode left;  // reference to left subtree
        protected TreeNode right; // reference to right subtree
        protected int height;     // height of this subtree (for AVL)

        /**
         * Constructs a leaf node with the given data.
         * Throws a NullPointerException if the key/value is null.
         */
        protected TreeNode(K key, V value) {
            ensureNotNull(key, value);
            this.key = key;
            this.value = value;
            this.height = 1;
        }
    }
}