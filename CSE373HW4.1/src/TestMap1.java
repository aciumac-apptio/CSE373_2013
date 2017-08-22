// CSE 373 Homework 4: HashMaps vs. Zombies
// instructor-provided file

/**
 * This is a basic testing program for your HashMap class.
 * It will help you to test each of your methods to make sure they work properly.
 * Please feel free to modify it to add your own testing code.
 */
public class TestMap1 {
    /** Runs the test program. */
    public static void main(String[] args) {
        printIntro();
        Map<Integer, String> map = new HashMap<Integer, String>();
        testPut(map);
        testContainsKey(map);
        testKeySet(map);
        testRemove(map);
        testRehash(map);
        testKeySet(map);
        testClear(map);
    }

    // explains the test program
    private static void printIntro() {
        System.out.println("HashMap Test Program #1");
        System.out.println("The exact order of your map may differ from the");
        System.out.println("expected output, but you should have the same");
        System.out.println("overall collection of key/value pairs.");
        System.out.println("The Output Comparison Tool's \"Ignore order of chars\"");
        System.out.println("option will help you compare your output.");
        System.out.println();
    }

    // test case for the put method (and toString/size)
    private static void testPut(Map<Integer, String> map) {
        System.out.println("test put():");
        for (int n : new int[] {42, 29, 42, 17, 112, -9, 17, 82, 53}) {
            map.put(n, "boo" + n);
            System.out.printf("after putting %3d, map = %s, size = %d\n",
                    n, map, map.size());
        }
        System.out.println();
    }

    // test case for the containsKey method
    private static void testContainsKey(Map<Integer, String> map) {
        System.out.println("test containsKey():");
        for (int n : new int[] {
                42, 29, 42, 17, 112, -9, 17, 82, 53,
                55, 182, -91, 888, 72, 33, 999, -17}) {
            System.out.printf("%s containsKey(%3d)? %s\n", map, n, map.containsKey(n));
        }
        System.out.println();
    }

    // test case for the keySet method
    private static void testKeySet(Map<Integer, String> map) {
        System.out.println("test keySet():");
        System.out.println("keySet() = " + map.keySet());
        System.out.println();
    }

    // test case for the remove method
    private static void testRemove(Map<Integer, String> map) {
        System.out.println("test remove():");
        for (int n : new int[] {55, 42, 182, 17, -91, 82}) {
            map.remove(n);
            System.out.printf("after removing %3d, map = %s, size = %d, containsKey(%d)? %s\n",
                    n, map, map.size(), n, map.containsKey(n));
        }
        System.out.println();

        // test put after remove
        int newValue = 22;
        map.put(newValue, "foo");
        System.out.printf("after putting %d, map = %s, size = %d, containsKey(%d)? %s\n",
                newValue, map, map.size(), newValue, map.containsKey(newValue));
        System.out.println();
    }

    // test case that puts many pairs until the hash table needs to resize/rehash
    private static void testRehash(Map<Integer, String> map) {
        System.out.println("test put() with many elements:");
        for (int n : new int[] {
                33, 56, 22, 19, 41, 104, -2, 97,
                97, 22, 984, -797, 66, 33, 90210, 44444}) {
            map.put(n, "foo" + n);
            System.out.printf("after putting %5d, map = %s, size = %d, containsKey(%d)? %s\n",
                    n, map, map.size(), n, map.containsKey(n));
        }
        System.out.println();
    }

    // test case for the clear method
    private static void testClear(Map<Integer, String> map) {
        // test clear
        System.out.println("test clear():");
        map.clear();
        System.out.printf("after clear #1, map = %s, size = %d, isEmpty = %s\n",
                map, map.size(), map.isEmpty());
        System.out.println("keySet() = " + map.keySet());
        map.clear();
        System.out.printf("after clear #2, map = %s, size = %d, isEmpty = %s\n",
                map, map.size(), map.isEmpty());
    }
}