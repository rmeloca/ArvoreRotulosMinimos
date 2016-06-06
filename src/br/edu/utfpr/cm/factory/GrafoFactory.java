package br.edu.utfpr.cm.factory;

import br.edu.utfpr.cm.grafo.Grafo;

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
