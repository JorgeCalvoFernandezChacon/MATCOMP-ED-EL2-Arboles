public class ArbolBinarioDeBusquedaEnteros extends ArbolBinarioDeBusqueda<Integer> {

    // Necesitas un constructor que llame al del padre (super)
    public ArbolBinarioDeBusquedaEnteros(NodoArbol<Integer> nodo) {
        super(nodo);
    }

    // Método para sumar todos los valores del árbol
    public int getSuma() {
        // Usamos un método auxiliar para la recursividad
        // Pasamos la raíz (inicial), que es accesible si es protected o mediante un getter
        return sumarNodos(this.inicial);
    }

    public int sumarNodos(NodoArbol<Integer> nodo) {
        // Caso base: si el nodo es nulo, la suma es 0
        if (nodo == null) {
            return 0;
        }

        // Suma = Valor actual + suma de la izquierda + suma de la derecha
        return nodo.getNodo() + sumarNodos(nodo.getNodoIzq()) + sumarNodos(nodo.getNodoDer());
    }
}
