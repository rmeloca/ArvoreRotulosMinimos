/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.cm.algoritmo;

import br.edu.utfpr.cm.algoritmo.entidades.Node;
import br.edu.utfpr.cm.algoritmo.entidades.Label;
import br.edu.utfpr.cm.grafo.ArestaPonderada;
import br.edu.utfpr.cm.grafo.Grafo;
import br.edu.utfpr.cm.grafo.Vertice;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author romulo
 */
public class ArvoreGeradoraRotulosMinimos implements Algoritmo {

    /**
     * OPEN is the storage place for all generated but unexpanded nodes.
     */
    private final List<Node> generatedNodes;

    /**
     * CLOSED is the storage place for the expanded nodes.
     */
    private final List<Node> tracePath;

    /**
     * Grafo sobre o qual opera-se o algoritmo.
     */
    private final Grafo grafo;

    /**
     * Lista de rótulos não utilizados no passo atual.
     */
    private final List<Label> unusedLabels;

    /**
     * Construtor.
     *
     * @param grafo
     */
    public ArvoreGeradoraRotulosMinimos(Grafo grafo) {
        this.grafo = grafo;

        unusedLabels = getUnusedLabelsList();

        generatedNodes = new ArrayList<>();
        tracePath = new ArrayList<>();

        //1. Put the root node r on OPEN.
        generatedNodes.add(new Node());
    }

    /**
     * Obtém a lista de rótulos utilizados. Provê a separação entre as arestas
     * que utilizam determinados rótulos.
     *
     * @return
     */
    private List<Label> getUnusedLabelsList() {
        List<Label> unusedLabels;
        HashMap<String, List<ArestaPonderada>> labelHasEdgesCovered;
        ArestaPonderada next;
        Iterator<ArestaPonderada> arestas;

        unusedLabels = new ArrayList<>();
        labelHasEdgesCovered = new HashMap<>();

        arestas = grafo.getArestas();
        while (arestas.hasNext()) {
            next = arestas.next();
            if (!labelHasEdgesCovered.containsKey(String.valueOf(next.getPeso()))) {
                labelHasEdgesCovered.put(String.valueOf(next.getPeso()), new ArrayList<>());
            }
            labelHasEdgesCovered.get(String.valueOf(next.getPeso())).add(next);
        }

        for (Map.Entry<String, List<ArestaPonderada>> entry : labelHasEdgesCovered.entrySet()) {
            unusedLabels.add(new Label(entry.getKey(), entry.getValue()));
        }

        Collections.sort(unusedLabels);
        return unusedLabels;
    }

    /**
     * Input: A graph G = (V, E) where each edge has a label in L and |V| = n,
     * |E| = m, and |L| = l. Output: A spanning tree with minimum number of tree
     * edge labels.
     */
    @Override
    public void executar() {
        Node minimumF;
        try {
            while (true) {
                //2. If OPEN is empty, exit with failure.
                if (generatedNodes.isEmpty()) {
                    //Since a solution always exists, this will never be executed.
                    throw new Exception("Falha");
                }
                //3. Remove from OPEN and place on CLOSED a node n for which f is minimum.
                minimumF = getMinimumF();
                generatedNodes.remove(minimumF);
                tracePath.add(minimumF);
                //4. If n is a goal node (a spanning subgraph is formed), goto Step 8.
                if (isGoalNode(minimumF)) {
                    break;
                } else {
                    //5. Otherwise expand n
                    for (Node son : expandNode(minimumF)) {
                        //6. For each child son of n
                        //If n1 is not already on OPEN or CLOSE
                        if (!generatedNodes.contains(son) && !tracePath.contains(son)) {
                            //calculate f(son) = g(son) + h(son)
                            //where g(son) = g(n) + 1 and g(r) = 0.
                            son.calculateF();
                            //Put son into OPEN.
                            generatedNodes.add(son);
                        }
                    }
                }
            }
            //8. Find a spanning tree of the subgraph.
        } catch (Exception ex) {
            Logger.getLogger(ArvoreGeradoraRotulosMinimos.class.getName()).log(Level.SEVERE, null, ex);
        }
        throw new UnsupportedOperationException();
    }

    /**
     * Obtém o nó cuja função heurística é mínima. If there are more than one
     * node with the same minimum f value, the latest generated node will be
     * selected.
     *
     * @return
     */
    private Node getMinimumF() {
        if (generatedNodes == null) {
            return null;
        }
        if (generatedNodes.isEmpty()) {
            return null;
        }
        Node minimumF = generatedNodes.get(generatedNodes.size() - 1);
        for (Node generatedNode : generatedNodes) {
            if (generatedNode.getF() < minimumF.getF()) {
                minimumF = generatedNode;
            }
        }
        return minimumF;
    }

    /**
     * Obtém o número de arestas que um rótulo cobre.
     *
     * @param id
     * @return
     * @deprecated unusedLabelList already provides the number.
     */
    @Deprecated
    private int getNumberOfEdgesCovered(String id) {
        int numberOfEdgesCovered = 0;
        Iterator<ArestaPonderada> arestas = grafo.getArestas();
        while (arestas.hasNext()) {
            ArestaPonderada next = arestas.next();
            if (id.equals(next.getPeso())) {
                numberOfEdgesCovered++;
            }
        }
        return numberOfEdgesCovered;
    }

    /**
     * Expande um nó. Combinação dos labels utilizados no nó recebido com todos
     * os labels ainda não utilizados.
     *
     * @param minimumF
     * @return
     */
    private List<Node> expandNode(Node minimumF) {
        //If there are k unselected labels, then n has k children, one for each unselected label.
        Node clone;
        List<Node> expandedNodes = new ArrayList<>();
        for (Label unusedLabel : unusedLabels) {
            clone = (Node) minimumF.clone();
            clone.addLabel(unusedLabel);
            expandedNodes.add(clone);
        }
        return expandedNodes;
    }

    /**
     * Apenas uma componente é formada.
     *
     * @param minimumF
     * @return
     */
    private boolean isGoalNode(Node minimumF) {
        return minimumF.getVerticesCovered().size() == grafo.getQuantidadeVertices();
    }

}
