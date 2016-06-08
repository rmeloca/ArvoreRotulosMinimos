package br.edu.utfpr.cm.factory;

import br.edu.utfpr.cm.grafo.ArestaPonderada;
import br.edu.utfpr.cm.grafo.Grafo;
import br.edu.utfpr.cm.grafo.Vertice;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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

    public static List<Grafo> lerGrafos(Representacao representacao, Orientacao orientacao, InputStream inputStream) throws IOException {
        List<Grafo> listaGrafos = new ArrayList<>();
        InputStreamReader inputStreamReader;
        BufferedReader bufferedReader;
        String line;
        String[] split;
        Grafo grafo = null;
        Vertice verticeOrigem;
        Vertice verticeDestino;
        int valorLabel;
        int quantidadeVertices;
        int quantidadeLabels;

        inputStreamReader = new InputStreamReader(inputStream);
        bufferedReader = new BufferedReader(inputStreamReader);
        line = "";
        line = bufferedReader.readLine();
        split = line.split(" ");
        quantidadeVertices = Integer.valueOf(split[0]);
        quantidadeLabels = Integer.valueOf(split[1]);

        while (line != null) {
            grafo = constroiGrafo(representacao, orientacao);
            for (int i = 0; i <= quantidadeVertices - 1; i++) {
                grafo.adicionaVertice(new Vertice(String.valueOf(i)));
            }
            for (int i = quantidadeVertices - 1; i > 0; i--) {
                line = bufferedReader.readLine();
                if (line.isEmpty()) {
                    break;
                }
                split = line.split(" ");
                verticeOrigem = grafo.getVertice(String.valueOf(i));
                for (int j = 0; j < i; j++) {
                    verticeDestino = grafo.getVertice(String.valueOf(j));
                    valorLabel = Integer.valueOf(split[j]);
                    if (valorLabel != quantidadeLabels) {
                        grafo.adicionaAresta(new ArestaPonderada(verticeOrigem, verticeDestino, valorLabel));
//                        grafo.adicionaAresta(new ArestaPonderada(verticeDestino, verticeOrigem, valorLabel));
                    }
                }
            }
            listaGrafos.add(grafo);
            line = bufferedReader.readLine();

        }

        return listaGrafos;
    }

}
