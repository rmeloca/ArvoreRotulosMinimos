package br.edu.utfpr.cm.grafo;

public class Aresta<U extends Vertice, V extends Vertice> {
	private Vertice v1 = null;
	private Vertice v2 = null;
	
	public Aresta(V v1, V v2){
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
	
	
}
