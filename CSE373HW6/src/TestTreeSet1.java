// CSE 373 Homework 6: The Rotating Dead
// instructor-provided file

import java.util.Iterator;

/**
 * This is a basic testing program for your TreeSet class.
 * It will help you to test each of your methods to make sure they work properly.
 * Please feel free to modify it to add your own testing code.
 */
public class TestTreeSet1 {
    /** Runs the test program. */
    public static void main(String[] args) {
        System.out.println("TreeSet Test Program #1\n");
        Set<Integer> set = new TreeSet<Integer>();
        testAdd(set);
        testContains(set);
        testIteratorIterable();
        testRemove(set);
        testClear(set);
        System.out.println("\nAll tests complete.");
    }

    // test case for the add method (and toString/size)
    private static void testAdd(Set<Integer> set) {
        System.out.println("test add():");
        for (int n : new int[] {42, 29, 42, 17, 112, -9, 17, 82, 53}) {
            set.add(n);
            System.out.printf("after adding %3d, set = %s, size = %d\n",
                    n, set, set.size());
        }
        System.out.println();
    }

    // test case for the contains method
    private static void testContains(Set<Integer> set) {
        System.out.println("test contains():");
        for (int n : new int[] {
                42, 29, 42, 17, 112, -9, 17, 82, 53,
                55, 182, -91, 888, 72, 33, 999, -17}) {
            System.out.printf("%s contains(%3d)? %s\n", set, n, set.contains(n));
        }
        System.out.println();
    }

    // Tests the iterator method and the for-each loop (Iterable).
    private static void testIteratorIterable() {
        System.out.println("iterator:");
        Set<String> set = new TreeSet<String>();
        set.add("four");
        set.add("score");
        set.add("and");
        set.add("seven");
        set.add("years");
        set.add("ago");
        Iterator<String> itr = set.iterator();
        while (itr.hasNext()) {
            System.out.println("iterator next() = " + itr.next());
        }

        System.out.println();
        System.out.println("Iterable:");
        for (String s : set) {
            System.out.println("foreach loop value = " + s);
        }
        System.out.println();
    }

    // test case for the remove method
    private static void testRemove(Set<Integer> set) {
        System.out.println("test remove():");
        for (int n : new int[] {55, 42, 182, 17, -91, 82}) {
            set.remove(n);
            System.out.printf("after removing %3d, set = %s, size = %d, contains(%d)? %s\n",
                    n, set, set.size(), n, set.contains(n));
        }
        System.out.println();

        // test add after remove
        int newValue = 22;
        set.add(newValue);
        System.out.printf("after adding %d, set = %s, size = %d, contains(%d)? %s\n",
                newValue, set, set.size(), newValue, set.contains(newValue));
        System.out.println();
    }

    // test case for the clear method
    private static void testClear(Set<Integer> set) {
        // test clear
        System.out.println("test clear():");
        set.clear();
        System.out.printf("after clear #1, set = %s, size = %d, isEmpty = %s\n",
                set, set.size(), set.isEmpty());
        set.add(42);
        set.clear();
        set.clear();
        System.out.printf("after clear #2, set = %s, size = %d, isEmpty = %s\n",
                set, set.size(), set.isEmpty());
    }
}