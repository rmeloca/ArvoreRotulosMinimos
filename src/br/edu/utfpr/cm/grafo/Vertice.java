package br.edu.utfpr.cm.grafo;

import br.edu.utfpr.cm.algoritmo.entidades.VerticeBuscaProfundidade;

public abstract class Vertice {

    public String id;

    public Vertice() {
        this.id = String.valueOf(getClass().hashCode());
    }

    public Vertice(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Deprecated
    public VerticeBuscaProfundidade toVerticeBuscaProfundidade() {
        VerticeBuscaProfundidade vBuscaProfundidade = new VerticeBuscaProfundidade();
        vBuscaProfundidade.setId(id);

        return vBuscaProfundidade;
    }

    @Deprecated
    public Vertice toVertice() {
        if (this instanceof VerticeBuscaProfundidade) {
            Vertice v = null;
            return v;
        } else {
            return this;

        }
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Vertice other = (Vertice) obj;
        if (!this.id.equals(other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "< " + this.id + " >";
    }

    @Override
    public abstract Object clone();

}
