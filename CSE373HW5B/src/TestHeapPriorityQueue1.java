//CSE 373 Homework 5: The Even More Amazing Heap
// instructor-provided file

import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;

/**
 * This is a basic testing program for your HeapPriorityQueue class.
 * It will help you to test each of your methods to make sure they work properly.
 * Please feel free to modify it to add your own testing code.
 */
public class TestHeapPriorityQueue1 {
    /** Runs the testing program. */
    public static void main(String[] args) {
        System.out.println("HeapPriorityQueue Testing Program #1");
        System.out.println();

        PriorityQueue<String> pqueue = new HeapPriorityQueue<String>();
        testAddToString(pqueue);
        testRemoveMin(pqueue);
        testIsEmptySize(pqueue);
        testClear();

        pqueue = new HeapPriorityQueue<String>();
        testResize();

        testContains();
        testRemove();

        testIteratorIterable();
        testComparator();

        System.out.println("All tests complete.");
    }

    // Tests the add method.
    private static void testAddToString(PriorityQueue<String> pqueue) {
        System.out.println("add, toString:");
        System.out.println(pqueue);
        pqueue.add("four");
        System.out.println(pqueue);
        pqueue.add("score");
        System.out.println(pqueue);
        pqueue.add("and");
        System.out.println(pqueue);
        pqueue.add("seven");
        System.out.println(pqueue);
        pqueue.add("years");
        System.out.println(pqueue);
        pqueue.add("ago");
        System.out.println(pqueue);
    }

    // Tests the contains method.
    private static void testContains() {
        System.out.println("contains:");
        PriorityQueue<String> pqueue = new HeapPriorityQueue<String>();
        pqueue.add("four");
        pqueue.add("score");
        pqueue.add("and");
        pqueue.add("seven");
        pqueue.add("years");
        pqueue.add("ago");
        for (String word : "four five and AGO e seven years score test ago".split(" ")) {
            System.out.println(pqueue + " contains " + word + "? " + pqueue.contains(word));
        }
        System.out.println();
    }

    // Tests the contains method.
    private static void testRemove() {
        System.out.println("remove(E) - removing arbitrary element:");
        PriorityQueue<String> pqueue = new HeapPriorityQueue<String>();
        pqueue.add("four");
        pqueue.add("score");
        pqueue.add("and");
        pqueue.add("seven");
        pqueue.add("years");
        pqueue.add("ago");
        pqueue.add("our");
        pqueue.add("fathers");
        System.out.println(pqueue);

        String words = "years AGO and five e seven four score test ago fathers booyah our";
        for (String word : words.split(" ")) {
            pqueue.remove(word);
            System.out.print(pqueue + " after removing " + word);
            System.out.println(" (size " + pqueue.size() + ")");
        }
        System.out.println();
    }

    // Tests the remove method to remove the minimum element.
    private static void testRemoveMin(PriorityQueue<String> pqueue) {
        System.out.println();
        System.out.println("remove() - removing minimum element:");
        System.out.println(pqueue.remove());
        System.out.println(pqueue);
        System.out.println(pqueue.remove());
        System.out.println(pqueue);
        System.out.println(pqueue.remove());
        System.out.println(pqueue);
    }

    // Tests the isEmpty and size methods.
    private static void testIsEmptySize(PriorityQueue<String> pqueue) {
        System.out.println();
        System.out.println("isEmpty / size:");
        System.out.println(pqueue + " (size " + pqueue.size() + "), empty? " + pqueue.isEmpty());
        pqueue.add("seven");
        System.out.println(pqueue + " (size " + pqueue.size() + "), empty? " + pqueue.isEmpty());
        pqueue.add("years");
        System.out.println(pqueue + " (size " + pqueue.size() + "), empty? " + pqueue.isEmpty());
        pqueue.add("ago");
        System.out.println(pqueue + " (size " + pqueue.size() + "), empty? " + pqueue.isEmpty());
        while (!pqueue.isEmpty()) {
            System.out.println("remove() returns " + pqueue.remove());
            System.out.println(pqueue + " (size " + pqueue.size() + "), empty? "
                    + pqueue.isEmpty());
        }
    }

