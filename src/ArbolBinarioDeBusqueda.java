import java.util.ArrayList;
import java.util.List;
import java.util.LinkedList;
import java.util.Queue;

public class ArbolBinarioDeBusqueda<T extends Comparable<T>> {
    protected NodoArbol<T> inicial = null;

    public ArbolBinarioDeBusqueda(){}

    public ArbolBinarioDeBusqueda(NodoArbol<T> nodo){
        this.inicial = nodo;
    }

    public NodoArbol<T> getInicial() {
        return this.inicial;
    }

    public void add(T elemento) {
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
    public List<T> getListaDatosNivel(int nivel){
        List<T> resultado = new ArrayList<>();
        ListaDatosNivel(this.inicial, nivel, resultado);
        return resultado;
    }
    public void ListaDatosNivel(NodoArbol<T> nodo, int Nivelactual, List<T> lista){
        // Si el nodo está vacío no se hace nada
        if(nodo == null){
            return;
        }
        // Si llegamos al nivel deseado, se añaden los números
        if(Nivelactual == 0){
            lista.add(nodo.getNodo());
        // Si no, seguimos recorriendo ambas ramas de los nodos hasta llegar al nivel pedido
        }else{
            ListaDatosNivel(nodo.getNodoIzq(),Nivelactual -1, lista);
            ListaDatosNivel(nodo.getNodoDer(),Nivelactual -1, lista);
        }
    }
    public boolean isArbolHomogeneo(){
        if(this.inicial == null ){return true;}
        int gradoArbol = this.getGrado();
        return esHomogeneo(this.inicial, gradoArbol);
    }
    public boolean esHomogeneo(NodoArbol<T> nodo, int gradoObjetivo) {
        // Caso base hoja
        if (nodo.getNodoIzq() == null && nodo.getNodoDer() == null) {
            return true;
        }

        // Contamos los hijos del nodo actual
        int hijosActual = 0;
        if (nodo.getNodoIzq() != null) hijosActual++;
        if (nodo.getNodoDer() != null) hijosActual++;

        // Si tiene hijos pero no son los mismos que el grado del árbol, no es homogéneo
        if (hijosActual != gradoObjetivo) {
            return false;
        }

        // Si el nodo cumple, comprobamos recursivamente sus hijos
        boolean izqHomogeneo = (nodo.getNodoIzq() == null) || esHomogeneo(nodo.getNodoIzq(), gradoObjetivo);
        boolean derHomogeneo = (nodo.getNodoDer() == null) || esHomogeneo(nodo.getNodoDer(), gradoObjetivo);

        return izqHomogeneo && derHomogeneo;
    }
    public boolean isArbolCompleto(){
        if(this.inicial == null){return true;}
        int[] profundidad = {-1};
        return sonHojas(this.inicial, 0, profundidad);
    }

    public boolean sonHojas(NodoArbol<T> nodo, int profundidadActual, int[] profundidad) {
        if (nodo == null) return true;

        // ¿Es una hoja?
        if (nodo.getNodoIzq() == null && nodo.getNodoDer() == null) {
            // Si es la primera hoja que vemos, guardamos su profundidad
            if (profundidad[0] == -1) {
                profundidad[0] = profundidadActual;
                return true;
            }
            // Si no es la primera, comparamos con la primera
            return (profundidadActual == profundidad[0]);
        }

        // Si no es hoja, seguimos bajando
        return sonHojas(nodo.getNodoIzq(), profundidadActual + 1, profundidad) &&
                sonHojas(nodo.getNodoDer(), profundidadActual + 1, profundidad);
    }
    public boolean isArbolCasiCompleto() {
        if (this.inicial == null) {
            return true; // Un árbol vacío se considera casi completo
        }

        Queue<NodoArbol<T>> cola = new LinkedList<>();
        cola.add(this.inicial);

        boolean encontradoNulo = false;

        while (!cola.isEmpty()) {
            NodoArbol<T> actual = cola.poll();

            if (actual == null) {
                // A partir de aquí, no deberíamos ver más nodos reales
                encontradoNulo = true;
            } else {
                if (encontradoNulo) {
                    return false; // Hueco detectado
                }

                // Añadimos los hijos a la cola (incluyendo los null)
                // Primero el izquierdo y luego el derecho
                cola.add(actual.getNodoIzq());
                cola.add(actual.getNodoDer());
            }
        }

        return true;
    }
    public List<T> getCamino(T numero){
        List<T> resultado = new ArrayList<>();
        camino(this.inicial,numero, resultado);
        return resultado;
    }
    public void camino(NodoArbol<T> nodo, T numero, List<T> lista){
        // Caso base
        if (nodo == null) {
            return;
        }
        // Añadimos el nodo actual al camino
        lista.add(nodo.getNodo());
        // Comparamos
        int comparacion = numero.compareTo(nodo.getNodo());
        if (comparacion == 0) {
            return;
        } else if (comparacion < 0) {
            // El número buscado es MENOR, vamos a la IZQUIERDA
            camino(nodo.getNodoIzq(), numero, lista);
        } else {
            // El número buscado es MAYOR, vamos a la DERECHA
            camino(nodo.getNodoDer(), numero, lista);
        }
    }
    public ArbolBinarioDeBusqueda<T> getSubArbolIzquierda(){
        return SubArbolIzquierda(this.inicial);
    }

    public ArbolBinarioDeBusqueda<T> SubArbolIzquierda(NodoArbol<T> nodo){
        // Si el árbol está vacío o si no tiene rama izquierda
        if(nodo == null || nodo.getNodoIzq() == null ) return null;
        // Creamos un nuevo árbol cuya raíz sea el hijo izquierdo del nodo recibido
        return new ArbolBinarioDeBusqueda<>(nodo.getNodoIzq());
    }
    public ArbolBinarioDeBusqueda<T> getSubArbolDerecha(){
        return SubArbolDerecha(this.inicial);
    }

    public ArbolBinarioDeBusqueda<T> SubArbolDerecha(NodoArbol<T> nodo){
        // Si el árbol está vacío o si no tiene rama derecha
        if(nodo == null || nodo.getNodoDer() == null ) return null;
        // Creamos un nuevo árbol cuya raíz sea el hijo izquierdo del nodo recibido
        return new ArbolBinarioDeBusqueda<>(nodo.getNodoDer());
    }
}
