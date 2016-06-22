/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.cm.algoritmo.entidades;

import br.edu.utfpr.cm.grafo.Aresta;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author romulo
 */
public class Label implements Comparable<Label> {

    private final String value;
    private final List<Aresta> edgesCovered;

    public Label(String value, List<Aresta> edgesCovered) {
        this.edgesCovered = new ArrayList<>();
        this.value = value;
        for (Aresta edge : edgesCovered) {
            this.edgesCovered.add(edge);
        }
    }

    public Label(String value) {
        this.value = value;
        this.edgesCovered = new ArrayList<>();
    }

    public List<Aresta> getEdgesCovered() {
        return edgesCovered;
    }

    public String getValue() {
        return value;
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
        Label label = new Label(this.value);
        label.edgesCovered.addAll(this.edgesCovered);
        return label;
    }

}
