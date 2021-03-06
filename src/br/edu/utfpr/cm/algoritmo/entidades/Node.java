/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.cm.algoritmo.entidades;

import br.edu.utfpr.cm.algoritmo.BuscaProfundidade;
import br.edu.utfpr.cm.algoritmo.Romulo;
import br.edu.utfpr.cm.factory.GrafoMatrizAdjacencia;
import br.edu.utfpr.cm.factory.Orientacao;
import br.edu.utfpr.cm.grafo.Aresta;
import br.edu.utfpr.cm.grafo.Grafo;
import br.edu.utfpr.cm.grafo.Vertice;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author romulo
 */
public class Node {

    private final List<Label> selectedLabels;
    private final List<Label> unusedLabels;
    private final List<Vertice> verticesCovered;
    private final List<Aresta> edgesCovered;
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
            for (Aresta arestaPonderada : label.getEdgesCovered()) {
                addEdge(arestaPonderada);
            }
        }
    }

    private void addEdge(Aresta edge) {
        if (!this.edgesCovered.contains(edge)) {
            Aresta clone = (Aresta) edge.clone();
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
    }

    public int getF() {
        return this.f;
    }

    private int getG() {
        return this.selectedLabels.size();
    }

    private int getH() {
        int edgeNeeded = this.n - 1 - getAcyclicEdges().size();
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

    public List<Aresta> getEdgesCovered() {
        return edgesCovered;
    }

    public Grafo<VerticeBuscaProfundidade, Aresta<VerticeBuscaProfundidade, VerticeBuscaProfundidade>> getGrafoInduzido() {
        Grafo<VerticeBuscaProfundidade, Aresta<VerticeBuscaProfundidade, VerticeBuscaProfundidade>> grafo;
        grafo = new GrafoMatrizAdjacencia<>(Orientacao.NAO_DIRIGIDO);
        for (Aresta aresta : edgesCovered) {
            grafo.adicionaAresta(aresta);
        }
        return grafo;
    }

    public List<Aresta> getAcyclicEdges() {
        Romulo romulo = new Romulo(edgesCovered);
        romulo.executar();
        return romulo.getSelectedEdges();
    }

    @Deprecated
    public List<Aresta> getAcyclicEdgesDFS() {
        Grafo<VerticeBuscaProfundidade, Aresta<VerticeBuscaProfundidade, VerticeBuscaProfundidade>> grafoInduzido;
        BuscaProfundidade buscaProfundidade;
        List<Aresta> listaArestas;

        listaArestas = new ArrayList<>();
        grafoInduzido = getGrafoInduzido();
        if (grafoInduzido.getQuantidadeVertices() == 0) {
            return listaArestas;
        }
        buscaProfundidade = new BuscaProfundidade(grafoInduzido, grafoInduzido.getVertices().next());

        buscaProfundidade.dfs();
        buscaProfundidade.removeArestasRetorno();

        Iterator<Aresta<VerticeBuscaProfundidade, VerticeBuscaProfundidade>> arestas;
        arestas = buscaProfundidade.getG().getArestas();
        while (arestas.hasNext()) {
            Aresta<VerticeBuscaProfundidade, VerticeBuscaProfundidade> next = arestas.next();
            listaArestas.add(next);
        }
        return listaArestas;
    }

    @Override
    public Object clone() {
        return new Node(this.selectedLabels, this.unusedLabels, this.n);
    }
}
