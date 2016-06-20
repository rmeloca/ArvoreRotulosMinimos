package br.edu.utfpr.cm.factory;

import br.edu.utfpr.cm.algoritmo.entidades.VerticeBuscaProfundidade;
import java.util.Iterator;

import br.edu.utfpr.cm.grafo.Aresta;
import br.edu.utfpr.cm.grafo.Grafo;
import br.edu.utfpr.cm.grafo.Vertice;
import static org.junit.Assert.*;
import org.junit.Test;

public class GrafoListaAdjacenciaTest {

    @Test
    public void testeVertice() {
        Grafo<Vertice, Aresta<Vertice, Vertice>> g = GrafoFactory
                .constroiGrafo(Representacao.LISTA_ADJACENCIA, Orientacao.DIRIGIDO);
        Vertice a = new VerticeBuscaProfundidade("a");
        g.adicionaVertice(a);

        // 'a' foi adicionado ao grafo
        assertEquals(a, g.getVertice(a.getId()));

        Vertice b = new VerticeBuscaProfundidade("b");
        Vertice c = new VerticeBuscaProfundidade("c");
        g.adicionaVertice(a, b);
        g.adicionaVertice(a, c);

        // 'b' e 'c' foram adicionados ao grafo
        assertEquals(b, g.getVertice(b.getId()));
        assertEquals(c, g.getVertice(c.getId()));

        // todas as arestas tem 'a' como antecessor
        Iterator<Aresta<Vertice, Vertice>> iteratorAresta = g.getArestas();
        while (iteratorAresta.hasNext()) {
            Aresta<Vertice, Vertice> aresta = iteratorAresta.next();
            assertEquals(a, aresta.getVertice1());
        }

        // 'a' tem 'b' e 'c' como sucessores
        Iterator<Vertice> iteratorVertice = g.getVerticesAdjacentes(a);
        while (iteratorVertice.hasNext()) {
            Vertice u = iteratorVertice.next();
            assertTrue(u.equals(b) || u.equals(c));
        }

        Vertice d = new VerticeBuscaProfundidade("d");
        Vertice e = new VerticeBuscaProfundidade("e");
        Vertice f = new VerticeBuscaProfundidade("f");

        g.adicionaVertice(b, d);
        g.adicionaVertice(b, e);
        g.adicionaVertice(b, f);

        // as arestas 'd', 'e' e 'f' tem 'b' como antecessor
        iteratorAresta = g.getArestas();
        while (iteratorAresta.hasNext()) {
            Aresta<Vertice, Vertice> aresta = iteratorAresta.next();
            if (aresta.getVertice1().getId().equals("b")) {
                assertTrue(aresta.getVertice2().getId().equals("d")
                        || aresta.getVertice2().getId().equals("e")
                        || aresta.getVertice2().getId().equals("f"));
            }
        }

        // 'b' tem 'd', 'e' e 'f' como sucessores
        iteratorVertice = g.getVerticesAdjacentes(b);
        while (iteratorVertice.hasNext()) {
            Vertice u = iteratorVertice.next();
            assertTrue(u.equals(d) || u.equals(e) || u.equals(f));
        }
    }

    @Test
    public void testeAresta() {
        Grafo<Vertice, Aresta<Vertice, Vertice>> g = GrafoFactory
                .constroiGrafo(Representacao.LISTA_ADJACENCIA, Orientacao.DIRIGIDO);
        Vertice v1 = new VerticeBuscaProfundidade("a");
        Vertice v2 = new VerticeBuscaProfundidade("b");
        Aresta<Vertice, Vertice> a1 = new Aresta<Vertice, Vertice>(v1, v2);
        g.adicionaAresta(a1);

        Iterator<Aresta<Vertice, Vertice>> i = g.getArestas();
        while (i.hasNext()) {
            Aresta<Vertice, Vertice> aresta = i.next();
            assertTrue(aresta.getVertice1().equals(v1));
            assertTrue(aresta.getVertice2().equals(v2));
        }

        Vertice v3 = new VerticeBuscaProfundidade("a");
        Vertice v4 = new VerticeBuscaProfundidade("c");
        Aresta<Vertice, Vertice> a2 = new Aresta<Vertice, Vertice>(v3, v4);
        g.adicionaAresta(a2);

        assertFalse(g.getVerticesAdjacentes(v4).hasNext());

        i = g.getArestas();
        while (i.hasNext()) {
            Aresta<Vertice, Vertice> aresta = i.next();
            assertTrue(aresta.getVertice1().equals(v1));
            assertTrue(aresta.getVertice2().equals(v4)
                    || aresta.getVertice2().equals(v2));
        }

        Vertice v5 = new VerticeBuscaProfundidade("x");
        Vertice v6 = new VerticeBuscaProfundidade("y");
        Aresta<Vertice, Vertice> a3 = new Aresta<Vertice, Vertice>(v5, v6);
        g.adicionaAresta(a3);

        i = g.getArestas();
        while (i.hasNext()) {
            Aresta<Vertice, Vertice> aresta = i.next();
            if (aresta.getVertice1() == v5) {
                assertTrue(aresta.getVertice2().equals(v6));
            }
        }
    }
}
