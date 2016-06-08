/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.cm.algoritmo;

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
import org.junit.Test;

/**
 *
 * @author romulo
 */
public class AestrelaTest {

    private Aestrela arvoreGeradoraRotulosMinimos;

    public AestrelaTest() {
    }

    public void testFile(InputStream inputStream) throws IOException {
        testFile(Representacao.LISTA_ADJACENCIA, Orientacao.DIRIGIDO, inputStream);
    }

    public void testFile(Representacao representacao, Orientacao orientacao, InputStream inputStream) throws IOException {
        InputStreamReader inputStreamReader;
        BufferedReader bufferedReader;
        String line;
        String[] split;
        Grafo grafo;
        Vertice verticeOrigem;
        Vertice verticeDestino;
        int valorLabel;
        int quantidadeVertex;
        int quantidadeLabel;

        inputStreamReader = new InputStreamReader(inputStream);
        bufferedReader = new BufferedReader(inputStreamReader);
        line = "";
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
        throw new UnsupportedOperationException();
    }

    @Test
    public void testSomeMethod() {
    }

}
