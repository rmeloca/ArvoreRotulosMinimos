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
public class Node {

    private final List<Label> selectedLabels;
    private final List<Label> unusedLabels;
    private final List<Vertice> verticesCovered;
    private final List<ArestaPonderada> edgesCovered;
    private int f;

    public Node(List<Label> selectedLabels, List<Label> unusedLabels) {
        this.unusedLabels = new ArrayList<>();
        this.selectedLabels = new ArrayList<>();
        this.verticesCovered = new ArrayList<>();
        this.edgesCovered = new ArrayList<>();
        for (Label selectedLabel : selectedLabels) {
            addLabel(selectedLabel);
        }
    }

    public final void addLabel(Label label) {
        if (!this.selectedLabels.contains(label)) {
            this.selectedLabels.add((Label) label.clone());
            this.unusedLabels.remove(label);
            for (ArestaPonderada arestaPonderada : label.getEdgesCovered()) {
                addEdge(arestaPonderada);
            }
        }
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

    public List<Label> getSelectedLabels() {
        return selectedLabels;
    }

    public List<Label> getUnusedLabels() {
        return unusedLabels;
    }

    public void calculateF() {
        this.f = getG() + getH();
        throw new UnsupportedOperationException();
    }

    public int getF() {
        return this.f;
    }

    private int getG() {
        return this.selectedLabels.size();
    }

    private int getH() {
        throw new UnsupportedOperationException();
    }

    public List<Vertice> getVerticesCovered() {
        List<Vertice> vertices = new ArrayList<>();
        for (Label selectedLabel : selectedLabels) {
            for (Vertice vertice : selectedLabel.getVerticesCovered()) {
                if (!vertices.contains(vertice)) {
                    vertices.add(vertice);
                }
            }
        }
        return vertices;
    }

    public List<ArestaPonderada> getAcyclicEdges() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Object clone() {
        return new Node(this.selectedLabels, this.unusedLabels);
    }
}
