// TODO: Remove each 'todo' comment once I implement each part!

// TODO: class comment header

import java.awt.Point;
import java.util.Comparator;

// TODO: comment out these two imports to use your own priority queue
import java.util.PriorityQueue;

public class PriorityQueueSolver implements Solver {
    // TODO: declare any private fields here (you may not need any)

    // TODO: comment header
    public boolean solve(Maze maze) {
        // TODO: implement this method

        if (maze == null) {
            throw new IllegalArgumentException();
        }

        Comparator<Point> comp = new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                if (o1.distance(maze.end().getX(), maze.end().getY()) > o2.distance(maze.end().getX(), maze.end().getY())) {
                    return 1;
                } else if (o1.distance(maze.end().getX(), maze.end().getY()) < o2.distance(maze.end().getX(), maze.end().getY())) {
                    return -1;
                } else {
                    return 0;
                }
            }
        };

        PriorityQueue<Point> path = new PriorityQueue<>(999,comp);
        path.add(maze.start());
        boolean isSolved = false;
        while(!path.isEmpty() && !isSolved) {
            Point l = path.poll();
            if (l.equals(maze.end())) {
                isSolved = true;
            } else if (!maze.isInBounds((int) l.getX(), (int) l.getY())|| maze.isWall((int) l.getX(), (int) l.getY()) || maze.isVisited((int) l.getX(), (int) l.getY())) {
                continue;
            } else {
                maze.setVisited((int) l.getX(), (int) l.getY());
                path.add(new Point((int) l.getX(), (int) l.getY() - 1));
                path.add(new Point((int) l.getX(), (int) l.getY() + 1));
                path.add(new Point((int) l.getX() - 1, (int) l.getY()));
                path.add(new Point((int) l.getX() + 1, (int) l.getY()));
               /* addPointToDeque(maze, path, (int) l.getX(), (int) l.getY() - 1, l);
                addPointToDeque(maze, path, (int) l.getX(), (int) l.getY() + 1, l);
                addPointToDeque(maze, path, (int) l.getX() - 1, (int) l.getY(), l);
                addPointToDeque(maze, path, (int) l.getX() + 1, (int) l.getY(), l);*/
            }
        }

        return isSolved;

    }

   /* private void addPointToDeque(Maze maze, PriorityQueue<Point> path, int x, int y, Point l) {
        if (maze.isInBounds(x,y)) {
            if (l.distance(maze.end().getX(), maze.end().getY()) > maze.end().distance((double) x, (double) y)) {
                path.addFirst(new Point(x,y));
            } else {
                path.addLast(new Point(x,y));
            }
        }
    }*/

}