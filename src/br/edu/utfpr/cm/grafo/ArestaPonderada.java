package br.edu.utfpr.cm.grafo;

public final class ArestaPonderada<U extends Vertice, V extends Vertice> extends Aresta<U, V> {

    double peso = Double.POSITIVE_INFINITY;

    public ArestaPonderada(U v1, V v2, double peso) {
        super(v1, v2);
        setPeso(peso);
    }

    public ArestaPonderada(U v1, V v2) {
        super(v1,v2);
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    @Override
    public Object clone() {
        return new ArestaPonderada((U) getVertice1().clone(), (V) getVertice2().clone(), peso);
    }

    @Override
    public boolean equals(Object obj) {
        if (!super.equals(obj)) {
            return false;
        }
        if (obj instanceof ArestaPonderada) {
            ArestaPonderada arestaPonderada = (ArestaPonderada) obj;
            return this.peso == arestaPonderada.peso;
        }
        return false;
    }
}
