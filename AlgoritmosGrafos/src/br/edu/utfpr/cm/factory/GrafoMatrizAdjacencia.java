package br.edu.utfpr.cm.factory;

import java.util.Iterator;

import br.edu.utfpr.cm.grafo.Aresta;
import br.edu.utfpr.cm.grafo.Grafo;
import br.edu.utfpr.cm.grafo.Vertice;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GrafoMatrizAdjacencia implements Grafo<Vertice, Aresta<Vertice, Vertice>> {

    int[][] grafo;
    int numeroMaxVertices;
    int numeroVertices;
    Orientacao orientacao;
    List<Aresta<Vertice, Vertice>> arestas;
    HashMap<Vertice, Integer> verticeInteger;
    HashMap<Integer, Vertice> integerVertice;

    public GrafoMatrizAdjacencia(Orientacao orientacao) {
        this.orientacao = orientacao;
        numeroMaxVertices = 10;
        numeroVertices = 0;
        arestas = new ArrayList<>();
        int[][] grafo = new int[numeroMaxVertices][numeroMaxVertices];
        inicializaMatriz();
        verticeInteger = new HashMap<>();
        integerVertice = new HashMap<>();
    }

    private void inicializaMatriz() {
        inicializaMatriz(this.grafo);
    }

    private void inicializaMatriz(int[][] matriz) {
        for (int[] linha : matriz) {
            for (int i = 0; i < linha.length; i++) {
                linha[i] = 0;
            }
        }
    }

    private void dobrarMatriz() {
        numeroMaxVertices *= 2;
        int[][] grafo = new int[numeroMaxVertices][numeroMaxVertices];
        inicializaMatriz(grafo);
        copiarMatriz(this.grafo, grafo);
        this.grafo = grafo;
        System.gc();
    }

    private void copiarMatriz(int[][] origem, int[][] destino) {
        for (int i = 0; i < origem.length; i++) {
            for (int j = 0; j < origem.length; j++) {
                destino[i][j] = origem[i][j];
            }
        }
    }

    @Override
    public Iterator<Vertice> getVerticesAdjacentes(Vertice u) {
        if (u == null || !verticeInteger.containsKey(u)) {
            return null;
        }
        int numeroVerticeMatriz = verticeInteger.get(u);
        List<Vertice> verticesAdjacentes = new ArrayList<>();
        for (int coluna = 0; coluna < grafo[numeroVerticeMatriz].length; coluna++) {
            if (grafo[numeroVerticeMatriz][coluna] > 0) {
                verticesAdjacentes.add(integerVertice.get(coluna));
            }
        }
        return verticesAdjacentes.iterator();
    }

    @Override
    public Iterator<Vertice> getVertices() {
        return verticeInteger.keySet().iterator();
    }

    @Override
    public Iterator<Aresta<Vertice, Vertice>> getArestas() {
        return arestas.iterator();
    }

    @Override
    public Vertice getVertice(String idVertice) {
        for (Vertice vertice : verticeInteger.keySet()) {
            if (vertice.getId().equals(idVertice)) {
                return vertice;
            }
        }
        return null;
    }

    @Override
    public void adicionaVertice(Vertice verticeNoGrafo, Vertice verticeAdicionado) {
        if (!this.verticeInteger.containsKey(verticeNoGrafo)) {
            return;
        }
        adicionaVertice(verticeAdicionado);
        Aresta aresta = new Aresta(verticeNoGrafo, verticeAdicionado);
        adicionaAresta(aresta);
    }

    @Override
    public void adicionaVertice(Vertice verticeAdicionado) {
        if (this.verticeInteger.containsKey(verticeAdicionado)) {
            return;
        }
        if (this.numeroVertices == this.numeroMaxVertices) {
            dobrarMatriz();
        }
        this.verticeInteger.put(verticeAdicionado, this.numeroVertices);
        this.integerVertice.put(this.numeroVertices, verticeAdicionado);
        this.numeroVertices++;
    }

    @Override
    public void adicionaAresta(Aresta<Vertice, Vertice> arestaAdicionada) {
        if (this.arestas.contains(arestaAdicionada)) {//sobrescrever equals
            return;
        }
        arestas.add(arestaAdicionada);
        grafo[Integer.valueOf(arestaAdicionada.getVertice1().getId())][Integer.valueOf(arestaAdicionada.getVertice2().getId())] = 1;
        if (orientacao == Orientacao.NAO_DIRIGIDO) {
            grafo[Integer.valueOf(arestaAdicionada.getVertice2().getId())][Integer.valueOf(arestaAdicionada.getVertice1().getId())] = 1;
        }
    }

    @Override
    public void removerAresta(Aresta<Vertice, Vertice> aresta) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void removerVertice(Vertice vertice) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
