package br.edu.utfpr.cm.factory;

import br.edu.utfpr.cm.grafo.ArestaPonderada;
import br.edu.utfpr.cm.grafo.Grafo;
import br.edu.utfpr.cm.grafo.Vertice;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

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

}
