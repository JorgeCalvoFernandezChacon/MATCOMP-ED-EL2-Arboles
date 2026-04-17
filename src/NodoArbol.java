public class NodoArbol<T extends Comparable<T>> {
    private T nodo;
    private NodoArbol<T> nodoIzq;
    private NodoArbol<T> nodoDer;

    public NodoArbol(T nodo) {
        this.nodo = nodo;
        this.nodoDer = null;
        this.nodoIzq = null;
    }

    public NodoArbol<T> getNodoDer() {
        return this.nodoDer;
    }

    public NodoArbol<T> getNodoIzq() {
        return this.nodoIzq;
    }

    public T getNodo() {
        return this.nodo;
    }

    public void setNodo(T nodo) {
        this.nodo = nodo;
    }

    public void insertar(T nuevoNodo) {
        if (nuevoNodo.compareTo(this.nodo) < 0) {
            if (this.nodoIzq == null) {
                this.nodoIzq = new NodoArbol<T>(nuevoNodo);
            } else {
                this.nodoIzq.insertar(nuevoNodo);
            }
        }

        if (nuevoNodo.compareTo(this.nodo) > 0) {
            if (this.nodoDer == null) {
                this.nodoDer = new NodoArbol<T>(nuevoNodo);
            } else {
                this.nodoDer.insertar(nuevoNodo);
            }
        }

    }
}

