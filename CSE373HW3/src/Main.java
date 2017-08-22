// CSE 373 Homework 3: The Amazing Deque
// YOU DO NOT NEED TO MODIFY THIS INSTRUCTOR-PROVIDED FILE.
// Your code should work properly with an unmodified version of this file.

import java.io.*;
import java.util.*;

/**
 * This program runs the maze solver.
 * It reads the input data from the maze text file
 * that will be passed in to construct a Maze object,
 * then it asks your MazeSolver to solve the maze.
 */
public class Main {
    /** Runs the program. */
    public static void main(String[] args) throws FileNotFoundException {
        System.out.println("CSE 373 Maze Solver");
        System.out.println("===================");
        System.out.println("This program explores ways to escape from a 2-D maze.");
        System.out.println();

        Scanner console = new Scanner(System.in);
        System.out.print("Do you want graphics (Y/N)? ");
        boolean graphics = !console.nextLine().trim().toUpperCase().startsWith("N");

        Maze maze = readMaze(console, graphics);
        MazeSolver solver = new MazeSolver();

        System.out.println("Searching for a path ...");
        System.out.println();
        if (solver.solve(maze)) {
            System.out.println("Here is your solved maze:");
        } else {
            System.out.println("No path to the exit was found.");
        }
        System.out.println(maze);
    }

    /**
     * Reads maze data from the user's text file and uses it to construct and
     * return a Maze object.  If graphical is true, constructs a GraphicalMaze.
     * Throws a FileNotFoundException if the maze file is not found.
     */
    public static Maze readMaze(Scanner console, boolean graphics) throws FileNotFoundException {
        System.out.print("Maze file name? ");
        String filename = console.nextLine();
        StringBuilder sb = new StringBuilder();
        Scanner input = new Scanner(new File(filename));
        while (input.hasNextLine()) {
            sb.append(input.nextLine());
            sb.append('\n');
        }
        if (graphics) {
            return new GraphicalMaze(sb.toString());
        } else {
            return new Maze(sb.toString());
        }
    }
}