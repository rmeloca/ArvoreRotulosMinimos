/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.cm.algoritmo.entidades;

import br.edu.utfpr.cm.algoritmo.BuscaProfundidade;
import br.edu.utfpr.cm.algoritmo.BuscaProfundidadeNode;
import br.edu.utfpr.cm.grafo.ArestaPonderada;
import br.edu.utfpr.cm.grafo.Grafo;
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
    private final List<VerticeBuscaProfundidade> verticesCovered;
    private final List<ArestaPonderada> edgesCovered;
    private int f;
    private int n;

    public Node(List<Label> selectedLabels, List<Label> unusedLabels, int quantidadeVerticesNoGrafo) {
        this.unusedLabels = new ArrayList<>();
        this.selectedLabels = new ArrayList<>();
        this.verticesCovered = new ArrayList<>();
        this.edgesCovered = new ArrayList<>();
        this.n = quantidadeVerticesNoGrafo;
        if (selectedLabels != null) {
            for (Label selectedLabel : selectedLabels) {
                addLabel(selectedLabel);
            }
        }
        if (unusedLabels != null) {
            for (Label unusedLabel : unusedLabels) {
                if (!this.unusedLabels.contains(unusedLabel)) {
                    this.unusedLabels.add(unusedLabel);
                }
            }
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

    public void calculateF(Grafo g) {
        this.f = getG() + getH(g);
    }

    public int getF() {
        return this.f;
    }

    private int getG() {
        return this.selectedLabels.size();
    }

    
    
    private int getH(Grafo g) {
        int edgeNeeded = this.n - 1 - getAcyclicEdges(g,g.getVertice("0")).size();
        int sum = 0;
        int size = this.unusedLabels.size();
        int estimativa;
        for (estimativa = 0; sum <= edgeNeeded && estimativa < size; estimativa++) {
            sum += this.unusedLabels.get(estimativa).getEdgesCovered().size();
        }
        return estimativa;
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

    public List<ArestaPonderada> getAcyclicEdges(Grafo g, Vertice v) {
        VerticeBuscaProfundidade s = (VerticeBuscaProfundidade) v;
        BuscaProfundidadeNode bpn = new BuscaProfundidadeNode(verticesCovered,edgesCovered);
        bpn.dfs();
        bpn.removeArestasRetorno();
        return  (List<ArestaPonderada>) bpn.getG().getArestas();
    }

    @Override
    public Object clone() {
        return new Node(this.selectedLabels, this.unusedLabels, this.n);
    }
}
