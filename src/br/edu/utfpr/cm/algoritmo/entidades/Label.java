/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.cm.algoritmo.entidades;

import br.edu.utfpr.cm.grafo.ArestaPonderada;
import br.edu.utfpr.cm.grafo.Vertice;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author romulo
 */
public class Label implements Comparable<Label> {

    private final String value;
    private final List<ArestaPonderada> edgesCovered;
    private final List<Vertice> verticesCovered;

    public Label(String value, List<ArestaPonderada> edgesCovered) {
        this.edgesCovered = new ArrayList<>();
        this.verticesCovered = new ArrayList<>();
        this.value = value;
        for (ArestaPonderada arestaPonderada : edgesCovered) {
            addEdge(arestaPonderada);
        }
    }

    public Label(String value) {
        this.value = value;
        this.edgesCovered = new ArrayList<>();
        this.verticesCovered = new ArrayList<>();
    }

    public List<ArestaPonderada> getEdgesCovered() {
        return edgesCovered;
    }

    public List<Vertice> getVerticesCovered() {
        return verticesCovered;
    }

    public String getValue() {
        return value;
    }

    private void addEdge(ArestaPonderada edge) {
        if (!this.edgesCovered.contains(edge)) {
            ArestaPonderada clone = (ArestaPonderada) edge.clone();
            this.edgesCovered.add(clone);
            if (!verticesCovered.contains(clone.getVertice1())) {
                verticesCovered.add(clone.getVertice1());
            }
            if (!verticesCovered.contains(clone.getVertice2())) {
                verticesCovered.add(clone.getVertice2());
            }
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Label)) {
            return false;
        }
        return this.value.equals(((Label) obj).value);
    }

    @Override
    public int compareTo(Label other) {
        return Integer.compare(edgesCovered.size(), other.edgesCovered.size());
    }

    @Override
    protected Object clone() {
        return new Label(this.value, this.edgesCovered);
    }

}
