import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainEnterosDesordenados {
    public static void main(String[] args) {
        ArbolBinarioDeBusquedaEnteros arbol = new ArbolBinarioDeBusquedaEnteros(null);

        // 1. Crear lista de 0 a 128 y desordenarla
        List<Integer> numeros = new ArrayList<>();
        for (int i = 0; i <= 128; i++) {
            numeros.add(i);
        }
        Collections.shuffle(numeros); // Mezcla aleatoria

        // 2. Añadir al árbol
        for (Integer n : numeros) {
            arbol.add(n);
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

        List<Integer> camino = arbol.getCamino(110);
        System.out.println("7. Camino al 110: " + camino);
        System.out.println("8. Longitud del camino: " + (camino.size() - 1));
    }
}
