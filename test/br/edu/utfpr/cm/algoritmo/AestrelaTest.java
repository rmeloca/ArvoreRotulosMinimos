/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.cm.algoritmo;

import br.edu.utfpr.cm.factory.GrafoFactory;
import br.edu.utfpr.cm.factory.Orientacao;
import br.edu.utfpr.cm.factory.Representacao;
import br.edu.utfpr.cm.grafo.ArestaPonderada;
import br.edu.utfpr.cm.grafo.Grafo;
import br.edu.utfpr.cm.grafo.Vertice;
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
        List<Grafo> lista;
        try {
            inputStream = (getClass().getResourceAsStream("../instancias/group_1/HDGraph20_20.txt"));
            lista = GrafoFactory.lerGrafos(Representacao.MATRIZ_ADJACENCIA, Orientacao.DIRIGIDO, inputStream);
            Grafo grafo = lista.get(0);

            this.aestrela = new Aestrela(grafo);
            this.aestrela.execute();
        } catch (IOException ex) {
            Logger.getLogger(AestrelaTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
