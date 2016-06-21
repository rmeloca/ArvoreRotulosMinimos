package br.edu.utfpr.cm.factory;

import br.edu.utfpr.cm.algoritmo.entidades.VerticeBuscaProfundidade;
import br.edu.utfpr.cm.grafo.Aresta;
import br.edu.utfpr.cm.grafo.Grafo;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
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

    public static List<Grafo<VerticeBuscaProfundidade, Aresta<VerticeBuscaProfundidade, VerticeBuscaProfundidade>>> lerGrafos(Representacao representacao, Orientacao orientacao, InputStream inputStream) throws IOException {
        List<Grafo<VerticeBuscaProfundidade, Aresta<VerticeBuscaProfundidade, VerticeBuscaProfundidade>>> listaGrafos;
        InputStreamReader inputStreamReader;
        BufferedReader bufferedReader;
        String line;
        String[] split;
        Grafo<VerticeBuscaProfundidade, Aresta<VerticeBuscaProfundidade, VerticeBuscaProfundidade>> grafo;
        int valorLabel;
        int quantidadeVertices;
        int quantidadeLabels;
        int idGrafo = 0;

        listaGrafos = new ArrayList<>();
        inputStreamReader = new InputStreamReader(inputStream);
        bufferedReader = new BufferedReader(inputStreamReader);
        line = "";
        line = bufferedReader.readLine();
        split = line.split(" ");
        quantidadeVertices = Integer.valueOf(split[0]);
        quantidadeLabels = Integer.valueOf(split[1]);

        while (line != null) {
            grafo = constroiGrafo(representacao, orientacao);
            for (int i = quantidadeVertices - 1; i > 0; i--) {
                line = bufferedReader.readLine();
                if (line == null) {
                    break;
                }
                if (line.isEmpty()) {
                    break;
                }
                split = line.split(" ");
                for (int j = 0; j < i; j++) {
                    valorLabel = Integer.valueOf(split[j]);
                    if (valorLabel != quantidadeLabels) {
                        grafo.adicionaAresta(new Aresta(new VerticeBuscaProfundidade(String.valueOf(i)), new VerticeBuscaProfundidade(String.valueOf(j)), valorLabel));
                    }
                }
            grafo.setId(idGrafo);
            }
            idGrafo++;
            if (grafo.getQuantidadeVertices() != 0) {
                if (grafo.getQuantidadeVertices() == quantidadeVertices) {
                    listaGrafos.add(grafo);
                }
            }
            line = bufferedReader.readLine();
        }

        return listaGrafos;
    }

}
