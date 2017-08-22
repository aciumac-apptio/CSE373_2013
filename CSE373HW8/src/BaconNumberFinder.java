// TODO: Remove each 'todo' comment once I implement each part!

// TODO: class comment header

import java.util.*;

public class BaconNumberFinder {
    SearchableGraph<String, String> movieActors;

    // TODO: comment header
    public BaconNumberFinder(Graph<String, String> actors) {
        // TODO: implement this constructor
        this.movieActors = (SearchableGraph<String, String>) actors;
    }

    // TODO: comment header
    public void findBaconNumber(String actor) {

        if (!movieActors.containsVertex(actor)) {
            System.out.println("No such actor.");
            return;
        }
        System.out.println("Path from " + actor + " to " + "Kevin Bacon:");
        List<String> path = movieActors.shortestPath(actor, "Kevin Bacon");
        if (path != null) {
            for (int i = 0; i < path.size() - 1; i++) {
                System.out.println(path.get(i) + " was in \"" + movieActors.edge(path.get(i), path.get(i+1))+ "\" with "+ path.get(i+1));
            }

            System.out.println(actor + "'s Bacon number is " + (path.size() - 1));
        } else {
            System.out.println("No path found.");
        }

    }
}