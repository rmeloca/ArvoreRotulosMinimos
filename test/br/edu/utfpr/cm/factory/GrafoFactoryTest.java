/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.cm.factory;

import br.edu.utfpr.cm.grafo.Aresta;
import br.edu.utfpr.cm.grafo.ArestaPonderada;
import br.edu.utfpr.cm.grafo.Grafo;
import br.edu.utfpr.cm.grafo.Vertice;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;
import org.junit.Test;

/**
 *
 * @author marco
 */
public class GrafoFactoryTest {

    @Test
    public void testLerGrafos() throws IOException {
        InputStream inputStream = (getClass().getResourceAsStream("../instancias/group_1/HDGraph20_20.txt"));
        List<Grafo> lista = GrafoFactory.lerGrafos(Representacao.MATRIZ_ADJACENCIA, Orientacao.DIRIGIDO, inputStream);
        Grafo g = lista.get(0);
        for (Iterator<Vertice> iterator = g.getVertices(); iterator.hasNext();) {
            Vertice next = iterator.next();
            System.out.println(next.getId());
        }
        for (Iterator<Aresta> iterator = g.getArestas(); iterator.hasNext();) {
            Aresta<Vertice,Vertice> next = iterator.next();
            if(next instanceof ArestaPonderada){
                ArestaPonderada nextPonderada = (ArestaPonderada) next;
            System.out.println("v1: "+ nextPonderada.getVertice1());
            System.out.println("v2: "+ nextPonderada.getVertice2());
            System.out.println("Peso: "+ nextPonderada.getPeso() + "\n\n");
            }
        }
        System.out.println("----------------------------------------");
        g.removerAresta(new Aresta(new Vertice("19"), new Vertice("0")));
        System.out.println("----------------------------------------");
        
        for (Iterator<Aresta> iterator = g.getArestas(); iterator.hasNext();) {
            Aresta<Vertice,Vertice> next = iterator.next();
            if(next instanceof ArestaPonderada){
                ArestaPonderada nextPonderada = (ArestaPonderada) next;
            System.out.println("v1: "+ nextPonderada.getVertice1());
            System.out.println("v2: "+ nextPonderada.getVertice2());
            System.out.println("Peso: "+ nextPonderada.getPeso() + "\n\n");
            }
        }
        
    }

}
