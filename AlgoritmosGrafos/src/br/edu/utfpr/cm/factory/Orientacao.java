package br.edu.utfpr.cm.factory;

public enum Orientacao {
    DIRIGIDO(0),
    NAO_DIRIGIDO(1);

    private int orientacao;

    Orientacao(int orientacao) {
        this.setOrientacao(orientacao);
    }

    public int getOrientacao() {
        return orientacao;
    }

    public void setOrientacao(int orientacao) {
        this.orientacao = orientacao;
    }
}
