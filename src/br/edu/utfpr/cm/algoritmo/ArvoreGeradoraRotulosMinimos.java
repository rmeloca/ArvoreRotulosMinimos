/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.cm.algoritmo;

import br.edu.utfpr.cm.grafo.Vertice;
import java.util.ArrayList;
import java.util.List;
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
    private List<Vertice> open;

    /**
     * CLOSED is the storage place for the expanded nodes.
     */
    private List<Vertice> closed;

    private Vertice root;

    public ArvoreGeradoraRotulosMinimos() {
        open = new ArrayList<>();
        closed = new ArrayList<>();

        //1. Put the root node r on OPEN.
        open.add(root);
    }

    public Vertice heuristica() {
        //If there are more than one node with the same minimum f value, the latest generated node will be selected.
        throw new UnsupportedOperationException();
    }

    //Input: A graph G = (V, E) where each edge has a label in L and |V| = n, |E| = m, and |L| = l.
    //Output: A spanning tree with minimum number of tree edge labels.
    @Override
    public void executar() {
        Vertice minimumF;
        try {
            while (true) {
                //2. If OPEN is empty, exit with failure.
                if (open.isEmpty()) {
                    //Since a solution always exists, this will never be executed.
                    throw new Exception("Falha");
                }
                //3. Remove from OPEN and place on CLOSED a node n for which f is minimum.
                minimumF = heuristica();
                open.remove(minimumF);
                closed.add(minimumF);
                //4. If n is a goal node (a spanning subgraph is formed), goto Step 8.
                if (minimumF != null) {
                    return;
                } else {
                    //5. Otherwise expand n.
                    //If there are k unselected labels, then n has k children, one for each unselected label.
                    for (Vertice son : open) {
                        //6. For each child son of n
                        //If n1 is not already on OPEN or CLOSE
                        if (!open.contains(son) && !closed.contains(son)) {
                            //calculate f(son) = g(son) + h(son)
                            //where g(son) = g(n) + 1 and g(r) = 0.
                            //Put n1 into OPEN.
                            open.add(son);
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
    
    

}
