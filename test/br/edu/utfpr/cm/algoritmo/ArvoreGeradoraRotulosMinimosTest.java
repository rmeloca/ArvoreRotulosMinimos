/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.cm.algoritmo;

import br.edu.utfpr.cm.factory.GrafoFactory;
import static br.edu.utfpr.cm.factory.GrafoFactory.constroiGrafo;
import br.edu.utfpr.cm.factory.Orientacao;
import br.edu.utfpr.cm.factory.Representacao;
import br.edu.utfpr.cm.grafo.ArestaPonderada;
import br.edu.utfpr.cm.grafo.Grafo;
import br.edu.utfpr.cm.grafo.Vertice;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Test;

/**
 *
 * @author romulo
 */
public class ArvoreGeradoraRotulosMinimosTest {

    private ArvoreGeradoraRotulosMinimos arvoreGeradoraRotulosMinimos;

    public ArvoreGeradoraRotulosMinimosTest() {
    }

    public void testar() {
        try {
            testFile(getClass().getResourceAsStream("../instancias/group_1/HDGraph20_20.txt"));
        } catch (IOException ex) {
            Logger.getLogger(ArvoreGeradoraRotulosMinimosTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void testFile(InputStream inputStream) throws IOException {
        testFile(Representacao.LISTA_ADJACENCIA, Orientacao.DIRIGIDO, inputStream);
    }

    public void testFile(Representacao representacao, Orientacao orientacao, InputStream inputStream) throws IOException {
        InputStreamReader inputStreamReader;
        BufferedReader bufferedReader;
        String line;
        String[] split;
        Grafo grafo = null;
        Vertice verticeOrigem;
        Vertice verticeDestino;
        int valorLabel;
        int quantidadeVertex;
        int quantidadeLabel;

        inputStreamReader = new InputStreamReader(inputStream);
        bufferedReader = new BufferedReader(inputStreamReader);
        line = "";
        System.out.println("oiee");

        while (line != null) {
            line = bufferedReader.readLine();
            split = line.split(" ");
            grafo = constroiGrafo(representacao, orientacao);
            quantidadeVertex = Integer.valueOf(split[0]);
            quantidadeLabel = Integer.valueOf(split[1]);

            for (int i = 0; i < quantidadeVertex - 1; i++) {
                grafo.adicionaVertice(new Vertice(String.valueOf(i)));
            }

            for (int i = quantidadeVertex - 1; i >= 0; i++) {
                line = bufferedReader.readLine();
                split = line.split(" ");
                verticeOrigem = grafo.getVertice(String.valueOf(i));
                for (int j = 0; j < quantidadeVertex; j++) {
                    verticeDestino = grafo.getVertice(String.valueOf(j));
                    valorLabel = Integer.valueOf(split[j]);
                    if (valorLabel != quantidadeLabel) {
                        grafo.adicionaAresta(new ArestaPonderada(verticeOrigem, verticeDestino, valorLabel));
                    }
                }
            }
            arvoreGeradoraRotulosMinimos.executar();
            //calcula
            line = bufferedReader.readLine();
        }
        if (grafo != null) {
            for (Iterator iterator = grafo.getVertices(); iterator.hasNext();) {
                Object next = iterator.next();
                System.out.println("V:" + next.toString());

            }
        }
        System.out.println("oi");
        throw new UnsupportedOperationException();
    }

    @Test
    public void testLerGrafos() throws IOException {
        InputStream inputStream = (getClass().getResourceAsStream("../instancias/group_1/HDGraph20_20.txt"));
        List<Grafo> lista = GrafoFactory.lerGrafos(Representacao.LISTA_ADJACENCIA, Orientacao.DIRIGIDO, inputStream);
        Grafo g = lista.get(0);
        while (g.getVertices().hasNext()) {
            Object next = g.getVertices().next();
            System.out.println("Vertice "+next.toString());
        }
    }

}
