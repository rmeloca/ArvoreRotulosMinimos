/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.cm.algoritmo;

import br.edu.utfpr.cm.factory.GrafoFactory;
import br.edu.utfpr.cm.factory.Orientacao;
import br.edu.utfpr.cm.factory.Representacao;
import br.edu.utfpr.cm.grafo.ArestaPonderada;
import br.edu.utfpr.cm.grafo.Grafo;
import br.edu.utfpr.cm.grafo.Vertice;
import org.junit.Test;

/**
 *
 * @author romulo
 */
public class AestrelaTest {

    private Aestrela aestrela;

    @Test
    public void testarAEstrela() {
        Grafo grafo = GrafoFactory.constroiGrafo(Representacao.MATRIZ_ADJACENCIA, Orientacao.NAO_DIRIGIDO);
        
        grafo.adicionaAresta(new ArestaPonderada(new Vertice("1"), new Vertice("2"), 1));
        grafo.adicionaAresta(new ArestaPonderada(new Vertice("3"), new Vertice("2"), 2));
        grafo.adicionaAresta(new ArestaPonderada(new Vertice("5"), new Vertice("2"), 4));
        grafo.adicionaAresta(new ArestaPonderada(new Vertice("2"), new Vertice("2"), 2));
        grafo.adicionaAresta(new ArestaPonderada(new Vertice("5"), new Vertice("2"), 7));
        grafo.adicionaAresta(new ArestaPonderada(new Vertice("5"), new Vertice("7"), 2));
        grafo.adicionaAresta(new ArestaPonderada(new Vertice("5"), new Vertice("2"), 9));
        grafo.adicionaAresta(new ArestaPonderada(new Vertice("5"), new Vertice("6"), 3));
        grafo.adicionaAresta(new ArestaPonderada(new Vertice("5"), new Vertice("6"), 2));
        grafo.adicionaAresta(new ArestaPonderada(new Vertice("8"), new Vertice("6"), 7));
        
        this.aestrela = new Aestrela(grafo);
        this.aestrela.execute();
    }

}
