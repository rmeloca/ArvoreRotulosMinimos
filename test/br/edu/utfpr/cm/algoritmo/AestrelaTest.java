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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Test;

/**
 *
 * @author romulo
 */
public class AestrelaTest {

    private Aestrela aestrela;

    @Test
    public void testarAEstrela() {

        InputStream inputStream;
        List<Grafo<VerticeBuscaProfundidade,Aresta<VerticeBuscaProfundidade,VerticeBuscaProfundidade>>> lista;
        try {
            inputStream = (getClass().getResourceAsStream("../instancias/group_1/HDGraph20_20.txt"));
            lista = GrafoFactory.lerGrafos(Representacao.MATRIZ_ADJACENCIA, Orientacao.NAO_DIRIGIDO, inputStream);
            Grafo grafo = lista.get(0);

            this.aestrela = new Aestrela(grafo);
            List<Aresta<VerticeBuscaProfundidade, VerticeBuscaProfundidade>> resultado;
            resultado = this.aestrela.execute();
            
            System.out.println("aa");
        } catch (IOException ex) {
            Logger.getLogger(AestrelaTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
