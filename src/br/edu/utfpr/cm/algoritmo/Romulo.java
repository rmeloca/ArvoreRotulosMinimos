/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.cm.algoritmo;

import br.edu.utfpr.cm.grafo.Aresta;
import br.edu.utfpr.cm.grafo.Vertice;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author romulo
 */
public class Romulo implements Algoritmo {//extends prim e kruskal. Arestas seguras

    private final List<Aresta> edges;
    private final List<Aresta> selectedEdges;
    private final List<Vertice> verticesCovered;
    private final List<Vertice> verticesUncovered;

    public Romulo(List<Aresta> edges) {
        this.edges = edges;
        verticesCovered = new ArrayList<>();
        verticesUncovered = new ArrayList<>();
        selectedEdges = new ArrayList<>();
        HashMap<Vertice, Boolean> vertices = new HashMap<>();
        for (Aresta edge : edges) {
            vertices.put(edge.getVertice1(), Boolean.TRUE);
            vertices.put(edge.getVertice2(), Boolean.TRUE);
        }
        for (Map.Entry<Vertice, Boolean> entry : vertices.entrySet()) {
            verticesUncovered.add(entry.getKey());
        }
    }

    @Override
    public void executar() {
        Vertice start;
        if (this.edges.isEmpty()) {
            return;
        }
        start = this.edges.get(0).getVertice1();
        coverVertice(start);
        for (int i = 0; i < verticesCovered.size(); i++) {
            Aresta firstSafeEdge = getFirstSafeEdge();
            if (firstSafeEdge == null) {
                break;
            }
            selectedEdges.add(firstSafeEdge);
            if (verticesCovered.contains(firstSafeEdge.getVertice1())) {
                coverVertice(firstSafeEdge.getVertice2());
            } else {
                coverVertice(firstSafeEdge.getVertice1());
            }
        }
    }

    public List<Aresta> getSelectedEdges() {
        return selectedEdges;
    }

    private void coverVertice(Vertice vertice) {
        verticesCovered.add(vertice);
        verticesUncovered.remove(vertice);
    }

    private Aresta getFirstSafeEdge() {
        for (Aresta edge : edges) {
            if (verticesCovered.contains(edge.getVertice1()) && verticesUncovered.contains(edge.getVertice2())) {
                return edge;
            } else if (verticesUncovered.contains(edge.getVertice1()) && verticesCovered.contains(edge.getVertice2())) {
                return edge;
            }
        }
        return null;
    }
}
