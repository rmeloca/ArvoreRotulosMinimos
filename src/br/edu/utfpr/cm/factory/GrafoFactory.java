package br.edu.utfpr.cm.factory;

import br.edu.utfpr.cm.grafo.Aresta;
import br.edu.utfpr.cm.grafo.ArestaPonderada;
import br.edu.utfpr.cm.grafo.Grafo;
import br.edu.utfpr.cm.grafo.Vertice;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GrafoFactory implements Factory {

    public static Grafo constroiGrafo(Representacao r, Orientacao orientacao) {
        switch (r) {
            case LISTA_ADJACENCIA:
                return new GrafoListaAdjacencia(orientacao);
            case MATRIZ_ADJACENCIA:
                return new GrafoMatrizAdjacencia(orientacao);
        }
        return null;
    }

    public void lerGrafo(Representacao representacao, Orientacao orientacao, InputStream inputStream) throws IOException {
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        String line = "";
        String[] split;
        Grafo grafo;
        Vertice verticeOrigem;
        Vertice verticeDestino;
        int valorLabel;
        int quantidadeVertex;
        int quantidadeLabel;

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
            //calcula
        }
        throw new UnsupportedOperationException();
    }

}
