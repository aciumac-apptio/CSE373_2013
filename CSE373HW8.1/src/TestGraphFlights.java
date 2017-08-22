// CSE 373, Winter 2013, Marty Stepp
// This program demonstrates some usage of the SearchableGraph class.
// It creates a graph that represents the "flights between cities" graph
// shown in the lecture slides and then prints information about the graph's
// vertices (cities), edges (flights), and paths.
// You must attach Graph.jar and Guava's JAR to your project for it to compile
// and run properly.

import java.util.List;

public class TestGraphFlights {
    public static void main(String[] args) {
        Graph<String, String> graph = new SearchableGraph<String, String>(false, true);
        printLots(graph);

        System.out.println("adding vertices (airports) ...");
        graph.addVertex("DFW");
        graph.addVertex("HNL");
        graph.addVertex("LAX");
        graph.addVertex("LGA");
        graph.addVertex("MIA");
        graph.addVertex("ORD");
        graph.addVertex("PVD");
        graph.addVertex("SFO");
        printLots(graph);

        System.out.println("adding edges (flights) ...");
        graph.addEdge("DFW", "LAX", "flight01", 120);
        graph.addEdge("DFW", "LGA", "flight02", 140);
        graph.addEdge("DFW", "MIA", "flight03", 110);
        graph.addEdge("DFW", "ORD", "flight04",  80);
        graph.addEdge("HNL", "LAX", "flight05", 250);
        graph.addEdge("HNL", "MIA", "flight06", 500);
        graph.addEdge("HNL", "SFO", "flight07", 130);
        graph.addEdge("LAX", "SFO", "flight08",  60);
        graph.addEdge("LAX", "ORD", "flight09", 170);
        graph.addEdge("LGA", "MIA", "flight10", 100);
        graph.addEdge("LGA", "PVD", "flight11", 200);
        graph.addEdge("ORD", "PVD", "flight12",  50);
        graph.addEdge("ORD", "SFO", "flight13",  70);
        printLots(graph);

        System.out.println("clear ...");
        graph.clear();
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