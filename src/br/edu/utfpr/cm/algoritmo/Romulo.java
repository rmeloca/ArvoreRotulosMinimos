/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.cm.algoritmo;

import br.edu.utfpr.cm.grafo.Aresta;
import br.edu.utfpr.cm.grafo.Vertice;
import java.util.ArrayList;
import java.util.List;

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
        for (Aresta edge : edges) {
            if (!verticesUncovered.contains(edge.getVertice1())) {
                verticesUncovered.add(edge.getVertice1());
            }
            if (!verticesUncovered.contains(edge.getVertice2())) {
                verticesUncovered.add(edge.getVertice2());
            }
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
