// CSE 373 Homework 6: The Rotating Dead
// instructor-provided file

/**
 * This is a basic testing program for your TreeMap class.
 * It will help you to test each of your methods to make sure they work properly.
 * Please feel free to modify it to add your own testing code.
 */
public class TestTreeMap2 {
    public static void main(String[] args) {
        System.out.println("TreeMap Test Program #2\n");
        TreeMap<Integer, String> map = new TreeMap<Integer, String>();
        putTests(map);
        removeTests(map);
        largeMapTest();
        System.out.println("\nAll tests complete.");
    }

    // Tests your put method.
    private static void putTests(TreeMap<Integer, String> map) {
        int[] elements = {8, 1, 0, 6, 9, 7, 4, 2, 5, 3};
        for (int n : elements) {
            System.out.println("putting " + n + "=x" + n + ":");
            map.put(n, "x" + n);
            System.out.println("map toString: " + map);
            map.print();
            System.out.println();
        }
    }

    // Tests your remove method.
    private static void removeTests(TreeMap<Integer, String> map) {
        int[] elements = {7, 9, 5, 8, 1, 0, 6, 3, 2, 4};
        for (int n : elements) {
            System.out.println("removing " + n + ":");
            map.remove(n);
            System.out.println("map toString: " + map);
            map.print();
            System.out.println();
        }
    }

    // Tests your tree map's balance by adding a large number of elements.
    private static void largeMapTest() {
        // large size test
        System.out.println("Building very large map...");
        Map<Integer, String> map = new TreeMap<Integer, String>();
        for (int i = 1; i <= 200000; i++) {
            map.put(i, "value" + i);
        }

        System.out.println(map.get(23895));
        System.out.println(map.get(1));
        System.out.println(map.get(4126));
        System.out.println(map.get(389));
        System.out.println(map.get(88417));
        System.out.println(map.get(200000));

        System.out.println("Done.");
    }
}