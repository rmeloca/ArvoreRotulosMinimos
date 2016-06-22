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
import java.util.ArrayList;
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
        long tempoInicial;
        long tempoTotal;
        double qtdGrafosExec;
        double qtdLabels;
        InputStream arquivoStream;
        List<Grafo<VerticeBuscaProfundidade, Aresta<VerticeBuscaProfundidade, VerticeBuscaProfundidade>>> lista;
        List<Aresta> resultado;
        List<String> arquivosTeste;
        arquivosTeste = new ArrayList<>();
        try {
//            arquivosTeste.add("../instancias/group_1/HDGraph20_20.txt");
//            arquivosTeste.add("../instancias/group_1/MDGraph20_20.txt");
//            arquivosTeste.add("../instancias/group_1/LDGraph20_20.txt");
//            arquivosTeste.add("../instancias/group_1/HDGraph30_30.txt");
//            arquivosTeste.add("../instancias/group_1/MDGraph30_30.txt");
//            arquivosTeste.add("../instancias/group_1/LDGraph30_30.txt");
//            arquivosTeste.add("../instancias/group_1/HDGraph40_40.txt");
//            arquivosTeste.add("../instancias/group_1/MDGraph40_40.txt");
//            arquivosTeste.add("../instancias/group_1/LDGraph40_40.txt");
//            arquivosTeste.add("../instancias/group_1/HDGraph50_50.txt");
//            arquivosTeste.add("../instancias/group_1/MDGraph50_50.txt");
//            arquivosTeste.add("../instancias/group_1/LDGraph50_50.txt");
//            
//            arquivosTeste.add("../instancias/group_2_with_n=100/HDGraph100_25.txt");
//            arquivosTeste.add("../instancias/group_2_with_n=100/MDGraph100_25.txt");
//            arquivosTeste.add("../instancias/group_2_with_n=100/LDGraph100_25.txt");
            arquivosTeste.add("../instancias/group_2_with_n=100/HDGraph100_50.txt");
//            arquivosTeste.add("../instancias/group_2_with_n=100/MDGraph100_50.txt");
//            arquivosTeste.add("../instancias/group_2_with_n=100/LDGraph100_50.txt");
//            arquivosTeste.add("../instancias/group_2_with_n=100/HDGraph100_100.txt");
//            arquivosTeste.add("../instancias/group_2_with_n=100/MDGraph100_100.txt");
//            arquivosTeste.add("../instancias/group_2_with_n=100/LDGraph100_100.txt");
//            arquivosTeste.add("../instancias/group_2_with_n=100/HDGraph100_125.txt");
//            arquivosTeste.add("../instancias/group_2_with_n=100/MDGraph100_125.txt");
//            arquivosTeste.add("../instancias/group_2_with_n=100/LDGraph100_125.txt");
//            arquivosTeste.add("../instancias/group_2_with_n=500/HDGraph500_625.txt");
            for (String arquivoNome : arquivosTeste) {
                System.out.println("@@@ " + arquivoNome + " @@@");
                arquivoStream = (getClass().getResourceAsStream(arquivoNome));
                lista = GrafoFactory.lerGrafos(Representacao.MATRIZ_ADJACENCIA, Orientacao.NAO_DIRIGIDO, arquivoStream);
                tempoInicial = System.currentTimeMillis();
                qtdGrafosExec = 0;
                qtdLabels = 0;
                for (Grafo<VerticeBuscaProfundidade, Aresta<VerticeBuscaProfundidade, VerticeBuscaProfundidade>> grafo : lista) {
                    this.aestrela = new Aestrela(grafo);
                    this.aestrela.executar();
                    qtdGrafosExec++;
                    qtdLabels += aestrela.getGoal().getSelectedLabels().size();
//                    resultado = this.aestrela.getSpanningTree();
//                    System.out.println("*** Grafo: " + grafo.getId() + " ***");
//                    for (Aresta aresta : resultado) {
//                        System.out.println(aresta.getVertice1() + " --" + aresta.getPeso() + "-- " + aresta.getVertice2());
//                    }
                }
                tempoTotal = System.currentTimeMillis() - tempoInicial;
                System.out.println("Tempo de execução de " + qtdGrafosExec + " do grupo é de: " + tempoTotal);
                System.out.println("Media de: " + (tempoTotal/qtdGrafosExec));
                System.out.println("Qtdade de labels"+qtdLabels/qtdGrafosExec);
            }
        } catch (IOException ex) {
            Logger.getLogger(AestrelaTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
