/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.cm.algoritmo.entidades;

import br.edu.utfpr.cm.grafo.ArestaPonderada;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author romulo
 */
public class Label implements Comparable<Label> {

    private final String value;
    private List<ArestaPonderada> edgesCovered;

    public Label(String value, List<ArestaPonderada> edgesCovered) {
        this.value = value;
        this.edgesCovered = edgesCovered;
    }

    public Label(String value) {
        this.value = value;
        this.edgesCovered = new ArrayList<>();
    }

    public List<ArestaPonderada> getEdgesCovered() {
        return edgesCovered;
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object obj) {
        return this.value.equals(((Label) obj).value);
    }

    @Override
    public int compareTo(Label other) {
        return Integer.compare(edgesCovered.size(), other.edgesCovered.size());
    }

}
