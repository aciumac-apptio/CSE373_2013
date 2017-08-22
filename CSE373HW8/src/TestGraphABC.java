// CSE 373, Winter 2013, Marty Stepp
// This program demonstrates some usage of the SearchableGraph class.
// You must attach Graph.jar and Guava's JAR to your project for it to compile
// and run properly.
import com.google.common.collect.*;

import java.util.*;

public class TestGraphABC {
    public static void main(String[] args) {
        Graph<String, Void> graph = new SearchableGraph<String, Void>(true, true);
        printLots(graph);

        System.out.println("adding vertices ...");
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addVertex("D");
        graph.addVertex("E");
        graph.addVertex("F");
        graph.addVertex("G");
        printLots(graph);

        System.out.println("adding edges ...");
        graph.addEdge("A", "B", 2);
        graph.addEdge("A", "D", 1);
        graph.addEdge("B", "D", 3);
        graph.addEdge("B", "E", 10);
        graph.addEdge("C", "A", 4);
        graph.addEdge("C", "F", 5);
        graph.addEdge("D", "C", 2);
        graph.addEdge("D", "E", 2);
        graph.addEdge("D", "F", 8);
        graph.addEdge("D", "G", 4);
        graph.addEdge("E", "G", 6);
        graph.addEdge("G", "F", 1);
       //boolean s = graph.isReachable("A", "B");

        printLots(graph);
    }

    // Prints out a lot of information about the given graph,
    // such as all vertices, all edges, and all paths found.
    public static <V, E> void printLots(Graph<V, E> graph) {
        System.out.println("graph:");
        System.out.println("  toString=" + graph);
        System.out.println("  directed=" + graph.isDirected() + ", weighted="
                + graph.isWeighted() + ", isEmpty=" + graph.isEmpty());

        System.out.println("vertices=" + graph.vertices() + ", vertexCount="
                + graph.vertexCount());
        for (V v : graph.vertices()) {
            System.out.println("  vertex " + v + ": degree=" + graph.degree(v)
                    + ", inDegree=" + graph.inDegree(v) + ", outDegree=" + graph.outDegree(v)
                    + ", containsVertex(" + v + ")=" + graph.containsVertex(v)
                    + ", neighbors=" + graph.neighbors(v));
        }

        System.out.println("edges=" + graph.edges() + ", edgeCount=" + graph.edgeCount());
        for (E e : graph.edges()) {
            System.out.println("  edge " + e + ": containsEdge(" + e + ")=" + graph.containsEdge(e));
        }

        System.out.println("paths:");
        for (V v1 : graph.vertices()) {
            for (V v2 : graph.vertices()) {
                List<V> min = graph.minimumWeightPath(v1, v2);
                System.out.println("  (" + v1 + "," + v2 + ") isReachable="
                        + graph.isReachable(v1, v2)
                        + ", shortest=" + graph.shortestPath(v1, v2)
                        + ", minWeight=" + min
                        + (min == null ? "" : " (cost=" + graph.cost(min) + ")"));
            }
        }
        System.out.println();
    }
}