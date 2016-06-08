package br.edu.utfpr.cm.grafo;

public class Aresta<U extends Vertice, V extends Vertice> {

    private Vertice v1 = null;
    private Vertice v2 = null;

    public Aresta(U v1, V v2) {
        this.v1 = v1;
        this.v2 = v2;
    }

    public Vertice getVertice1() {
        return v1;
    }

    public void setVertice1(Vertice v1) {
        this.v1 = v1;
    }

    public Vertice getVertice2() {
        return v2;
    }

    public void setVertice2(Vertice v2) {
        this.v2 = v2;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Aresta)) {
            return false;
        }
        Aresta aresta = (Aresta) obj;
        if (!this.v1.equals(aresta.v1)) {
            return false;
        }
        if (!this.v2.equals(aresta.v2)) {
            return false;
        }
        return true;
    }

}
