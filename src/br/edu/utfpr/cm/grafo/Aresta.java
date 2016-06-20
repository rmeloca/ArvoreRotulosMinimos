package br.edu.utfpr.cm.grafo;

public class Aresta<U extends Vertice, V extends Vertice> {

    private U v1;
    private V v2;
    double peso;

    public Aresta(U v1, V v2) {
        if (!v1.getClass().equals(v2.getClass())) {
            throw new RuntimeException("Vértices precisam ser iguais");
        }
        this.v1 = v1;
        this.v2 = v2;
        this.peso = 1;
    }

    public Aresta(U v1, V v2, double peso) {
        this.v1 = v1;
        this.v2 = v2;
        this.peso = peso;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public U getVertice1() {
        return v1;
    }

    public void setVertice1(U v1) {
        this.v1 = v1;
    }

    public V getVertice2() {
        return v2;
    }

    public void setVertice2(V v2) {
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

    @Override
    public Object clone() {
        return new Aresta((U) getVertice1().clone(), (V) getVertice2().clone(), peso);
    }

}
