import EstructurasDeDatos.Cola;

public class Camino<T> {
    Cola<Vertice<T>> camino;
    double coste;

    public Camino(Cola<Vertice<T>> camino, double coste) {
        this.camino = camino;
        this.coste = coste;
    }

    public Cola<Vertice<T>> getCamino() {
        return this.camino;
    }

    public double getCoste() {
        return this.coste;
    }

    public String toString() {
        // 1. Verificamos si hay camino para evitar errores
        if (this.getCamino() == null || this.getCamino().vacia()) {
            return "El camino está vacío.";
        }

        int tamano = this.getCamino().tamañoLista();
        // 2. Obtener el ORIGEN (está al frente)
        T origen = this.getCamino().peek().data;
        T destino = null;
        String listaDatos = "";
        // 3. ROTACIÓN COMPLETA para no perder el orden
        // Sacamos cada elemento, lo leemos y lo volvemos a meter al final
        for (int i = 0; i < tamano; i++) {
            Vertice<T> actual = this.getCamino().dequeue();
            // El último elemento que pase por aquí será el destino
            destino = actual.data;
            // Concatenamos el dato a nuestra lista
            listaDatos += actual.data;
            if (i < tamano - 1) {
                listaDatos += ", "; // Añade coma excepto al último
            }
            // LO DEVOLVEMOS a la cola para mantener el orden original
            this.getCamino().enqueue(actual);
        }
        // 4. Construimos el mensaje final sumando los Strings
        String resultado = "======= Volcado del camino desde [" + origen + "] hasta [" + destino + "]: ======\n";
        resultado += "Lista de datos en vértices: [" + listaDatos + "]\n";
        resultado += "Coste total: " + this.getCoste() + "\n";

        return resultado;
    }
}
