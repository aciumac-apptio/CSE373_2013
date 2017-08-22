// CSE 373 Homework 2 (Star Chart)
// instructor-provided file

import java.util.*;

/**
 * This is a basic testing program for your Star class.
 * It will help you to write some of your methods even if StarChart is not completed.
 * Please feel free to modify it to add your own testing code.
 */
public class TestStar {
    /** Runs the testing program. */
    public static void main(String[] args) {
        toStringTests();
        equalsTests();
        compareToTests1();
        compareToTests2();
        distanceTests1();
        distanceTests2();
    }

    /**
     * Performs a series of tests of the toString method.
     */
    public static void toStringTests() {
        System.out.println("Constructing some Stars ...");
        Star star1 = new Star(0.25, -0.5, 0.125, 4.75);
        Star star2 = new Star(-0.25, 0.6, 0.85, 6.75);
        Star star3 = new Star(0.35, -0.1, -0.5, 0.75);
        Star star4 = new Star(0.6, 0.1, 0.4, 2.75);
        Star star5 = new Star(0.8, -0.3, -0.3, 5.75);

        System.out.println();
        System.out.println("toString:");
        System.out.println(star1);
        System.out.println(star2);
        System.out.println(star3);
        System.out.println(star4);
        System.out.println(star5);
    }

    /**
     * Performs a series of tests of the equals method.
     */
    public static void equalsTests() {
        Star star1 = new Star(0.25, -0.5, 0.125, 4.75);
        Star star1copy = new Star(0.25, -0.5, 0.125, 4.75);
        Star star1similar1 = new Star(0.75, -0.5, 0.125, 4.75);
        Star star1similar3 = new Star(0.25, -0.5, 0.625, 4.75);
        Star star1similar4 = new Star(0.25, -0.5, 0.625, 2.0);
        Star star2 = new Star(-0.25, 0.6, 0.85, 6.75);
        Star star2copy = new Star(-0.25, 0.6, 0.85, 6.75);
        Star star3 = new Star(0.35, -0.1, -0.5, 0.75);
        Star star4 = new Star(0.6, 0.1, 0.4, 2.75);
        Star star5 = new Star(0.8, -0.3, -0.3, 5.75);
        Star star5copy = new Star(0.8, -0.3, -0.3, 5.75);

        System.out.println();
        System.out.println("equals:");
        equalsTestHelper(star1, star1, true);
        equalsTestHelper(star1, star1copy, true);
        equalsTestHelper(star1, star1similar1, false);
        equalsTestHelper(star1, star1similar3, false);
        equalsTestHelper(star1, star1similar4, false);
        equalsTestHelper(star1, star2, false);
        equalsTestHelper(star1, star3, false);
        equalsTestHelper(star1, star4, false);
        equalsTestHelper(star1, star5, false);
        equalsTestHelper(star2, star2copy, true);
        equalsTestHelper(star2, star3, false);
        equalsTestHelper(star2, star5, false);
        equalsTestHelper(star3, star3, true);
        equalsTestHelper(star4, star4, true);
        equalsTestHelper(star5, star5copy, true);
        equalsTestHelper(star1, "\"uh-oh\"", false);
        equalsTestHelper(star1, 3.14, false);
    }

