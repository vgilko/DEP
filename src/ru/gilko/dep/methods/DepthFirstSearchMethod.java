package ru.gilko.dep.methods;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import ru.gilko.dep.domain.Edge;
import ru.gilko.dep.domain.Vertex;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

@Data
@RequiredArgsConstructor
public class DepthFirstSearchMethod implements SearchMethod {
    private final Stack<Vertex> allowable = new Stack<>();
    private final Set<Vertex> restricted = new HashSet<>();

    private boolean isFound = false;

    private final Vertex beginVertex;
    private final Vertex endVertex;
    private final List<Edge> graph;

    @Override
    public boolean canReachVertex() {
        allowable.push(beginVertex);

        while (!allowable.isEmpty()) {
            if (isFound) {
                return true;
            }

            getDescendants(allowable.peek());
        }

        return false;
    }

    private void getDescendants(Vertex target) {
        for (Edge edge : graph) {
            if (edge.begin().equals(target) && !restricted.contains(edge.end())) {
                if (isDesiredVertex(edge)) {
                    System.out.printf("Last edge before target: -%d-> [%d]%n", edge.id(), endVertex.getId());

                    isFound = true;
                    return;
                }

                allowable.push(edge.end());
                return;
            }
        }

        if (allowable.peek().equals(target)) {
            restricted.add(target);
            allowable.pop();
        }
    }

    private boolean isDesiredVertex(Edge edge) {
        return edge.end().equals(endVertex);
    }
}
