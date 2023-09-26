package ru.gilko.dep.domain;

import lombok.Data;


@Data
public class Vertex {
    private final int id;
    private VertexState state;

    public Vertex(int id) {
        this.id = id;
    }
}
