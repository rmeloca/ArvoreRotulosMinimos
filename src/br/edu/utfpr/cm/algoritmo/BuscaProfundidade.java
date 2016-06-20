package br.edu.utfpr.cm.algoritmo;

import br.edu.utfpr.cm.algoritmo.entidades.Cor;
import br.edu.utfpr.cm.algoritmo.entidades.VerticeBuscaProfundidade;
import br.edu.utfpr.cm.grafo.Aresta;
import br.edu.utfpr.cm.grafo.Grafo;
import br.edu.utfpr.cm.grafo.Vertice;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BuscaProfundidade implements Algoritmo {

    private Grafo<VerticeBuscaProfundidade, Aresta<VerticeBuscaProfundidade, VerticeBuscaProfundidade>> g;
    private VerticeBuscaProfundidade s;
    private List<Aresta> arestasRetorno;
    private int timestamp;

    public BuscaProfundidade(Grafo<VerticeBuscaProfundidade, Aresta<VerticeBuscaProfundidade, VerticeBuscaProfundidade>> g, VerticeBuscaProfundidade verticeInicial) {
        if (g.getVertice(verticeInicial.getId()) == null) {
            throw new RuntimeException("O vértice de índice " + verticeInicial.getId() + " não pertence ao grafo " + g.toString() + ". "
                    + "Utilize um vértice válido como argumento do construtor da classe " + this.getClass().getName());
        } else {
            this.g = g;
            this.s = verticeInicial;
            arestasRetorno = new ArrayList<>();
        }
        timestamp = 0;
    }

    public void dfs() {
        Iterator<VerticeBuscaProfundidade> vertices;
        VerticeBuscaProfundidade u;
        vertices = g.getVertices();
        while (vertices.hasNext()) {
            u = vertices.next();
            u.setCor(Cor.Branco);
            u.setPai(null);
        }
        timestamp = 0;
        vertices = g.getVertices();
        while (vertices.hasNext()) {
            u = vertices.next();
            if (u.getCor().equals(Cor.Branco)) {
                dfsVisit(u.getId());
            }
        }
    }

    private void dfsVisit(String idVertice) {
        VerticeBuscaProfundidade u;
        Iterator<VerticeBuscaProfundidade> verticesAdjacentes;
        VerticeBuscaProfundidade verticeAdjacente;
        u = g.getVertice(idVertice);
        timestamp++;
        u.setTempoDescoberta(timestamp);
        u.setCor(Cor.Cinza);
        verticesAdjacentes = g.getVerticesAdjacentes(u);
        while (verticesAdjacentes.hasNext()) {
            verticeAdjacente = verticesAdjacentes.next();
            if (verticeAdjacente.getCor().equals(Cor.Branco)) {
                verticeAdjacente.setPai(u);
                dfsVisit(verticeAdjacente.getId());
            } else if (verticeAdjacente.getCor().equals(Cor.Cinza) && !u.getPai().equals(verticeAdjacente)) {
                arestasRetorno.add(new Aresta(u, verticeAdjacente));
            }

        }
        u.setCor(Cor.Preto);
        timestamp++;
        u.setTempoFinalizacao(timestamp);
    }

    @Override
    public void executar() {
        BuscaProfundidade.this.dfsVisit(s.getId());
    }

    public void imprimeGrafo() {
        Iterator<VerticeBuscaProfundidade> iteratorVertice;
        VerticeBuscaProfundidade verticeBuscaProfundidade;
        Aresta<VerticeBuscaProfundidade, VerticeBuscaProfundidade> aresta;
        Iterator<Aresta<VerticeBuscaProfundidade, VerticeBuscaProfundidade>> iteratorAresta;

        iteratorVertice = g.getVertices();
        while (iteratorVertice.hasNext()) {
            verticeBuscaProfundidade = iteratorVertice.next();
            System.out.print(verticeBuscaProfundidade);
            System.out.println("  Cor: " + verticeBuscaProfundidade.getCor());
            System.out.println(" Descoberta:" + verticeBuscaProfundidade.getTempoDescoberta());
            System.out.println(" Finalizacao:" + verticeBuscaProfundidade.getTempoFinalizacao());
        }
        iteratorAresta = g.getArestas();
        while (iteratorAresta.hasNext()) {
            aresta = iteratorAresta.next();
            System.out.println(aresta.getVertice1() + " --" + aresta.getPeso() + "-- " + aresta.getVertice2());
        }

    }

    public List<Aresta> getArestasRetorno() {
        return arestasRetorno;
    }

    public void removeArestasRetorno() {
        for (Aresta aresta : arestasRetorno) {
            g.removerAresta(aresta);
        }
    }

    public Grafo<VerticeBuscaProfundidade, Aresta<VerticeBuscaProfundidade, VerticeBuscaProfundidade>> getG() {
        return g;
    }

}