    /**
     * Performs a series of tests of the compareTo method.
     */
    public static void compareToTests1() {
        Star star1 = new Star(0.25, -0.5, 0.125, 4.75);
        Star star1copy = new Star(0.25, -0.5, 0.125, 4.75);
        Star star1similar1 = new Star(0.75, -0.5, 0.125, 4.75);
        Star star1similar2 = new Star(0.25, -0.8, 0.125, 4.75);
        Star star1similar3 = new Star(0.25, -0.5, 0.625, 4.75);
        Star star1similar4 = new Star(0.25, -0.5, 0.625, 2.0);
        Star star2 = new Star(-0.25, 0.6, 0.85, 6.75);
        Star star3 = new Star(0.35, -0.1, -0.5, 0.75);
        Star star4 = new Star(0.6, 0.1, 0.4, 2.75);
        Star star5 = new Star(0.8, -0.3, -0.3, 5.75);

        System.out.println();
        System.out.println("compareTo:");
        compareToTestHelper(star1, star1copy, 0.0);
        compareToTestHelper(star1, star1similar1, -1.0);
        compareToTestHelper(star1, star1similar2, 1.0);
        compareToTestHelper(star1, star1similar3, -1.0);
        compareToTestHelper(star1, star1similar4, -1.0);
        compareToTestHelper(star1similar1, star1similar2, 1.0);
        compareToTestHelper(star1similar1, star1similar4, -1.0);
        compareToTestHelper(star1similar2, star1similar2, 0.0);
        compareToTestHelper(star1similar2, star1similar3, -1.0);
        compareToTestHelper(star1similar2, star1similar4, -1.0);
        compareToTestHelper(star1similar3, star1similar4, 1.0);
        compareToTestHelper(star1, star2, -1.0);
        compareToTestHelper(star2, star1, 1.0);
        compareToTestHelper(star1, star3, 1.0);
        compareToTestHelper(star3, star1, -1.0);
        compareToTestHelper(star1, star4, -1.0);
        compareToTestHelper(star4, star1, 1.0);
        compareToTestHelper(star1, star5, 1.0);
    }

    /**
     * Performs a series of tests of the compareTo method.
     */
    public static void compareToTests2() {
        Star star1 = new Star(0.25, -0.5, 0.125, 4.75);
        Star star1copy = new Star(0.25, -0.5, 0.125, 4.75);
        Star star1similar1 = new Star(0.75, -0.5, 0.125, 4.75);
        Star star1similar2 = new Star(0.25, -0.8, 0.125, 4.75);
        Star star1similar3 = new Star(0.25, -0.5, 0.625, 4.75);
        Star star1similar4 = new Star(0.25, -0.5, 0.625, 2.0);
        Star star2 = new Star(-0.25, 0.6, 0.85, 6.75);
        Star star2copy = new Star(-0.25, 0.6, 0.85, 6.75);
        Star star3 = new Star(0.35, -0.1, -0.5, 0.75);
        Star star4 = new Star(0.6, 0.1, 0.4, 2.75);
        Star star5 = new Star(0.8, -0.3, -0.3, 5.75);
        Star star5copy = new Star(0.8, -0.3, -0.3, 5.75);

        compareToTestHelper(star5, star1, -1.0);
        compareToTestHelper(star2, star2copy, 0.0);
        compareToTestHelper(star2, star3, 1.0);
        compareToTestHelper(star2, star4, 1.0);
        compareToTestHelper(star4, star2, -1.0);
        compareToTestHelper(star2, star5, 1.0);
        compareToTestHelper(star3, star4, -1.0);
        compareToTestHelper(star4, star3, 1.0);
        compareToTestHelper(star3, star5, -1.0);
        compareToTestHelper(star5, star3, 1.0);
        compareToTestHelper(star4, star5, 1.0);
        compareToTestHelper(star5, star4, -1.0);
        compareToTestHelper(star5, star5copy, 0.0);

        // comparable stars should be able to be sorted
        List<Star> starlist = Arrays.asList(star1, star1copy, star2copy,
                star1similar1, star2, star3, star5copy, star1similar2,
                star4, star5, star1similar3, star1similar4);
        System.out.println();
        System.out.println("list of stars: " + starlist);
        Collections.sort(starlist);
        System.out.println("sorted  stars: " + starlist);
    }

