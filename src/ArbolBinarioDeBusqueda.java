import EstructurasDeDatos.Cola;
import EstructurasDeDatos.Pila;


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
    public Cola<T> getListaDatosNivel(int nivel){
        Cola<T> resultado = new Cola<>();
        ListaDatosNivel(this.inicial, nivel, resultado);
        return resultado;
    }
    public void ListaDatosNivel(NodoArbol<T> nodo, int Nivelactual, Cola<T> lista){
        // Si el nodo está vacío no se hace nada
        if(nodo == null){
            return;
        }
        // Si llegamos al nivel deseado, se añaden los números
        if(Nivelactual == 0){
            lista.enqueue(nodo.getNodo());
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

        Cola<NodoArbol<T>> cola = new Cola<>();
        cola.enqueue(this.inicial);

        boolean encontradoNulo = false;

        while (!cola.vacia()) {
            NodoArbol<T> actual = cola.dequeue();

            if (actual == null) {
                // A partir de aquí, no deberíamos ver más nodos reales
                encontradoNulo = true;
            } else {
                if (encontradoNulo) {
                    return false; // Hueco detectado
                }

                // Añadimos los hijos a la cola (incluyendo los null)
                // Primero el izquierdo y luego el derecho
                cola.enqueue(actual.getNodoIzq());
                cola.enqueue(actual.getNodoDer());
            }
        }

        return true;
    }
    public Pila<T> getCamino(T numero) {
        Pila<T> resultado = new Pila<>();
        // Es importante manejar el caso de que el árbol esté vacío
        if (this.inicial != null) {
            camino(this.inicial, numero, resultado);
        }
        return resultado;
    }

    public boolean camino(NodoArbol<T> nodo, T numero, Pila<T> pila) {
        if (nodo == null) {
            return false;
        }
        // 1. Agregamos el nodo actual al camino potencial
        pila.push(nodo.getNodo());

        // 2. Comparamos
        int cmp = numero.compareTo(nodo.getNodo());

        // CASO BASE: Lo encontramos
        if (cmp == 0) {
            return true;
        }
        // 3. Búsqueda recursiva basada en la propiedad del BST
        boolean encontrado = false;
        if (cmp < 0) {
            encontrado = camino(nodo.getNodoIzq(), numero, pila);
        } else {
            encontrado = camino(nodo.getNodoDer(), numero, pila);
        }
        // 4. BACKTRACKING: Si no se encontró en esta rama, lo sacamos de la pila
        if (!encontrado) {
            pila.pop();
        }
        return encontrado;
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
