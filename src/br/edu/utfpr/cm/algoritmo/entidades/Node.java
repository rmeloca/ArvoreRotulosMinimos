/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.cm.algoritmo.entidades;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author romulo
 */
public class Node {

    private List<Label> selectedLabels;

    public Node() {
        this.selectedLabels = new ArrayList<>();
    }

    public int getF() {
        throw new UnsupportedOperationException();
    }

    private int getG() {
        throw new UnsupportedOperationException();
    }

    private int getH() {
        throw new UnsupportedOperationException();
    }
}
