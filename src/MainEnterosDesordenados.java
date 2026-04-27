import EstructurasDeDatos.Cola;
import EstructurasDeDatos.Pila;

import java.util.Collections;
import java.util.Random;


public class MainEnterosDesordenados {
    public static void main(String[] args) {
        ArbolBinarioDeBusquedaEnteros arbol = new ArbolBinarioDeBusquedaEnteros(null);

        // 1. Crear lista de 0 a 128 y desordenarla
        Cola<Integer> cola = new Cola<>();
        Random r = new Random();
        for (int i = 0; i <= 128; i++) {
            cola.enqueue(i);
        } // Mezcla aleatoria
        Cola<Integer> mezcla = new Cola<>();

        while (!cola.vacia()) {

            int size = cola.tamañoLista();
            int pos = r.nextInt(size);

            Cola<Integer> temp = new Cola<>();
            Integer elegido = null;

            for (int i = 0; i < size; i++) {
                Integer x = cola.dequeue();

                if (i == pos) {
                    elegido = x;
                } else {
                    temp.enqueue(x);
                }
            }

            cola = temp;
            mezcla.enqueue(elegido);
        }

        // 2. Añadir al árbol
        while (!mezcla.vacia()) {
            arbol.add(mezcla.dequeue());
        }

        // 3. Calcular suma
        int sumaTotal = arbol.getSuma();
        System.out.println("1. Suma total (aleatoria): " + sumaTotal);
        // ¡Debe seguir siendo 8256! El orden no cambia la suma.

        // 4. Verificación de subárboles
        NodoArbol<Integer> raiz = arbol.getInicial();
        int sI = arbol.sumarNodos(raiz.getNodoIzq());
        int sD = arbol.sumarNodos(raiz.getNodoDer());
        int vR = raiz.getNodo();

        System.out.println("2. Raíz elegida aleatoriamente: " + vR);
        System.out.println("3. Suma Sub. Izquierdo: " + sI);
        System.out.println("4. Suma Sub. Derecho: " + sD);
        System.out.println("5. Verificación (R + SI + SD): " + (vR + sI + sD));

        // 5. Altura y Caminos
        System.out.println("6. Altura del árbol: " + arbol.getAltura());

        Pila<Integer> camino = arbol.getCamino(110);
        Pila<Integer> auxiliar = new Pila<>();
        Pila<Integer> auxiliar2 = new Pila<>();

// Pasamos todo de la pila 'camino' a 'auxiliar' (esto invierte el orden)
        while(!camino.vacia()){
            int X = camino.pop();
            auxiliar.push(X);
            auxiliar2.push(X);
        }
        System.out.print("7. Camino al 110 (Raíz a Destino): ");
        // 2. Imprimimos 'auxiliar'
        while(!auxiliar.vacia()){
            Integer actual = auxiliar.pop();
            System.out.print(actual);

            // Un toque estético: solo ponemos la flecha si no es el último
            if (!auxiliar.vacia()) {
                System.out.print(" -> ");
            }
        }
        System.out.println();
        System.out.println("8. Longitud del camino: " + (auxiliar2.tamañoLista() - 1));
    }
}
