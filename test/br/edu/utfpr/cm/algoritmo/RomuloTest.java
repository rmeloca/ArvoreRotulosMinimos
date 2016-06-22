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
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Test;

/**
 *
 * @author romulo
 */
public class RomuloTest {

    public RomuloTest() {
    }

    @Test
    public void testSomeMethod() {
        InputStream inputStream;
        List<Grafo<VerticeBuscaProfundidade, Aresta<VerticeBuscaProfundidade, VerticeBuscaProfundidade>>> lista;
        try {
            inputStream = (getClass().getResourceAsStream("../instancias/group_2_with_n=500/HDGraph500_625.txt"));
//            inputStream = (getClass().getResourceAsStream("../instancias/group_2_with_n=500/HDGraph100_25.txt"));
            lista = GrafoFactory.lerGrafos(Representacao.MATRIZ_ADJACENCIA, Orientacao.NAO_DIRIGIDO, inputStream);
            Grafo<VerticeBuscaProfundidade, Aresta<VerticeBuscaProfundidade, VerticeBuscaProfundidade>> grafo;
            grafo = lista.get(0);
            List<Aresta> listaArestas = new ArrayList<>();
            Iterator<Aresta<VerticeBuscaProfundidade, VerticeBuscaProfundidade>> arestas = grafo.getArestas();
            while (arestas.hasNext()) {
                Aresta<VerticeBuscaProfundidade, VerticeBuscaProfundidade> aresta = arestas.next();
                System.out.println(aresta.getVertice1() + " --" + aresta.getPeso() + "-- " + aresta.getVertice2());
                listaArestas.add(aresta);
            }
            Romulo rmRomulo = new Romulo(listaArestas);
            rmRomulo.executar();
            List<Aresta> selectedEdges = rmRomulo.getSelectedEdges();
            System.out.println("*****");
            for (Aresta aresta : selectedEdges) {
                System.out.println(aresta.getVertice1() + " --" + aresta.getPeso() + "-- " + aresta.getVertice2());

            }

        } catch (IOException ex) {
            Logger.getLogger(AestrelaTest.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
