// CSE 373 Homework 3: The Amazing Deque
// YOU DO NOT NEED TO MODIFY THIS INSTRUCTOR-PROVIDED FILE.
// Your code should work properly with an unmodified version of this file.

import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;

/**
 * A graphical extension of the Maze class that allows the client to view the
 * maze as it is being searched.  As the client calls the various 'is' methods
 * on the maze (isWall, isVisited, etc.) or marks squares as visited, the board
 * pauses and animates the action on screen to show the algorithm's progress.
 * Parts of this code are based on graphical 8 Queens by Stuart Reges.
 */
public class GraphicalMaze extends Maze {
    private static final Color VISITED_COLOR = new Color(192, 228, 255);
    private static final Font LABEL_FONT = new Font("SansSerif", Font.BOLD, 20);
    private static final int MAX_PAUSE = 1000;  // milliseconds
    private JLabel[][] labels;
    private int delayMS;

    /**
     * Constructs a new graphical maze using the given text string of lines.
     * @param text the string of lines to pass; the complete contents of a maze input file
     * @throws IllegalArgumentException if the file is in an invalid format
     */
    public GraphicalMaze(String text) {
        super(text);
        this.delayMS = MAX_PAUSE / 2;
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();  // will not happen
        } catch (InstantiationException e) {
            e.printStackTrace();  // will not happen
        } catch (IllegalAccessException e) {
            e.printStackTrace();  // will not happen
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();  // will not happen
        }

        // add buttons in the middle for the maze squares
        Container center = createLabels();

        // initialize delay and add slider to control it at bottom
        JSlider slider = new JSlider(0, MAX_PAUSE - 10);
        delayMS = slider.getValue();
        slider.addChangeListener(new MazeSliderChangeListener());

        Container south = new JPanel();
        south.add(new JLabel("slow"));
        south.add(slider);
        south.add(new JLabel("fast"));

        JFrame frame = new JFrame("CSE 373 Maze Solver");
        frame.add(center);
        frame.add(south, BorderLayout.SOUTH);
        frame.pack();
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setAlwaysOnTop(true);
        frame.setVisible(true);
        frame.toFront();
    }

    /**
     * Sets all squares in the maze as not visited.
     */
    public void clearVisited() {
        super.clearVisited();
        for (int y = 0; y < height(); y++) {
            for (int x = 0; x < width(); x++) {
                updateLabel(x, y);
            }
        }
    }

    /**
     * Returns whether or not the given x/y position is marked as visited (via setVisited).
     * Also temporarily draws a colored '?' mark on that square to show the algorithm's progress.
     * @param x x-position to check
     * @param y y-position to check
     * @return true if x/y is marked as visited, false if not
     * @throws IllegalArgumentException if the x/y location is out of the bounds of the maze
     */
    public boolean isVisited(int x, int y) {
        return isHelper(x, y, super.isVisited(x, y));
    }

    /**
     * Returns whether or not the given x/y position is a wall of the maze ('#').
     * Also temporarily draws a colored '?' mark on that square to show the algorithm's progress.
     * @param x x-position to check
     * @param y y-position to check
     * @return true if x/y is a wall, false if not
     * @throws IllegalArgumentException if the x/y location is out of the bounds of the maze
     */
    public boolean isWall(int x, int y) {
        return isHelper(x, y, super.isWall(x, y));
    }

    /**
     * Marks the given x/y position as having been visited (.).
     * Also draws a colored '.' mark on that square to show the algorithm's progress.
     * @param x x-position to mark
     * @param y y-position to mark
     * @throws IllegalArgumentException if the x/y location is out of the bounds of the maze
     */
    public void setVisited(int x, int y) {
        super.setVisited(x, y);
        updateLabel(x, y);
        pause();
    }

    // sets up the button labels for each square in the maze
    private Container createLabels() {
        JPanel center = new JPanel(new GridLayout(height(), width(), 1, 1));
        center.setBackground(Color.BLACK);
        labels = new JLabel[height()][width()];
        Dimension labelSize = new Dimension(LABEL_FONT.getSize() + 10, LABEL_FONT.getSize() + 10);
        for (int y = 0; y < height(); y++) {
            for (int x = 0; x < width(); x++) {
                JLabel label = new JLabel();
                label.setHorizontalAlignment(JLabel.CENTER);
                label.setOpaque(true);
                label.setBackground(Color.WHITE);
                label.setForeground(Color.BLACK);
                label.setFont(LABEL_FONT);
                label.setPreferredSize(labelSize);
                labels[y][x] = label;
                updateLabel(x, y);
                center.add(label);
            }
        }
        return center;
    }

    // helper to call one of the maze's 'is' methods and temporarily show a '?' mark
    private boolean isHelper(int x, int y, boolean value) {
        labels[y][x].setText("?");
        labels[y][x].setBackground(Color.YELLOW);
        pause();
        updateLabel(x, y);
        return value;
    }

    // causes the board to pause for a given amount of time for animation
    private void pause() {
        try {
            Thread.sleep(delayMS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // helper to set the graphical text label at the given x/y position to its current maze state
    private void updateLabel(int x, int y) {
        char square = getSquare(x, y);
        labels[y][x].setText(String.valueOf(square));
        if (square == START) {
            labels[y][x].setBackground(Color.GREEN);
        } else if (square == END) {
            labels[y][x].setBackground(Color.PINK);
        } else if (square == WALL) {
            labels[y][x].setBackground(Color.LIGHT_GRAY);
        } else if (square == VISITED) {
            labels[y][x].setBackground(VISITED_COLOR);
        } else {
            labels[y][x].setBackground(Color.WHITE);
        }
    }

    /**
     * A helper class to listen to change events on the speed slider.
     */
    private class MazeSliderChangeListener implements ChangeListener {
        /**
         * Called when the slider's value changes during a slide action.
         */
        public void stateChanged(ChangeEvent e) {
            JSlider slider = (JSlider) e.getSource();
            delayMS = MAX_PAUSE - slider.getValue();
        }
    }
}