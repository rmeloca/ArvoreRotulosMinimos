/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.cm.algoritmo;

import br.edu.utfpr.cm.factory.GrafoFactory;
import br.edu.utfpr.cm.factory.Orientacao;
import br.edu.utfpr.cm.factory.Representacao;
import br.edu.utfpr.cm.grafo.Grafo;
import org.junit.Test;

/**
 *
 * @author romulo
 */
public class AestrelaTest {

    private Aestrela aestrela;

    @Test
    public void testarAEstrela() {
        Grafo grafo = GrafoFactory.constroiGrafo(Representacao.LISTA_ADJACENCIA, Orientacao.NAO_DIRIGIDO);
        this.aestrela = new Aestrela(grafo);
        this.aestrela.executar();
    }

}
