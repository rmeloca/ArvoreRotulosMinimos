package br.edu.utfpr.cm.algoritmo.entidades;

import br.edu.utfpr.cm.grafo.Vertice;

public class VerticeBuscaProfundidade extends Vertice {

    private Cor cor = Cor.Branco;
    private int tempoDescoberta = (int) Float.POSITIVE_INFINITY;
    private int tempoFinalizacao = (int) Float.POSITIVE_INFINITY;
    private VerticeBuscaProfundidade pai = null;

    public VerticeBuscaProfundidade() {
    }

    public VerticeBuscaProfundidade(String id) {
        setId(id);
    }

    public Cor getCor() {
        return cor;
    }

    public void setCor(Cor cor) {
        this.cor = cor;
    }

    public int getTempoDescoberta() {
        return tempoDescoberta;
    }

    public void setTempoDescoberta(int tempoDescoberta) {
        this.tempoDescoberta = tempoDescoberta;
    }

    public int getTempoFinalizacao() {
        return tempoFinalizacao;
    }

    public void setTempoFinalizacao(int tempoFinalizacao) {
        this.tempoFinalizacao = tempoFinalizacao;
    }

    public VerticeBuscaProfundidade getPai() {
        return pai;
    }

    public void setPai(VerticeBuscaProfundidade pai) {
        this.pai = pai;
    }

    @Override
    public String toString() {
        return this.getId();
    }

    @Override
    public Object clone() {
        VerticeBuscaProfundidade verticeBuscaProfundidade = new VerticeBuscaProfundidade();
        verticeBuscaProfundidade.setCor(this.cor);
        verticeBuscaProfundidade.setId(this.id);
        if (this.pai != null) {
            verticeBuscaProfundidade.setPai((VerticeBuscaProfundidade) this.pai.clone());
        }
        verticeBuscaProfundidade.setTempoDescoberta(this.tempoDescoberta);
        verticeBuscaProfundidade.setTempoFinalizacao(this.tempoFinalizacao);
        return verticeBuscaProfundidade;
    }
}
