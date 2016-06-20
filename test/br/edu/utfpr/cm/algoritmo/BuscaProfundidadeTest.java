/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.cm.algoritmo;

import br.edu.utfpr.cm.algoritmo.entidades.VerticeBuscaProfundidade;
import br.edu.utfpr.cm.factory.GrafoFactory;
import br.edu.utfpr.cm.factory.Orientacao;
import br.edu.utfpr.cm.factory.Representacao;
import br.edu.utfpr.cm.grafo.Aresta;
import br.edu.utfpr.cm.grafo.Grafo;
import br.edu.utfpr.cm.grafo.Vertice;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;
import org.junit.Test;

/**
 *
 * @author romulo
 */
public class BuscaProfundidadeTest {

    /**
     * Test of dfs method, of class BuscaProfundidade.
     */
    public void testInicializaGrafo() throws IOException {
        InputStream inputStream = (getClass().getResourceAsStream("../instancias/group_1/HDGraph20_20.txt"));
        List<Grafo<VerticeBuscaProfundidade, Aresta<VerticeBuscaProfundidade, VerticeBuscaProfundidade>>> lista;
        lista = GrafoFactory.lerGrafos(Representacao.MATRIZ_ADJACENCIA, Orientacao.NAO_DIRIGIDO, inputStream);
        Grafo g = lista.get(0);
        VerticeBuscaProfundidade s = new VerticeBuscaProfundidade();
        s.setId("a");
        g.adicionaVertice(s);

        s = new VerticeBuscaProfundidade();
        s.setId("b");
        g.adicionaVertice(s);

        s = new VerticeBuscaProfundidade();
        s.setId("c");
        g.adicionaVertice(s);

        g.adicionaAresta(new Aresta(new VerticeBuscaProfundidade("d"), new VerticeBuscaProfundidade("e")));
        g.adicionaAresta(new Aresta(new VerticeBuscaProfundidade("e"), new VerticeBuscaProfundidade("f")));
        g.adicionaAresta(new Aresta(new VerticeBuscaProfundidade("f"), new VerticeBuscaProfundidade("g")));
        g.adicionaAresta(new Aresta(new VerticeBuscaProfundidade("g"), new VerticeBuscaProfundidade("e")));

        BuscaProfundidade instance = new BuscaProfundidade(g, ((Vertice) g.getVertices().next()).toVerticeBuscaProfundidade());
        instance.dfs();
        instance.removeArestasRetorno();
        System.out.println("Busca Profundidade: ");
        for (Iterator<Aresta> iterator = instance.getArestasRetorno().iterator(); iterator.hasNext();) {
            Aresta next = iterator.next();
            System.out.println("v1:" + next.getVertice1().toString() + "V2: " + next.getVertice2().toString());
        }

    }

    /**
     * Test of executar method, of class BuscaProfundidade.
     */
    @Test
    public void testExecutar() throws IOException {
        InputStream inputStream = (getClass().getResourceAsStream("../instancias/group_2_with_n=400/LDGraph400_100.txt"));
        List<Grafo<VerticeBuscaProfundidade, Aresta<VerticeBuscaProfundidade, VerticeBuscaProfundidade>>> lista;
        lista = GrafoFactory.lerGrafos(Representacao.MATRIZ_ADJACENCIA, Orientacao.NAO_DIRIGIDO, inputStream);
        Grafo<VerticeBuscaProfundidade, Aresta<VerticeBuscaProfundidade, VerticeBuscaProfundidade>> g;
        g = lista.get(0);
        
        VerticeBuscaProfundidade s;
        s = g.getVertices().next();
        
        BuscaProfundidade instance = new BuscaProfundidade(g, s);
        instance.dfs();
        instance.removeArestasRetorno();
        instance.imprimeGrafo();
    }

    /**
     * Test of imprimeGrafo method, of class BuscaProfundidade.
     */
    public void testImprimeGrafo() throws IOException {
        InputStream inputStream = (getClass().getResourceAsStream("../instancias/group_1/HDGraph20_20.txt"));
        List<Grafo<VerticeBuscaProfundidade, Aresta<VerticeBuscaProfundidade, VerticeBuscaProfundidade>>> lista = GrafoFactory.lerGrafos(Representacao.MATRIZ_ADJACENCIA, Orientacao.NAO_DIRIGIDO, inputStream);
        Grafo<VerticeBuscaProfundidade, Aresta<VerticeBuscaProfundidade, VerticeBuscaProfundidade>> g = lista.get(0);
        VerticeBuscaProfundidade s;

        s = g.getVertices().next();
        BuscaProfundidade instance = new BuscaProfundidade(g, s);
        instance.dfs();

        VerticeBuscaProfundidade v = g.getVertices().next();
        System.out.println("Imprime grafo");
        instance.imprimeGrafo();
        System.out.println("FIm - Imprime grafo");
    }

}
