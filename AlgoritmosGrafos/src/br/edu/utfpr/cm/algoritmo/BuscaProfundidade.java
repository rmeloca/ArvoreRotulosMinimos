package br.edu.utfpr.cm.algoritmo;

import br.edu.utfpr.cm.algoritmo.entidades.Cor;
import br.edu.utfpr.cm.algoritmo.entidades.VerticeBuscaProfundidade;
import br.edu.utfpr.cm.grafo.Aresta;
import br.edu.utfpr.cm.grafo.Grafo;
import java.util.Iterator;

public class BuscaProfundidade implements Algoritmo {

    private Grafo<VerticeBuscaProfundidade, Aresta<VerticeBuscaProfundidade, VerticeBuscaProfundidade>> g;
    private VerticeBuscaProfundidade s;
    private int timestamp;

    public BuscaProfundidade(Grafo<VerticeBuscaProfundidade, Aresta<VerticeBuscaProfundidade, VerticeBuscaProfundidade>> g, VerticeBuscaProfundidade verticeInicial) {
        if (g.getVertice(verticeInicial.getId()) == null) {
            throw new RuntimeException("O vértice de índice " + verticeInicial.getId() + " não pertence ao grafo " + g.toString() + ". "
                    + "Utilize um vértice válido como argumento do construtor da classe " + this.getClass().getName());
        } else {
            this.g = g;
            this.s = verticeInicial;
        }
        timestamp = 0;
    }

    public void inicializaGrafo() {
        Iterator<VerticeBuscaProfundidade> vertices;
        VerticeBuscaProfundidade u;
        vertices = g.getVertices();
        while (vertices.hasNext()) {
            u = vertices.next();
            u.getCor().setCor(Cor.Branco);
            u.setPai(null);
        }
        timestamp = 0;
        vertices = g.getVertices();
        while (vertices.hasNext()) {
            u = vertices.next();
            if (u.getCor().equals(Cor.Branco)) {
                executar(u);
            }
        }
    }

    private void executar(VerticeBuscaProfundidade u) {
        timestamp++;
        u.setTempoDescoberta(timestamp);
        u.getCor().setCor(Cor.Cinza);
        Iterator<VerticeBuscaProfundidade> verticesAdjacentes;
        verticesAdjacentes = g.getVerticesAdjacentes(u);
        VerticeBuscaProfundidade verticeAdjacente;
        while (verticesAdjacentes.hasNext()) {
            verticeAdjacente = verticesAdjacentes.next();
            if (verticeAdjacente.getCor().equals(Cor.Branco)) {
                verticeAdjacente.setPai(u);
                executar(verticeAdjacente);
            }

        }
        u.getCor().setCor(Cor.Preto);
        timestamp++;
        u.setTempoFinalizacao(timestamp);
    }

    @Override
    public void executar() {
        executar(s);
    }

    public void imprimeGrafo(Grafo<VerticeBuscaProfundidade, Aresta<VerticeBuscaProfundidade, VerticeBuscaProfundidade>> g, VerticeBuscaProfundidade s, VerticeBuscaProfundidade v) {
        Iterator iterator = g.getVertices();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

}
