public class Main {
    public static void main(String[] args) {
        // 1. Creamos el árbol y añadimos elementos
        // Vamos a crear un árbol que sea "Casi Completo" pero no "Completo (Perfecto)"
        // Estructura:
        //        10
        //      /    \
        //     5      15
        //    / \    /
        //   2   7  12

        ArbolBinarioDeBusqueda<Integer> arbol = new ArbolBinarioDeBusqueda<>(null);
        arbol.add(10);
        arbol.add(5);
        arbol.add(15);
        arbol.add(2);
        arbol.add(7);
        arbol.add(12);

        System.out.println("--- RECORRIDOS ---");
        System.out.print("Orden Central (Debería salir ordenado): ");
        arbol.getListaOrdenCentral();
        System.out.println();

        System.out.println("\n--- PROPIEDADES ---");
        System.out.println("Grado del árbol (debe ser 2): " + arbol.getGrado());
        System.out.println("Altura del árbol (debe ser 3): " + arbol.getAltura());

        System.out.println("\n--- COMPROBACIONES DE FORMA ---");
        // No es homogéneo porque el nodo 15 solo tiene 1 hijo (el 12)
        System.out.println("¿Es Homogéneo?: " + arbol.isArbolHomogeneo());

        // No es completo según tu definición (hojas a misma altura) porque 12 está en nivel 2 y 2,7 en nivel 2,
        // pero 15 no tiene hijo derecho.
        System.out.println("¿Es Completo (hojas misma profundidad)?: " + arbol.isArbolCompleto());

        // Es casi completo porque las hojas están en los niveles 2 y están pegadas a la izquierda
        System.out.println("¿Es Casi Completo?: " + arbol.isArbolCasiCompleto());

        System.out.println("\n--- NIVELES Y CAMINOS ---");
        System.out.println("Datos en el nivel 2 (deben ser 2, 7, 12): " + arbol.getListaDatosNivel(2));

        System.out.println("Camino hasta el número 7: " + arbol.getCamino(7));
        System.out.println("Camino hasta el número 12: " + arbol.getCamino(12));

        System.out.println("\n--- SUBÁRBOLES ---");
        ArbolBinarioDeBusqueda<Integer> subIzq = arbol.getSubArbolIzquierda();
        if (subIzq != null) {
            System.out.print("Orden Central del Subárbol Izquierdo (raíz 5): ");
            subIzq.getListaOrdenCentral();
            System.out.println();
        }

        ArbolBinarioDeBusqueda<Integer> subDer = arbol.getSubArbolDerecha();
        if (subDer != null) {
            System.out.print("Orden Central del Subárbol Derecho (raíz 15): ");
            subDer.getListaOrdenCentral();
            System.out.println();
        }
    }
}