    // Tests the clear method.
    private static void testClear() {
        System.out.println();
        System.out.println("clear:");
        PriorityQueue<String> pqueue = new HeapPriorityQueue<String>();
        pqueue.add("four");
        pqueue.add("score");
        pqueue.add("and");
        pqueue.add("seven");
        pqueue.add("years");
        pqueue.add("ago");
        System.out.println(pqueue + " (size " + pqueue.size() + "), empty? " + pqueue.isEmpty());
        pqueue.clear();
        System.out.println(pqueue + " (size " + pqueue.size() + "), empty? " + pqueue.isEmpty());
    }

    // A large test that checks whether the internal array can resize properly after many adds.
    private static void testResize() {
        System.out.println();
        System.out.println("many elements (resize):");
        PriorityQueue<String> pqueue = new HeapPriorityQueue<String>();

        String message = "Four score and seven years ago " +
                "our fathers brought forth on this continent a new nation, " +
                "conceived in liberty, and dedicated to the proposition that " +
                "all men are created equal. " +
                "Now we are engaged in a great civil war, testing whether that " +
                "nation, or any nation, so conceived and so dedicated, can " +
                "long endure.";
        addAllRemoveAllHelper(pqueue, message);
    }

    // Tests the priority queue when using a Comparator for ordering.
    private static void testComparator() {
        System.out.println("Comparator (reverse ABC order):");
        Comparator<String> comp = Collections.reverseOrder();
        PriorityQueue<String> pqueue = new HeapPriorityQueue<String>(15, comp);
        String message = "the quick brown fox jumps over the very lazy bad dog";
        addAllRemoveAllHelper(pqueue, message);

        System.out.println("Comparator (order by length):");
        Comparator<String> comp2 = new StringLengthComparator();
        PriorityQueue<String> pqueue2 = new HeapPriorityQueue<String>(5, comp2);
        String message2 = "our fathers brought forth on this continent " +
                "a new nation conceived in liberty";
        addAllRemoveAllHelper(pqueue2, message2);
    }

    // helper method that breaks a string into words, adds all the words,
    // printing the priority queue each time, then removes them all until
    // the priority queue is empty
    private static void addAllRemoveAllHelper(PriorityQueue<String> pqueue, String message) {
        String[] words = message.split("[ .,]+");
        for (int i = 0; i < words.length; i++) {
            String word = words[i].toLowerCase();
            pqueue.add(word);
            System.out.println("after adding " + word + ": " + pqueue
                    + " (size " + pqueue.size() + ")");
        }

        System.out.println("Removing all words until empty:");
        while (!pqueue.isEmpty()) {
            System.out.print(pqueue.remove() + " ");
        }
        System.out.println();
        System.out.println();
    }

    // Tests the iterator method and the for-each loop (Iterable).
    private static void testIteratorIterable() {
        System.out.println("iterator:");
        PriorityQueue<String> pqueue = new HeapPriorityQueue<String>();
        pqueue.add("four");
        pqueue.add("score");
        pqueue.add("and");
        pqueue.add("seven");
        pqueue.add("years");
        pqueue.add("ago");
        Iterator<String> itr = pqueue.iterator();
        while (itr.hasNext()) {
            System.out.println("iterator next() = " + itr.next());
        }

        System.out.println();
        System.out.println("Iterable:");
        for (String s : pqueue) {
            System.out.println("foreach loop value = " + s);
        }
        System.out.println();
    }

    // A helper class that orders strings by length, breaking ties by ABC order.
    private static class StringLengthComparator implements Comparator<String> {
        // Returns > 0 if s1 is longer than s2, < 0 if shorter; if their
        // lengths are the same, returns the natural ABC ordering.
        public int compare(String s1, String s2) {
            int l1 = s1.length();
            int l2 = s2.length();
            if (l1 != l2) {
                return l1 - l2;
            } else {
                return s1.compareTo(s2);
            }
        }
    }
}