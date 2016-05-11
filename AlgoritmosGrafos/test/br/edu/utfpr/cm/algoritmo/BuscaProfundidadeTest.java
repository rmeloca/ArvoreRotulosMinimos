/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.cm.algoritmo;

import br.edu.utfpr.cm.algoritmo.entidades.VerticeBuscaProfundidade;
import br.edu.utfpr.cm.grafo.Aresta;
import br.edu.utfpr.cm.grafo.Grafo;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author romulo
 */
public class BuscaProfundidadeTest {

    public BuscaProfundidadeTest() {
    }

    /**
     * Test of inicializaGrafo method, of class BuscaProfundidade.
     */
    @Test
    public void testInicializaGrafo() {
        System.out.println("inicializaGrafo");
        BuscaProfundidade instance = null;
        instance.inicializaGrafo();
        fail("The test case is a prototype.");
    }

    /**
     * Test of executar method, of class BuscaProfundidade.
     */
    @Test
    public void testExecutar() {
        System.out.println("executar");
        VerticeBuscaProfundidade verticeBuscaProfundidade = new VerticeBuscaProfundidade();
        BuscaProfundidade instance = null;
        instance.executar();
        fail("The test case is a prototype.");
    }

    /**
     * Test of imprimeGrafo method, of class BuscaProfundidade.
     */
    @Test
    public void testImprimeGrafo() {
        System.out.println("imprimeGrafo");
        Grafo<VerticeBuscaProfundidade, Aresta<VerticeBuscaProfundidade, VerticeBuscaProfundidade>> g = null;
        VerticeBuscaProfundidade s = null;
        VerticeBuscaProfundidade v = null;
        BuscaProfundidade instance = null;
        instance.imprimeGrafo(g, s, v);
        fail("The test case is a prototype.");
    }

}
