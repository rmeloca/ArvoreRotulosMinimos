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
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author romulo
 */
public class BuscaProfundidadeTest {

    /**
     * Test of inicializaGrafo method, of class BuscaProfundidade.
     */
    @Test
    public void testInicializaGrafo() {
        Grafo grafo = GrafoFactory.constroiGrafo(Representacao.LISTA_ADJACENCIA, Orientacao.DIRIGIDO);
        BuscaProfundidade instance = new BuscaProfundidade(grafo, (VerticeBuscaProfundidade) grafo.getVertices().next());
        instance.inicializaGrafo();
    }

    /**
     * Test of executar method, of class BuscaProfundidade.
     */
    @Test
    public void testExecutar() {
        Grafo grafo = GrafoFactory.constroiGrafo(Representacao.LISTA_ADJACENCIA, Orientacao.DIRIGIDO);
        VerticeBuscaProfundidade verticeBuscaProfundidade = (VerticeBuscaProfundidade) grafo.getVertices().next();
        BuscaProfundidade instance = new BuscaProfundidade(grafo, verticeBuscaProfundidade);
        instance.inicializaGrafo();

        instance.executar();
    }

    /**
     * Test of imprimeGrafo method, of class BuscaProfundidade.
     */
    @Test
    public void testImprimeGrafo() {
        Grafo<VerticeBuscaProfundidade, Aresta<VerticeBuscaProfundidade, VerticeBuscaProfundidade>> g;
        g = GrafoFactory.constroiGrafo(Representacao.LISTA_ADJACENCIA, Orientacao.DIRIGIDO);

        VerticeBuscaProfundidade s = g.getVertices().next();
        BuscaProfundidade instance = new BuscaProfundidade(g, s);
        instance.inicializaGrafo();

        VerticeBuscaProfundidade v = g.getVertices().next();
        instance.imprimeGrafo(g, s, v);
    }

}
