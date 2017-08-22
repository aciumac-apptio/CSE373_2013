// TODO: Remove each 'todo' comment once I implement each part!

// TODO: class comment header

import java.util.List;
import java.util.Set;

public class SearchableGraph<V, E> extends AbstractGraph<V, E> {
    //Graph<V, E> graph;

    // TODO: comment header
    public SearchableGraph() {
        super();
    }

    // TODO: comment header
    public SearchableGraph(boolean directed, boolean weighted) {
        super(directed, weighted);
    }

    // TODO: comment header
    public boolean isReachable(V v1, V v2) {
        super.clearVertexInfo();
        return dfs(v1, v2);
    }

    // TODO: comment header
    private boolean dfs(V v1, V v2) {
        // TODO: implement this method
        if (v1.equals(v2)) {
            return true;
        }

        Vertex<V> info = vertexInfo(v1);
        info.setVisited(true);
        Set<V> neighbors = super.neighbors(v1);
        for (V n : neighbors) {
            Vertex<V> one = vertexInfo(n);
            if (!one.visited) {
                //boolean found
                if (dfs(n, v2)) {
                    return true;
                }
            }
        }

        return false;
    }

    // TODO: comment header
    public List<V> minimumWeightPath(V v1, V v2) {
        // TODO: implement this method
        return null;
    }

    // TODO: comment header
    public List<V> shortestPath(V v1, V v2) {
        // TODO: implement this method
        return null;
    }
}