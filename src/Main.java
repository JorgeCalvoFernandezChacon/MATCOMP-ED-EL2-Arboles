public class Main {
    public static void main(String[] args) {
        ArbolBinarioDeBusqueda arbol = new ArbolBinarioDeBusqueda();
        arbol.insertar(43);
        arbol.insertar(10);
        arbol.insertar(8);
        arbol.insertar(54);
        arbol.insertar(15);
        arbol.insertar(50);
        arbol.insertar(53);
        System.out.println("PREORDEN:");
        arbol.getListaPreOrden();
        System.out.println("");
        System.out.println("POSTORDEN:");
        arbol.getListaPostOrden();
        System.out.println("");
        System.out.println("ÓRDEN CENTRAL:");
        arbol.getListaOrdenCentral();
        System.out.println("");
    }
}