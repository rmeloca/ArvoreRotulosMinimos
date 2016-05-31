package br.edu.utfpr.cm.algoritmo;

import java.util.HashMap;

import br.edu.utfpr.cm.algoritmo.entidades.VerticeBuscaLargura;
import br.edu.utfpr.cm.factory.GrafoFactory;
import br.edu.utfpr.cm.factory.Orientacao;
import br.edu.utfpr.cm.factory.Representacao;
import br.edu.utfpr.cm.grafo.Aresta;
import br.edu.utfpr.cm.grafo.Grafo;
import br.edu.utfpr.cm.grafo.Vertice;
import java.util.Iterator;
import java.util.List;
import static org.junit.Assert.*;

import org.junit.Test;

public class BuscaLarguraTest {

    Grafo<VerticeBuscaLargura, Aresta<VerticeBuscaLargura, VerticeBuscaLargura>> g;
    HashMap<Vertice, Integer> distanciasMinimas = new HashMap<>();
    VerticeBuscaLargura s;

    public BuscaLarguraTest() {
        g = GrafoFactory.constroiGrafo(Representacao.LISTA_ADJACENCIA, Orientacao.DIRIGIDO);

        //vértice inicial
        s = new VerticeBuscaLargura("s");
        g.adicionaVertice(s);

        //armazena a distancia de 's' até 's
        distanciasMinimas.put(s, 0);

        //criação dos vértices
        VerticeBuscaLargura r = new VerticeBuscaLargura("r");
        VerticeBuscaLargura t = new VerticeBuscaLargura("t");
        VerticeBuscaLargura u = new VerticeBuscaLargura("u");
        VerticeBuscaLargura v = new VerticeBuscaLargura("v");
        VerticeBuscaLargura w = new VerticeBuscaLargura("w");
        VerticeBuscaLargura x = new VerticeBuscaLargura("x");
        VerticeBuscaLargura y = new VerticeBuscaLargura("y");

        //adição dos vértices
        g.adicionaVertice(s, r); //s-r
        g.adicionaVertice(s, w); //s-w
        g.adicionaVertice(r, v); //r-v
        g.adicionaVertice(w, t); //w-t
        g.adicionaVertice(w, x); //w-x
        g.adicionaVertice(x, u); //x-u
        g.adicionaVertice(x, y); //x-y
        g.adicionaVertice(x, t); //x-t
        g.adicionaVertice(t, u); //t-u
        g.adicionaVertice(u, y); //u-y

        BuscaLargura bl = new BuscaLargura(g, s);
        bl.executar();
    }

    @Test
    public void Lema1() {
        Iterator<Aresta<VerticeBuscaLargura, VerticeBuscaLargura>> arestas = g.getArestas();
        Aresta<VerticeBuscaLargura, VerticeBuscaLargura> aresta;
        while (arestas.hasNext()) {
            aresta = arestas.next();
            assertTrue(distanciasMinimas.get(aresta.getVertice1()) <= distanciasMinimas.get(aresta.getVertice2()) + 1L);
        }
    }

    @Test
    public void Lema2() {
        Iterator<VerticeBuscaLargura> vertices = g.getVertices();
        VerticeBuscaLargura vertice;
        while (vertices.hasNext()) {
            vertice = vertices.next();
            assertTrue(vertice.getDistancia() >= distanciasMinimas.get(vertice));
        }
    }

    @Test
    public void Lema3() {
        BuscaLargura b2 = new BuscaLargura(g, s);
        List<VerticeBuscaLargura[]> listaArrayVertice = b2.executar(true);
        for (VerticeBuscaLargura[] verticeBuscaLarguras : listaArrayVertice) {
            assertTrue(verticeBuscaLarguras[verticeBuscaLarguras.length].getDistancia() <= verticeBuscaLarguras[0].getDistancia() + 1);
            for (int i = 1; i < verticeBuscaLarguras.length - 1; i += 2) {
                VerticeBuscaLargura verticeBuscaLargura1 = verticeBuscaLarguras[i];
                VerticeBuscaLargura verticeBuscaLargura2 = verticeBuscaLarguras[i + 1];
                assertTrue(verticeBuscaLargura1.getDistancia() <= verticeBuscaLargura2.getDistancia());
            }
        }
    }

    @Test
    public void TeoremaCorretude() {

        fail("Not yet implemented");
    }

}
