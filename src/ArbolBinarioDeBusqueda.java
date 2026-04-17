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
    public int getGrado(){return Grado(this.inicial);}

    public int Grado(NodoArbol<T> nodo){
        if(nodo == null) return 0;
        int gradoActual = 0;
        if(nodo.getNodoIzq() != null) gradoActual++;
        if(nodo.getNodoDer() != null) gradoActual++;
        int gradoIzq = Grado(nodo.getNodoIzq());
        int gradoDer = Grado(nodo.getNodoDer());

        return Math.max(gradoActual, Math.max(gradoIzq, gradoDer));
    }
    public int getAltura(){return altura(this.inicial);}

    public int altura(NodoArbol<T> nodo) {
        if (nodo == null) {
            return 0;
        }

        int alturaIzq = altura(nodo.getNodoIzq());
        int alturaDer = altura(nodo.getNodoDer());

        return 1 + Math.max(alturaIzq, alturaDer);
    }

}
