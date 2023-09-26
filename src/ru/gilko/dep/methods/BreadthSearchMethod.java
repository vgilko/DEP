package ru.gilko.dep.methods;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.gilko.dep.domain.Edge;
import ru.gilko.dep.domain.Vertex;

import java.util.*;

@Data
@Slf4j
@RequiredArgsConstructor
public class BreadthSearchMethod implements SearchMethod {
    private final Queue<Vertex> allowable = new LinkedList<>();
    private final Set<Vertex> restricted = new HashSet<>();

    private boolean isFound = false;

    private final Vertex beginVertex;
    private final Vertex endVertex;
    private final List<Edge> graph;

    @Override
    public boolean canReachVertex() {
        isFound = false;
        allowable.add(beginVertex);

        while (!allowable.isEmpty()) {
            if (isFound) {
                return true;
            }

            findDescendants();
        }

        return isFound;
    }

    private void findDescendants() {
        for (Edge edge : graph) {
            if (isAllowableDescendant(allowable.peek(), edge)) {
                if (isDesiredVertex(edge)) {
                    System.out.printf("Last edge before target: -%d-> [%d]%n", edge.id(), endVertex.getId());

                    isFound = true;
                    return;
                }

                allowable.add(edge.end());
            }
        }

        restricted.add(allowable.poll());
    }

    private boolean isDesiredVertex(Edge edge) {
        return edge.end().getId() == endVertex.getId();
    }

    private boolean isAllowableDescendant(Vertex target, Edge edge) {
        return edge.begin().equals(target) && !restricted.contains(edge.end());
    }
}