    /**
     * Performs a series of tests of the distance method.
     */
    public static void distanceTests1() {
        Star star1 = new Star(0.25, -0.5, 0.125, 4.75);
        Star star1copy = new Star(0.25, -0.5, 0.125, 4.75);
        Star star1similar1 = new Star(0.75, -0.5, 0.125, 4.75);
        Star star1similar2 = new Star(0.25, -0.8, 0.125, 4.75);
        Star star1similar3 = new Star(0.25, -0.5, 0.625, 4.75);
        Star star1similar4 = new Star(0.25, -0.5, 0.625, 2.0);
        Star star2 = new Star(-0.25, 0.6, 0.85, 6.75);
        Star star3 = new Star(0.35, -0.1, -0.5, 0.75);
        Star star4 = new Star(0.6, 0.1, 0.4, 2.75);

        System.out.println();
        System.out.println("distance:");
        distanceTestHelper(star1, star1copy, 0.0);
        distanceTestHelper(star1, star1similar1, 0.5);
        distanceTestHelper(star1, star1similar2, 0.3);
        distanceTestHelper(star1, star1similar3, 0.5);
        distanceTestHelper(star1, star1similar4, 0.5);
        distanceTestHelper(star1similar1, star1similar2, 0.5831);
        distanceTestHelper(star1similar1, star1similar3, 0.7071);
        distanceTestHelper(star1similar1, star1similar4, 0.7071);
        distanceTestHelper(star1similar2, star1similar2, 0.0);
        distanceTestHelper(star1similar2, star1similar3, 0.5831);
        distanceTestHelper(star1similar2, star1similar4, 0.5831);
        distanceTestHelper(star1similar3, star1similar4, 0.0);
        distanceTestHelper(star1, star2, 1.4091);
        distanceTestHelper(star2, star1, 1.4091);
        distanceTestHelper(star1, star3, 0.7487);
        distanceTestHelper(star3, star1, 0.7487);
        distanceTestHelper(star1, star4, 0.7471);
        distanceTestHelper(star4, star1, 0.7471);
    }

    /**
     * Performs a series of tests of the distance method.
     */
    public static void distanceTests2() {
        Star star1 = new Star(0.25, -0.5, 0.125, 4.75);
        Star star2 = new Star(-0.25, 0.6, 0.85, 6.75);
        Star star2copy = new Star(-0.25, 0.6, 0.85, 6.75);
        Star star3 = new Star(0.35, -0.1, -0.5, 0.75);
        Star star4 = new Star(0.6, 0.1, 0.4, 2.75);
        Star star5 = new Star(0.8, -0.3, -0.3, 5.75);
        Star star5copy = new Star(0.8, -0.3, -0.3, 5.75);

        distanceTestHelper(star1, star5, 0.7233);
        distanceTestHelper(star5, star1, 0.7233);
        distanceTestHelper(star2, star2copy, 0.0);
        distanceTestHelper(star2, star3, 1.6348);
        distanceTestHelper(star3, star2, 1.6348);
        distanceTestHelper(star2, star4, 1.0840);
        distanceTestHelper(star4, star2, 1.0840);
        distanceTestHelper(star2, star5, 1.7986);
        distanceTestHelper(star5, star2, 1.7986);
        distanceTestHelper(star3, star4, 0.9552);
        distanceTestHelper(star4, star3, 0.9552);
        distanceTestHelper(star3, star5, 0.5315);
        distanceTestHelper(star5, star3, 0.5315);
        distanceTestHelper(star4, star5, 0.8307);
        distanceTestHelper(star5, star4, 0.8307);
        distanceTestHelper(star5, star5copy, 0.0);
    }

    /**
     * Performs one compareTo test on the given two stars, printing the result
     * as well as printing what numeric range (positive, negative, or 0) the
     * result should be in for comparison purposes.
     */
    public static void compareToTestHelper(Star star1, Star star2, double expected) {
        System.out.printf("%-22s .compareTo %-22s (should be %s)? ", star1,
                star2, signString(expected));
        System.out.println(signString(star1.compareTo(star2)));
    }

    /**
     * Performs one distance test on the given two stars, printing the result
     * as well as printing what the real number result should be for comparison.
     */
    public static void distanceTestHelper(Star star1, Star star2, double expected) {
        System.out.printf("%-22s .distance %-22s (should be %.4f)? %.4f\n",
                star1, star2, expected, star1.distance(star2));
    }

    /**
     * Performs one equals test on the given two stars, printing the result
     * as well as printing what the boolean result should be for comparison.
     */
    public static void equalsTestHelper(Star star1, Object star2, boolean expected) {
        System.out.printf("%-22s .equals %-22s (should be %-5s)? ",
                star1, star2, expected);
        System.out.println(star1.equals(star2));
    }

    /**
     * Helper to return a string about the sign of the given real number:
     * + for positive, - for negative, or 0 for 0.0.
     * This is used because compareTo can return any number in those ranges
     * and still be correct; it does not have to match any one specific value.
     */
    private static String signString(double value) {
        if (value > 0.0) {
            return "+";
        } else if (value < 0.0) {
            return "-";
        } else {
            return "0";
        }
    }
}