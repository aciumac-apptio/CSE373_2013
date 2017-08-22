// CSE 373 Homework 8: Six Degrees of Kevin Bacon
// YOU DO NOT NEED TO MODIFY THIS INSTRUCTOR-PROVIDED FILE.
// Your code should work properly with an unmodified version of this file.

import java.util.*;
import java.io.*;

/**
 * This is the main client program to run your Kevin Bacon simulation.
 * It uses your BaconNumberFinder.
 */
public class MainKevinBacon {
    // input file to use if user does not type one
    private static final String DEFAULT_INPUT_FILENAME = "movies.txt";

    // % of movies in file to add to graph
    private static final double DEFAULT_PERCENT = 100.0;

    /**
     * Runs the program.
     */
    public static void main(String[] args) throws IOException {
        // console input
        printIntro();
        Scanner console = new Scanner(System.in);
        File file = openFile(console);
        System.out.print("Show runtimes of each search (Y/N, Enter for N)? ");
        boolean showTimes = console.nextLine().trim().toLowerCase().startsWith("y");
        double percent = getPercent(console);

        // create and fill the graph
        Graph<String, String> graph = buildGraph(console, file, percent);
        BaconNumberFinder finder = new BaconNumberFinder(graph);

        // prompt for actors and show their Bacon numbers/paths
        String actor = getActorName(console);
        while (actor.length() > 0) {
            long startTime = System.currentTimeMillis();
            finder.findBaconNumber(actor);
            long endTime = System.currentTimeMillis();
            if (showTimes && endTime != startTime) {
                System.out.println("(took " + (endTime - startTime) + " ms)");
            }
            actor = getActorName(console);
        }
        System.out.println("Exiting.");
    }

    /**
     * Displays an introductory message explaining the program to the user.
     */
    public static void printIntro() {
        System.out.println("Welcome to the Six Degrees of Kevin Bacon.");
        System.out.println("If you tell me an actor's name, I'll connect them to Kevin Bacon");
        System.out.println("through the movies they've appeared in.  I bet your actor has");
        System.out.println("a Kevin Bacon number of six or less!");
        System.out.println();
    }

    /**
     * Prompts the user to enter a file name containing movie input data.
     * Defaults to "movies.txt".
     * If the user types a partial file name, ".txt" suffix will be added.
     */
    public static File openFile(Scanner console) {
        System.out.print("Movies file name to open (Enter for " + DEFAULT_INPUT_FILENAME + ")? ");
        String filename = console.nextLine().trim();
        if (filename.length() > 0 && !filename.contains(".")) {
            filename = filename + ".txt";   // shorthand, can type "foo" -> "foo.txt"
        }
        if (filename.length() == 0) {
            filename = DEFAULT_INPUT_FILENAME;
        }
        return new File(filename);
    }

    /**
     * Reads the given input file data and uses it to construct and return a
     * graph of actors and the movies they have appeared in.
     * Throws a FileNotFoundException if the input file is not found.
     * @precondition 0 < percent <= 100.0
     */
    public static Graph<String, String> buildGraph(Scanner console, File file, double percent)
            throws IOException {
        Graph<String, String> graph = new SearchableGraph<String, String>();
        int everyNth = (int) Math.max(1, (int) (100.0 / percent));
        BufferedReader reader = new BufferedReader(new FileReader(file));
        int count = 0;
        while (reader.ready()) {
            count++;
            String line = reader.readLine();
            if (everyNth != 1 && count % everyNth != 0) {
                // keep all lines that relate to Kevin Bacon
                if (!line.contains("Kevin Bacon")) {
                    continue;
                }
            }

            int colons = line.indexOf(";;;");
            if (colons < 0) {
                continue;
            }
            String movie = line.substring(0, colons);
            String[] actors = line.substring(colons + 3).split(", ");
            for (String actor : actors) {
                graph.addVertex(actor);
            }

            // connect all of the actors
            for (int i = 0; i < actors.length; i++) {
                for (int j = i + 1; j < actors.length; j++) {
                    if (!actors[i].equals(actors[j])) {
                        graph.addEdge(actors[i], actors[j], movie);
                    }
                }
            }

            if (count > 0 && count % 10000 == 0) {
                System.out.println("  movie #" + count + " ...");
            }
        }

        return graph;
    }

    /**
     * Prompts the user to type an actor's name and returns the name typed.
     */
    public static String getActorName(Scanner console) {
        System.out.println();
        System.out.print("Actor's name (Enter to quit)? ");
        String actor = console.nextLine().trim();
        return actor;
    }

    /**
     * Prompts the user to type a percentage of movies in the input file to use,
     * and returns the percentage typed, defaulting to 100.0.
     */
    public static double getPercent(Scanner console) {
        System.out.printf("Percentage of movies to include (Enter for %.0f)? ", DEFAULT_PERCENT);
        String percentStr = console.nextLine().trim();
        double percent = DEFAULT_PERCENT;
        try {
            if (percentStr.length() > 0) {
                percent = Math.max(0.00001, Math.min(100.0, Double.parseDouble(percentStr)));
            }
        } catch (NumberFormatException nfe) {
            percent = DEFAULT_PERCENT;
        }
        if (percent == 100.0) {
            System.out.println("Reading input file ...");
        } else {
            System.out.println("Reading input file (" + percent + "% of elements) ...");
        }
        return percent;
    }
}