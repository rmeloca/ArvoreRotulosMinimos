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
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author romulo
 */
public class Aestrela implements Algoritmo {

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
     * Construtor.
     *
     * @param grafo
     */
    public Aestrela(Grafo grafo) {
        if (grafo == null) {
            throw new RuntimeException("Grafo não pode ser nulo!");
        }

        this.grafo = grafo;

        generatedNodes = new ArrayList<>();
        tracePath = new ArrayList<>();

        List<Label> unusedLabels = getUnusedLabelsList();

        //1. Put the root node r on OPEN.
        generatedNodes.add(new Node(null, unusedLabels, grafo.getQuantidadeVertices()));
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

    @Override
    public void executar() {
        throw new UnsupportedOperationException("Not supported.");
    }

    /**
     * Input: A graph G = (V, E) where each edge has a label in L and |V| = n,
     * |E| = m, and |L| = l. Output: A spanning tree with minimum number of tree
     * edge labels.
     *
     * @return
     */
    public List<ArestaPonderada> execute() {
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
            return minimumF.getAcyclicEdges();
        } catch (Exception ex) {
            Logger.getLogger(Aestrela.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
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
        for (Label unusedLabel : minimumF.getUnusedLabels()) {
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
