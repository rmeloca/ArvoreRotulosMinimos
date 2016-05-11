package br.edu.utfpr.cm.grafo;

public class ArestaPonderada extends Aresta<Vertice,Vertice> {
	double peso = Double.POSITIVE_INFINITY;
	
	public ArestaPonderada(Vertice v1, Vertice v2, double peso) {
		super(v1, v2);
	}
	
	public double getPeso(){
		return peso;
	}
	
	public void setPeso(double peso){
		this.peso = peso;
	}
}
