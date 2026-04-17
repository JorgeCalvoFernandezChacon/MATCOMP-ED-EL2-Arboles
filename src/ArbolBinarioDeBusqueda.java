public class ArbolBinarioDeBusqueda<T extends Comparable<T>> {
    private NodoArbol<T> inicial = null;

    public void insertar(T elemento) {
        if (this.inicial == null) {
            this.inicial = new NodoArbol(elemento);
        } else {
            this.inicial.insertar(elemento);
        }

    }

    public void getListaPreOrden() {
        this.preorden(this.inicial);
    }

    public void preorden(NodoArbol<T> nodo) {
        if (nodo != null) {
            System.out.print(String.valueOf(nodo.getNodo()) + ",");
            this.preorden(nodo.getNodoDer());
            this.preorden(nodo.getNodoIzq());
        }

    }

    public void getListaPostOrden() {
        this.postorden(this.inicial);
    }

    public void postorden(NodoArbol<T> nodo) {
        if (nodo != null) {
            this.postorden(nodo.getNodoIzq());
            this.postorden(nodo.getNodoDer());
            System.out.print(String.valueOf(nodo.getNodo()) + ",");
        }

    }

    public void getListaOrdenCentral() {
        this.ordencentral(this.inicial);
    }

    public void ordencentral(NodoArbol<T> nodo) {
        if (nodo != null) {
            this.ordencentral(nodo.getNodoIzq());
            System.out.print(String.valueOf(nodo.getNodo()) + ",");
            this.ordencentral(nodo.getNodoDer());
        }

    }
}
