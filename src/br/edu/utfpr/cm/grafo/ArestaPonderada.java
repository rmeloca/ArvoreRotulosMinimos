package br.edu.utfpr.cm.grafo;

public final class ArestaPonderada extends Aresta<Vertice, Vertice> {

    double peso = Double.POSITIVE_INFINITY;

    public ArestaPonderada(Vertice v1, Vertice v2, double peso) {
        super(v1, v2);
        setPeso(peso);
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    @Override
    public Object clone() {
        return new ArestaPonderada((Vertice) getVertice1().clone(), (Vertice) getVertice2().clone(), peso);
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
