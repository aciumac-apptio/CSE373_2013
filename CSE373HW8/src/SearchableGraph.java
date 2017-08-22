// TODO: Remove each 'todo' comment once I implement each part!

// TODO: class comment header

import java.util.*;

public class SearchableGraph<V, E> extends AbstractGraph<V, E> {

    // Initializes new undirected, unweighted, empty graph
    public SearchableGraph() {
        super();
    }

    // Initializes new directed, weighted, empty graph
    public SearchableGraph(boolean directed, boolean weighted) {
        super(directed, weighted);
    }

    // Returns true if a path between two vertices of a graph exists
    // This method implements DFS
    // If graph does not contain both vertices, throws IllegalArgumentException
    // If either vertex is null, throws NullPointerException
    public boolean isReachable(V v1, V v2) {
        exceptions(v1,v2);
        super.clearVertexInfo();
        return dfs(v1, v2);
    }

    // Private helper that checks if the arguments are valid
    // If graph does not contain both vertices, throws IllegalArgumentException
    // If either vertex is null, throws NullPointerException
    private void exceptions(V v1, V v2) {
        if (v1 == null || v2 == null) {
            throw new NullPointerException();
        }

        if (!super.containsVertex(v1) || !super.containsVertex(v2)) {
            throw new IllegalArgumentException();
        }
    }

    // Private helper for isReachable
    // Implements DFS algorithm
    private boolean dfs(V v1, V v2) {
        if (v1.equals(v2)) {
            return true;
        }

        vertexInfo(v1).setVisited(true);
        Set<V> neighbors = super.neighbors(v1);
        for (V n : neighbors) {
            if (!vertexInfo(n).visited) {
                // Explores first neighbor vertex
                if (dfs(n, v2)) {
                    return true;
                }
            }
        }

        return false;
    }

    // Returns a list that contains most optimal(minimum cost) path from vertex 1 to 2
    // Implements Dijkstra's algorithm
    public List<V> minimumWeightPath(V v1, V v2) {
        exceptions(v1,v2);
        super.clearVertexInfo();
        // Sets cost of each vertex to maximum (infinity)
        for (V v : super.vertices()) {
            Vertex<V> info = vertexInfo(v);
            info.setCost(Vertex.MAX_COST);
        }

        // Set cost of the stating vertex to 0
        vertexInfo(v1).setCost(0);
        // Creates a priority queue and adds all vertices(arranged by cost)
        Queue<V> q = new PriorityQueue<>(999, comp);
        q.addAll(super.vertices());

        while (!q.isEmpty()) {
            V v = q.remove();
            vertexInfo(v).setVisited();
            // For each unvisited neighbor, update cost if necessary
            for (V n : super.neighbors(v)) {
                if (!vertexInfo(n).visited) {
                    int cost = vertexInfo(v).cost() + super.edgeWeight(v, n);
                    if (cost < 0) {
                        // Takes care of the 2^31 + 1 < 0 problem
                        cost = super.edgeWeight(v, n);
                    }

                    if (cost < vertexInfo(n).cost) {
                        vertexInfo(n).setCost(cost);
                        vertexInfo(n).setPrevious(v);
                        // Re-Add to queue to fix ordering
                        q.remove(n);
                        q.add(n);
                    }
                }
            }
        }
        return path(v1, v2);
    }

    // Compares vertices by cost
    Comparator<V> comp = new Comparator<V>() {
        @Override
        public int compare(V o1, V o2) {
            if (vertexInfo(o1).cost > vertexInfo(o2).cost) {
                return 1;
            } else if (vertexInfo(o1).cost < vertexInfo(o2).cost) {
                return -1;
            } else {
                return 0;
            }

        }
    };

    // Returns a list that contains most shortest path(min # of vertices) from vertex 1 to 2
    // Implements BFS algorithm
    public List<V> shortestPath(V v1, V v2) {
        exceptions(v1,v2);

        super.clearVertexInfo();
        vertexInfo(v1).setVisited();
        Queue<V> q = new LinkedList<>();
        q.add(v1);

        // Explores elements of the queue as they are added
        while (!q.isEmpty()) {
            V v = q.remove();
            // Found end vertex!
            if (v.equals(v2)) {
                return path(v1, v);
            }

            Set<V> neighbors = super.neighbors(v);
            // Explores neighbors of vertex
            // If not visited, marked and added to que for further exploration
            for (V n : neighbors) {
                if (!vertexInfo(n).visited) {
                    vertexInfo(n).setVisited();
                    vertexInfo(n).setPrevious(v);
                    q.add(n);
                }
            }
        }

        return null;
    }

    // Private helper that reconstructs path from the starting vertex to the final vertex
    // Doesn't work with loops
    private List<V> path (V v1, V v) {
        List<V> path = new ArrayList<>();
        // Assumes no loops
        while (!v1.equals(v)) {
            path.add(0,v);
            v = vertexInfo(v).previous;
            if (v == null) {
                return null;
            }
        }
        path.add(0,v1);
        return path;
    }

}