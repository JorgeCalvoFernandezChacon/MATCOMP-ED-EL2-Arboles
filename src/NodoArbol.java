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

    public void insertar(T nuevoDato) {
        if (nuevoDato.compareTo(this.nodo) < 0) {
            // Si es menor, va a la IZQUIERDA
            if (this.nodoIzq == null) {
                this.nodoIzq = new NodoArbol<>(nuevoDato);
            } else {
                this.nodoIzq.insertar(nuevoDato);
            }
        } else if (nuevoDato.compareTo(this.nodo) > 0) {
            // Si es mayor, va a la DERECHA
            if (this.nodoDer == null) {
                this.nodoDer = new NodoArbol<>(nuevoDato);
            } else {
                this.nodoDer.insertar(nuevoDato);
            }
        }
    }
}

