// CSE 373 Homework 1 (Stable Marriage)
// instructor-provided file

/**
 * This is a basic testing program for your MatchMaker.
 * It will help you to write some of your methods even if others are not completed.
 * Please feel free to modify it to add your own testing code.
 */
public class SimpleTest {
    /** Runs the testing program. */
    public static void main(String[] args) {
        System.out.println("MatchMaker(): Constructing a match maker ...");
        MatchMaker matchmaker = new MatchMaker();
        System.out.println();

        System.out.println("addPerson(): Adding some people ...");
        System.out.println("  - Alvin (M)");
        matchmaker.addPerson("Alvin", "M", new String[] {"Diane", "Emily", "Fiona"});
        System.out.println("  - Bobby (M)");
        matchmaker.addPerson("Bobby", "M", new String[] {"Emily", "Diane", "Fiona"});
        System.out.println("  - Caleb (M)");
        matchmaker.addPerson("Caleb", "M", new String[] {"Diane", "Fiona", "Emily"});

        System.out.println("  - Diane (F)");
        matchmaker.addPerson("Diane", "F", new String[] {"Caleb", "Bobby", "Alvin"});
        System.out.println("  - Emily (F)");
        matchmaker.addPerson("Emily", "F", new String[] {"Alvin", "Bobby", "Caleb"});
        System.out.println("  - Fiona (F)");
        matchmaker.addPerson("Fiona", "F", new String[] {"Bobby", "Caleb", "Alvin"});
        System.out.println();
        printAllRanks(matchmaker);

        for (int i = 1; i <= 5; i++) {
            System.out.println("isStable()? " + matchmaker.isStable() + "\n");
            System.out.println("printMatches():");
            matchmaker.printMatches();
            System.out.println();

            System.out.println("nextRound() - round " + i);
            matchmaker.nextRound();
            System.out.println();
        }

        System.out.println("Complete!");
    }

    /** Prints all rankings for all people. */
    public static void printAllRanks(MatchMaker matchmaker) {
        System.out.println("getRank():");
        printRankHelper(matchmaker, "Alvin", "Diane", 1);
        printRankHelper(matchmaker, "Alvin", "Emily", 2);
        printRankHelper(matchmaker, "Alvin", "Fiona", 3);
        printRankHelper(matchmaker, "Bobby", "Diane", 2);
        printRankHelper(matchmaker, "Bobby", "Emily", 1);
        printRankHelper(matchmaker, "Bobby", "Fiona", 3);
        printRankHelper(matchmaker, "Caleb", "Diane", 1);
        printRankHelper(matchmaker, "Caleb", "Emily", 3);
        printRankHelper(matchmaker, "Caleb", "Fiona", 2);
        printRankHelper(matchmaker, "Diane", "Alvin", 3);
        printRankHelper(matchmaker, "Diane", "Bobby", 2);
        printRankHelper(matchmaker, "Diane", "Caleb", 1);
        printRankHelper(matchmaker, "Emily", "Alvin", 1);
        printRankHelper(matchmaker, "Emily", "Bobby", 2);
        printRankHelper(matchmaker, "Emily", "Caleb", 3);
        printRankHelper(matchmaker, "Fiona", "Alvin", 3);
        printRankHelper(matchmaker, "Fiona", "Bobby", 1);
        printRankHelper(matchmaker, "Fiona", "Caleb", 2);
        System.out.println();
    }

    /** Helper to print the ranking for one pair. */
    public static void printRankHelper(MatchMaker matchmaker,
                                       String name1, String name2, int expected) {
        System.out.println("  - getRank(\"" + name1 + "\", \"" + name2 + "\")" +
                " - should be " + expected + "? " + matchmaker.getRank(name1, name2));
    }
}