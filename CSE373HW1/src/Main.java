// CSE 373 Homework 1: Stable Marriage
// YOU SHOULD NOT MODIFY THIS INSTRUCTOR-PROVIDED FILE.

import java.io.*;
import java.util.*;

/**
 * This program runs the stable marriage simulation and prints the results.
 * It reads and sets up the input data for all the men and women that will
 * be passed in to your MatchMaker object.
 */
public class Main {
    /** Runs the overall program's console user interface. */
    public static void main(String[] args) throws FileNotFoundException {
        System.out.println("CSE 373 Stable Marriage simulator");
        System.out.println("=================================");
        System.out.println("This program reads a file of men and women's preferences");
        System.out.println("and tries to find a 'stable' grouping of couples.");
        System.out.println();

        // prompt for input file name
        System.out.print("Input file name? ");
        Scanner console = new Scanner(System.in);
        String filename = console.nextLine();
        Scanner input = new Scanner(new File(filename));

        // read data from input file
        MatchMaker maker = new MatchMaker();
        readInput(input, maker);

        // prompt for rounds
        System.out.print("How many rounds (0 to repeat until stable)? ");
        int rounds = console.nextInt();
        System.out.println();

        // do match-making and print results
        int round = 0;
        while (!maker.isStable() && (rounds == 0 || round < rounds)) {
            round++;
            System.out.println("Round " + round);
            maker.nextRound();
            System.out.println();

            System.out.println("Current matches:");
            maker.printMatches();
            System.out.println();
        }

        if (maker.isStable()) {
            System.out.println(round + " rounds performed and reached stable state.");
        } else {
            System.out.println(round + " rounds performed but did not reach stable state.");
        }
    }

    /** Reads the people input from the given file and puts it into the MatchMaker. */
    public static void readInput(Scanner input, MatchMaker maker) {
        while (input.hasNextLine()) {
            String line = input.nextLine().trim();
            if (line.length() == 0 || line.startsWith("#")) {
                // this is an empty line or a comment line in the file; ignore
                continue;
            } else {
                // read one person's info; break apart line by ; or , with optional spaces
                String[] tokens = line.split("[ ]*[:,][ ]*");
                if (tokens.length < 3) {
                    System.out.println("invalid input line: \"" + line + "\"");
                    continue;
                }
                String name = tokens[0].intern();
                String gender = tokens[1].toUpperCase().intern();
                String[] preferences = new String[tokens.length - 2];
                for (int i = 0; i < preferences.length; i++) {
                    preferences[i] = tokens[i + 2].intern();
                }

                // add the person to the MatchMaker
                maker.addPerson(name, gender, preferences);
            }
        }
    }
}