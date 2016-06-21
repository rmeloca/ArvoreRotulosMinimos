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
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;
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
        List<Grafo<VerticeBuscaProfundidade, Aresta<VerticeBuscaProfundidade, VerticeBuscaProfundidade>>> lista;
        try {
            List<String> arquivosTeste = new ArrayList<>();
            arquivosTeste.add("../instancias/group_1/");
            arquivosTeste.add("../instancias/group_1/LDGraph20_20.txt");
            arquivosTeste.add("../instancias/group_1/LDGraph20_20.txt");
            arquivosTeste.add("../instancias/group_1/MDGraph20_20.txt");
            arquivosTeste.add("../instancias/group_1/MDGraph20_20.txt");
            arquivosTeste.add("../instancias/group_1/MDGraph20_20.txt");
            inputStream = (getClass().getResourceAsStream("../instancias/"));

            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            Stream<String> lines = bufferedReader.lines();
            Iterator<String> iterator = lines.iterator();
            while (iterator.hasNext()) {
                String next = iterator.next();
                System.out.println(next);
                inputStream = (getClass().getResourceAsStream("../instancias/"));
            }

//            inputStream = (getClass().getResourceAsStream("../instancias/group_2_with_n=500/LDGraph500_625.txt"));
//            inputStream = (getClass().getResourceAsStream("../instancias/group_1/MDGraph20_20.txt"));
            lista = GrafoFactory.lerGrafos(Representacao.MATRIZ_ADJACENCIA, Orientacao.NAO_DIRIGIDO, inputStream);

            for (Grafo<VerticeBuscaProfundidade, Aresta<VerticeBuscaProfundidade, VerticeBuscaProfundidade>> grafo : lista) {
                this.aestrela = new Aestrela(grafo);
                List<Aresta> resultado;
                resultado = this.aestrela.execute();
                System.out.println("*******Grafo n:" + grafo.getId() + "****");
                for (Aresta aresta : resultado) {
                    System.out.println(aresta.getVertice1() + " --" + aresta.getPeso() + "-- " + aresta.getVertice2());
                }
                System.out.println("*********");
            }
        } catch (IOException ex) {
            Logger.getLogger(AestrelaTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
