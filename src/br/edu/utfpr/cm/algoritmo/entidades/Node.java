/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.cm.algoritmo.entidades;

import br.edu.utfpr.cm.grafo.Vertice;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author romulo
 */
public class Node {

    private List<Label> selectedLabels;
    private int f;
    private List<Vertice> selectedVertices;

    public Node() {
        this.selectedLabels = new ArrayList<>();
    }

    private Node(List<Label> selectedLabels) {
        for (Label selectedLabel : selectedLabels) {
            addLabel(selectedLabel);
        }
    }

    public final void addLabel(Label label) {
        if (!this.selectedLabels.contains(label)) {
            this.selectedLabels.add((Label) label.clone());
        }
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

    @Override
    public Object clone() {
        return new Node(this.selectedLabels);
    }
}
