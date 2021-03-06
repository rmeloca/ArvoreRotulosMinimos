package br.edu.utfpr.cm.factory;

import br.edu.utfpr.cm.grafo.Aresta;
import java.util.Iterator;

import br.edu.utfpr.cm.grafo.Grafo;
import br.edu.utfpr.cm.grafo.Vertice;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GrafoMatrizAdjacencia<V extends Vertice> implements Grafo<V, Aresta<V, V>> {

    private double[][] grafo;
    private int numeroMaxVertices;
    private int numeroVertices;
    private int id;
    private final Orientacao orientacao;
    private final List<Aresta<V, V>> arestas;
    private final HashMap<V, Integer> verticeInteger;
    private final HashMap<Integer, V> integerVertice;


    public GrafoMatrizAdjacencia(Orientacao orientacao) {
        this.orientacao = orientacao;
        numeroMaxVertices = 10;
        numeroVertices = 0;
        arestas = new ArrayList<>();
        this.grafo = new double[numeroMaxVertices][numeroMaxVertices];
        inicializaMatriz();
        verticeInteger = new HashMap<>();
        integerVertice = new HashMap<>();
    }

    private void inicializaMatriz() {
        inicializaMatriz(this.grafo);
    }

    private void inicializaMatriz(double[][] matriz) {
        for (double[] linha : matriz) {
            for (int i = 0; i < linha.length; i++) {
                linha[i] = -1;
            }
        }
    }

    private void dobrarMatriz() {
        numeroMaxVertices *= 2;
        double[][] grafo = new double[numeroMaxVertices][numeroMaxVertices];
        inicializaMatriz(grafo);
        copiarMatriz(this.grafo, grafo);
        this.grafo = grafo;
        System.gc();
    }

    private void copiarMatriz(double[][] origem, double[][] destino) {
        for (int i = 0; i < origem.length; i++) {
            for (int j = 0; j < origem.length; j++) {
                destino[i][j] = origem[i][j];
            }
        }
    }

    @Override
    public Iterator<V> getVerticesAdjacentes(Vertice u) {
        List<V> verticesAdjacentes = new ArrayList<>();
        if (u == null || !verticeInteger.containsKey(u)) {
            return verticesAdjacentes.iterator();
        }
        int numeroVerticeMatriz = verticeInteger.get(u);
        for (int coluna = 0; coluna < grafo[numeroVerticeMatriz].length; coluna++) {
            if (grafo[numeroVerticeMatriz][coluna] >= 0) {
                verticesAdjacentes.add(integerVertice.get(coluna));
            }
        }
        return verticesAdjacentes.iterator();
    }

    @Override
    public Iterator<V> getVertices() {
        return verticeInteger.keySet().iterator();
    }

    @Override
    public Iterator<Aresta<V, V>> getArestas() {
        return arestas.iterator();
    }

    @Override
    public V getVertice(String idVertice) {
        for (V vertice : verticeInteger.keySet()) {
            if (vertice.getId().equals(idVertice)) {
                return vertice;
            }
        }
        return null;
    }

    @Override
    public void adicionaVertice(V verticeNoGrafo, V verticeAdicionado) {
        if (!this.verticeInteger.containsKey(verticeNoGrafo)) {
            return;
        }
        adicionaVertice(verticeAdicionado);
        Aresta aresta = new Aresta(verticeNoGrafo, verticeAdicionado);
        adicionaAresta(aresta);
    }

    @Override
    public void adicionaVertice(V verticeAdicionado) {
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
    public void adicionaAresta(Aresta<V, V> arestaAdicionada) {
        if (this.arestas.contains(arestaAdicionada)) {
            return;
        }
        if (arestaAdicionada.getVertice1() == null || arestaAdicionada.getVertice2() == null) {
            throw new RuntimeException("Não é possível adicionar uma aresta com vértice nulos no grafo");
        }
        if (!verticeInteger.containsKey(arestaAdicionada.getVertice1())) {
            adicionaVertice(arestaAdicionada.getVertice1());
        }
        if (!verticeInteger.containsKey(arestaAdicionada.getVertice2())) {
            adicionaVertice(arestaAdicionada.getVertice2());
        }

        grafo[verticeInteger.get(arestaAdicionada.getVertice1())][verticeInteger.get(arestaAdicionada.getVertice2())] = arestaAdicionada.getPeso();
        if (orientacao == Orientacao.NAO_DIRIGIDO) {
            grafo[verticeInteger.get(arestaAdicionada.getVertice2())][verticeInteger.get(arestaAdicionada.getVertice1())] = arestaAdicionada.getPeso();
        }
        arestas.add(arestaAdicionada);
    }

    @Override
    public void removerAresta(Aresta<V, V> arestaRemovida) {

        if (!this.arestas.contains(arestaRemovida)) {
            return;
        }
        if (arestaRemovida.getVertice1() == null || arestaRemovida.getVertice2() == null) {
            throw new RuntimeException("Não é possível adicionar uma aresta com vértice nulos no grafo");
        }
        if (!verticeInteger.containsKey(arestaRemovida.getVertice1())) {
            throw new RuntimeException("Não é possível remover uma aresta que não tenha no grafo");
        }
        if (!verticeInteger.containsKey(arestaRemovida.getVertice2())) {
            throw new RuntimeException("Não é possível remover uma aresta que não tenha no grafo");
        }
        grafo[verticeInteger.get(arestaRemovida.getVertice1())][verticeInteger.get(arestaRemovida.getVertice2())] = -1;
        arestas.remove(arestaRemovida);
        if (orientacao == Orientacao.NAO_DIRIGIDO) {
            grafo[verticeInteger.get(arestaRemovida.getVertice2())][verticeInteger.get(arestaRemovida.getVertice1())] = -1;
            arestas.remove(new Aresta(arestaRemovida.getVertice2(), arestaRemovida.getVertice1()));
        }
    }

    @Override
    public void removerVertice(Vertice verticeRemovido) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getQuantidadeVertices() {
        return this.numeroVertices;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
