package br.edu.utfpr.cm.algoritmo;

import br.edu.utfpr.cm.algoritmo.entidades.Cor;
import br.edu.utfpr.cm.algoritmo.entidades.CorVertice;
import br.edu.utfpr.cm.algoritmo.entidades.VerticeBuscaLargura;
import br.edu.utfpr.cm.grafo.Aresta;
import br.edu.utfpr.cm.grafo.Grafo;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;

public class BuscaLargura implements Algoritmo {

    private Grafo<VerticeBuscaLargura, Aresta<VerticeBuscaLargura, VerticeBuscaLargura>> g;
    private VerticeBuscaLargura s;
    PriorityQueue<VerticeBuscaLargura> queue;

    public BuscaLargura(Grafo<VerticeBuscaLargura, Aresta<VerticeBuscaLargura, VerticeBuscaLargura>> g, VerticeBuscaLargura verticeInicial) {
        if (g.getVertice(verticeInicial.getId()) == null) {
            throw new RuntimeException("O vértice de índice " + verticeInicial.getId() + " não pertence ao grafo " + g.toString() + ". "
                    + "Utilize um vértice válido como argumento do construtor da classe " + this.getClass().getName());
        } else {
            this.g = g;
            this.s = verticeInicial;
            queue = new PriorityQueue<>();
        }
    }

    //Algoritmo para inicialização do grafo na BFS 
    public void inicializaGrafo() {
        VerticeBuscaLargura u;
        while (this.g.getVertices().hasNext()) {
            u = this.g.getVertices().next();
            if (u != this.s) {
                u.setCor(new CorVertice(Cor.Branco));
                u.setDistancia((int) Float.POSITIVE_INFINITY);
                u.setPai(null);
            }
        }
        s.setDistancia(0);
        s.setPai(null);
        s.setCor(new CorVertice(Cor.Cinza));
        queue.add(s);

    }

    @Override
    public void executar() {
        VerticeBuscaLargura u;
        VerticeBuscaLargura v;
        Iterator<VerticeBuscaLargura> verticesAdjacentes;
        while (!queue.isEmpty()) {
            u = queue.poll();
            verticesAdjacentes = g.getVerticesAdjacentes(u);
            while (verticesAdjacentes.hasNext()) {
                v = verticesAdjacentes.next();
                if (v.getCor().getCor().equals(Cor.Branco)) {
                    v.getCor().setCor(Cor.Cinza);
                    v.setDistancia(u.getDistancia() + 1);
                    v.setPai(u);
                    queue.add(v);
                }
            }
            u.getCor().setCor(Cor.Preto);
        }
    }

    public List<VerticeBuscaLargura[]> executar(boolean isTest) {
        VerticeBuscaLargura u;
        VerticeBuscaLargura v;
        Iterator<VerticeBuscaLargura> verticesAdjacentes;
        List<VerticeBuscaLargura[]> listaFilas = new ArrayList<>();
        while (!queue.isEmpty()) {
            u = queue.poll();
            verticesAdjacentes = g.getVerticesAdjacentes(u);
            while (verticesAdjacentes.hasNext()) {
                v = verticesAdjacentes.next();
                if (v.getCor().getCor().equals(Cor.Branco)) {
                    v.getCor().setCor(Cor.Cinza);
                    v.setDistancia(u.getDistancia() + 1);
                    v.setPai(u);
                    queue.add(v);
                }
            }
            u.getCor().setCor(Cor.Preto);
            listaFilas.add(queue.toArray(new VerticeBuscaLargura[queue.size()]));
        }
        return listaFilas;
    }

    @Deprecated
    private PriorityQueue cloneQueue(PriorityQueue queue) {
        PriorityQueue clone = new PriorityQueue();
        Iterator iterator = queue.iterator();
        while (iterator.hasNext()) {
            clone.add(iterator.next());
        }
        return clone;
    }

    public void imprimeGrafo(Grafo<VerticeBuscaLargura, Aresta<VerticeBuscaLargura, VerticeBuscaLargura>> g, VerticeBuscaLargura s, VerticeBuscaLargura v) {
        if (v.equals(s)) {
            System.out.print(s);
        } else if (v.getPai() == null) {
            System.out.print("não há caminho entre " + s + " e " + v);
        } else {
            imprimeGrafo(g, s, v.getPai());
            System.out.print(v);
        }
    }
}
