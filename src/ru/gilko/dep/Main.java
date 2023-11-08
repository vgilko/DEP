package ru.gilko.dep;

import ru.gilko.dep.domain.Edge;
import ru.gilko.dep.domain.Vertex;
import ru.gilko.dep.methods.BreadthFirstSearchMethod;
import ru.gilko.dep.methods.DepthFirstSearchMethod;

import java.util.List;
import java.util.function.Function;

public class Main {
    public static void main(String[] args) {
        List<Edge> graph = buildGraph();
        Vertex sourceVertex = new Vertex(0);
        Vertex desiredVertex = new Vertex(4);

        BreadthFirstSearchMethod breadthFirstSearchMethod =
                new BreadthFirstSearchMethod(sourceVertex, desiredVertex, graph);
        DepthFirstSearchMethod depthFirstSearchMethod =
                new DepthFirstSearchMethod(sourceVertex, desiredVertex, graph);

        boolean canReachVertexBFS = breadthFirstSearchMethod.canReachVertex();
        boolean canReachVertexDFS = depthFirstSearchMethod.canReachVertex();


        Function<Boolean, String> messageGetter = canReach -> canReach ?
                "Can reach path [%d] ---> [%d]\n"
                : "Path [%d] ---> [%d] unreachable\n";

        System.out.printf("BFS: " + messageGetter.apply(canReachVertexBFS), sourceVertex.getId(), desiredVertex.getId());
        System.out.printf("DFS: " + messageGetter.apply(canReachVertexDFS), sourceVertex.getId(), desiredVertex.getId());
    }

    private static List<Edge> buildGraph() {
        return List.of(
                new Edge(new Vertex(0), new Vertex(1), 10),
                new Edge(new Vertex(0), new Vertex(2), 12),
                new Edge(new Vertex(0), new Vertex(3), 13),
                new Edge(new Vertex(1), new Vertex(5), 11),
                new Edge(new Vertex(2), new Vertex(4), 14),
                new Edge(new Vertex(3), new Vertex(6), 15),
                new Edge(new Vertex(4), new Vertex(5), 16),
                new Edge(new Vertex(4), new Vertex(6), 17),
                new Edge(new Vertex(5), new Vertex(7), 18),
                new Edge(new Vertex(6), new Vertex(7), 19),
                new Edge(new Vertex(8), new Vertex(9), 20)
        );
    }
}
