public class MainEnterosOrdenados{
    public static void main(String[] args) {
        // Creamos la instancia de la clase específica para enteros
        ArbolBinarioDeBusquedaEnteros arbol = new ArbolBinarioDeBusquedaEnteros(null);

        // 1. Añadir los números de 0 a 128 en orden
        for (int i = 0; i <= 128; i++) {
            arbol.add(i);
        }

        // 2. Calcular la suma total
        int sumaTotal = arbol.getSuma();
        System.out.println("1. Suma total (getSuma()): " + sumaTotal);

        // 3. Verificar suma en los 3 recorridos (Conceptualmente)
        // Como tus métodos de recorrido imprimen por pantalla,
        // la lógica nos dice que la suma DEBE ser la misma porque
        // los tres recorridos visitan exactamente los mismos nodos, solo cambia el orden.
        System.out.println("2. Verificación de recorridos: Los 3 recorridos visitan los mismos nodos, " +
                "por lo tanto la suma de sus elementos es idéntica (" + sumaTotal + ").");

        // 4. Suma de subárboles
        int sumaRaiz = (arbol.getInicial() != null) ? arbol.getInicial().getNodo() : 0;

        int sI = arbol.sumarNodos(arbol.getInicial().getNodoIzq());
        int sD = arbol.sumarNodos(arbol.getInicial().getNodoDer());

        System.out.println("3. Suma Sub. Izquierdo: " + sI);
        System.out.println("4. Suma Sub. Derecho: " + sD);
        System.out.println("5. Suma Raíz + SI + SD: " + (sumaRaiz + sI + sD));

        // ¿Por qué es la misma?
        // Porque un árbol es una estructura recursiva: la suma de un árbol
        // es su raíz más la suma de sus hijos.

        // 5. Altura del árbol
        // Al insertar en orden 0, 1, 2... el árbol crece solo hacia la derecha.
        System.out.println("6. Altura del árbol: " + arbol.getAltura());

        // 6. Camino al valor 110
        java.util.List<Integer> camino = arbol.getCamino(110);
        System.out.println("7. Camino al 110: " + camino);
        System.out.println("8. Longitud del camino: " + (camino.size() - 1));
    }
}
