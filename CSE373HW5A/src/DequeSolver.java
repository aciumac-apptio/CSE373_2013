import java.awt.*;
import java.util.LinkedList;

/**
 * Created by Artem on 2/25/2017.
 */
public class DequeSolver implements Solver {

    // TODO: comment header
    public boolean solve(Maze maze) {
        // TODO: implement this method
        if (maze == null) {
            throw new IllegalArgumentException();
        }

        java.util.Deque<Point> path = new LinkedList<>();
        path.addFirst(maze.start());
        boolean isSolved = false;
        while(!path.isEmpty() && !isSolved) {
            Point l = path.removeFirst();
            if (l.equals(maze.end())) {
                isSolved = true;
            } else if (!maze.isInBounds((int) l.getX(), (int) l.getY())|| maze.isWall((int) l.getX(), (int) l.getY()) || maze.isVisited((int) l.getX(), (int) l.getY())) {
                continue;
            } else {
                maze.setVisited((int) l.getX(), (int) l.getY());
                addPointToDeque(maze, path, (int) l.getX(), (int) l.getY() - 1, l);
                addPointToDeque(maze, path, (int) l.getX(), (int) l.getY() + 1, l);
                addPointToDeque(maze, path, (int) l.getX() - 1, (int) l.getY(), l);
                addPointToDeque(maze, path, (int) l.getX() + 1, (int) l.getY(), l);
            }
        }

        return isSolved;
    }

    private void addPointToDeque(Maze maze, java.util.Deque<Point> path, int x, int y, Point l) {
        if (maze.isInBounds(x,y)) {
            if (l.distance(maze.end().getX(), maze.end().getY()) > maze.end().distance((double) x, (double) y)) {
                path.addFirst(new Point(x,y));
            } else {
                path.addLast(new Point(x,y));
            }
        }
    }


}
