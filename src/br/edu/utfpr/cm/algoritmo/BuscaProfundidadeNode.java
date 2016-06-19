package br.edu.utfpr.cm.algoritmo;

import br.edu.utfpr.cm.algoritmo.entidades.Cor;
import br.edu.utfpr.cm.algoritmo.entidades.CorVertice;
import br.edu.utfpr.cm.algoritmo.entidades.VerticeBuscaProfundidade;
import br.edu.utfpr.cm.grafo.Aresta;
import br.edu.utfpr.cm.grafo.ArestaPonderada;
import br.edu.utfpr.cm.grafo.Grafo;
import br.edu.utfpr.cm.grafo.Vertice;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BuscaProfundidadeNode implements Algoritmo {

    private final List<VerticeBuscaProfundidade> listVertices;
    private final List<ArestaPonderada> listArestas;
    private Grafo<VerticeBuscaProfundidade, ArestaPonderada<VerticeBuscaProfundidade, VerticeBuscaProfundidade>> g;
    private VerticeBuscaProfundidade s;
    private List<ArestaPonderada> arestasRetorno;
    private int timestamp;

    public BuscaProfundidadeNode(List<VerticeBuscaProfundidade> listVertices, List<ArestaPonderada> listaArestas) {
        this.listVertices = listVertices;
        this.listArestas = listaArestas;
        populaGrafo();
        VerticeBuscaProfundidade verticeInicial = g.getVertice("0");
        if (g.getVertice(verticeInicial.getId()) == null) {
            throw new RuntimeException("O vértice de índice " + verticeInicial.getId() + " não pertence ao grafo " + g.toString() + ". "
                    + "Utilize um vértice válido como argumento do construtor da classe " + this.getClass().getName());
        } else {
            this.s = verticeInicial;
            arestasRetorno = new ArrayList<>();
        }
        timestamp = 0;
    }
    public void populaGrafo(){
        for (VerticeBuscaProfundidade next : listVertices) {
            g.adicionaVertice(next);
        }
        for (ArestaPonderada next : listArestas) {
            g.adicionaAresta(next);
        }
    
    }
    public void dfs() {
        Iterator<VerticeBuscaProfundidade> vertices;
        VerticeBuscaProfundidade u;
        vertices = g.getVertices();
        while (vertices.hasNext()) {
            u = vertices.next();
//            u = ((Vertice) vertices.next()).toVerticeBuscaProfundidade();
            u.setCor(new CorVertice(Cor.Branco));
            u.setPai(null);
        }
        timestamp = 0;
        vertices = g.getVertices();
        while (vertices.hasNext()) {
//            u = ((Vertice) vertices.next()).toVerticeBuscaProfundidade();
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
//        VerticeBuscaProfundidade v;

//        v = g.getVertice(idVertice);
//        u = v.toVerticeBuscaProfundidade();
        u = g.getVertice(idVertice);
        timestamp++;
        u.setTempoDescoberta(timestamp);
        u.setCor(new CorVertice(Cor.Cinza));
        verticesAdjacentes = g.getVerticesAdjacentes(u);
        while (verticesAdjacentes.hasNext()) {
//            verticeAdjacente = ((Vertice) verticesAdjacentes.next()).toVerticeBuscaProfundidade();
            verticeAdjacente = verticesAdjacentes.next();
            if (verticeAdjacente.getCor().equals(Cor.Branco)) {
                verticeAdjacente.setPai(u);
                dfsVisit(verticeAdjacente.getId());
            } else if (verticeAdjacente.getCor().equals(Cor.Cinza) && !u.getPai().equals(verticeAdjacente)) {
                arestasRetorno.add(new ArestaPonderada(u, verticeAdjacente));
            }

        }
        u.setCor(new CorVertice(Cor.Preto));
        timestamp++;
        u.setTempoFinalizacao(timestamp);
    }

    @Override
    public void dfs_visit() {
        BuscaProfundidadeNode.this.dfsVisit(s.getId());
    }

    public void imprimeGrafo(Grafo<VerticeBuscaProfundidade, ArestaPonderada<VerticeBuscaProfundidade, VerticeBuscaProfundidade>> g) {
        Iterator iterator = g.getVertices();
        System.out.println("Vertices: ");
        Vertice ver;
        while (iterator.hasNext()) {
            ver = (Vertice) iterator.next();
            System.out.print(ver);
            if (ver instanceof VerticeBuscaProfundidade) {
                System.out.println("  Cor: " + ((VerticeBuscaProfundidade) ver).getCor() + "Tempo desc: " + ((VerticeBuscaProfundidade) ver).getTempoDescoberta());
                System.out.println(" Timestamp:" + ((VerticeBuscaProfundidade) ver).getTempoDescoberta());
                System.out.println(" Timestamp:" + ((VerticeBuscaProfundidade) ver).getTempoFinalizacao());

            }
        }
        System.out.println("Fim Vertices");
        iterator = g.getArestas();
        System.out.println("Arestas: ");
        Aresta a;
        while (iterator.hasNext()) {
            a = (Aresta) iterator.next();
            System.out.println("V1: " + a.getVertice1() + "V2:" + a.getVertice2());
            if (a instanceof ArestaPonderada) {
                System.out.print("Peso: " + ((ArestaPonderada) a).getPeso());
            }
        }
        System.out.println("Fim Arestas");
    }

    public List<ArestaPonderada> getArestasRetorno() {
        return arestasRetorno;
    }

    public void removeArestasRetorno() {
        for (ArestaPonderada aresta : arestasRetorno) {
            g.removerAresta(aresta);
        }
    }

    public Grafo<VerticeBuscaProfundidade, ArestaPonderada<VerticeBuscaProfundidade, VerticeBuscaProfundidade>> getG() {
        return g;
    }

}
