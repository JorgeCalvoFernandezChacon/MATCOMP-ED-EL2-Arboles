import EstructurasDeDatos.ListaSE;
import EstructurasDeDatos.Cola;

public class Grafo<T> {
    ListaSE<Vertice<T>> vertices = new ListaSE<>();
    ListaSE<Arco<T>> arcos = new ListaSE<>();

    public void addVertex(Vertice<T> vertice) {
        this.vertices.add(vertice);
    }

    public void addEdge(Arco<T> arco) {
        this.arcos.add(arco);
        arco.origen.addOutEdge(arco);
        arco.destino.addInEdge(arco);

        // Hacemos que el arco vaya en los dos sentidos, para que el grafo sea no dirigido
        Arco<T> inverso = new Arco<>(arco.destino, arco.origen, arco.coste, arco.etiqueta + "_inv");
        arco.destino.addOutEdge(inverso);
        arco.origen.addInEdge(inverso);
    }

    public Camino<T> buscarCaminoMinimo(Vertice<T> inicio, Vertice<T> fin) {
        if (inicio == null || fin == null) return null;

        // Caso base: origen y destino son el mismo
        if (inicio == fin) {
            Cola<Vertice<T>> soloUno = new Cola<>();
            soloUno.enqueue(inicio);
            return new Camino<>(soloUno, 0.0);
        }

        // 1. Preparar todos los vértices
        resetearPropiedades();

        // 2. Usamos una Cola para buscar en anchura
        Cola<Vertice<T>> colaExploracion = new Cola<>();

        inicio.visitado = true;
        inicio.distancia = 0;
        colaExploracion.enqueue(inicio);

        boolean encontrado = false;

        while (!colaExploracion.vacia()) {
            Vertice<T> actual = colaExploracion.dequeue();
            // Cuando encontremos el nodo paramos
            if (actual == fin) {
                encontrado = true;
                break;
            }

            // 3. Explorar vecinos rotando la cola de arcos de salida
            int numArcos = actual.arcosSalida.size();
            for (int i = 0; i < numArcos; i++) {
                Arco<T> arco = actual.arcosSalida.dequeue();
                Vertice<T> vecino = arco.destino;

                if (!vecino.visitado) {
                    vecino.visitado = true;
                    // La distancia lógica es por saltos
                    vecino.distancia = actual.distancia + 1;

                    vecino.costeAcumulado = actual.costeAcumulado + arco.coste;

                    vecino.padre = actual;
                    colaExploracion.enqueue(vecino);
                }

                // Rotación: devolvemos el arco a la cola para no perderlo
                actual.arcosSalida.enqueue(arco);
            }
        }

        // 4. Si se encontró el destino, reconstruimos el objeto Camino
        if (encontrado) {
            return crearObjetoCamino(fin);
        }

        return null; // No hay conexión entre los puntos
    }

    private void resetearPropiedades() {
        // Recorrer la lista de vértices y limpiar datos de búsquedas anteriores
        for (int i = 0; i < vertices.size(); i++) {
            Vertice<T> v = vertices.get(i);
            v.visitado = false;
            v.distancia = -1;
            v.padre = null;
        }
    }

    private Camino<T> crearObjetoCamino(Vertice<T> destino) {
        // Para que la cola del camino vaya de Inicio -> Fin,
        // primero los metemos en una lista y luego los pasamos a la cola en orden inverso
        ListaSE<Vertice<T>> listaTemporal = new ListaSE<>();
        Vertice<T> actual = destino;
        double costeTotal = destino.costeAcumulado;

        // Vamos de hijo a padre (atrás hacia adelante)
        while (actual != null) {
            listaTemporal.add(actual);
            actual = actual.padre;
        }

        // Ahora los pasamos a una Cola en el orden correcto (Origen a Destino)
        Cola<Vertice<T>> colaCamino = new Cola<>();
        for (int i = listaTemporal.size() - 1; i >= 0; i--) {
            colaCamino.enqueue(listaTemporal.get(i));
        }

        return new Camino<>(colaCamino, costeTotal);
    }
    public Camino<T> obtenerCaminoDijkstra(Vertice<T> inicio, Vertice<T> fin) {
        if (inicio == null || fin == null) return null;

        // 1. Inicialización
        for (int i = 0; i < vertices.size(); i++) {
            Vertice<T> v = vertices.get(i);
            v.visitado = false;
            v.padre = null;
            v.costeAcumulado = Double.MAX_VALUE; // Infinito
        }

        inicio.costeAcumulado = 0.0;

        // 2. Bucle principal de Dijkstra
        for (int i = 0; i < vertices.size(); i++) {
            // Buscamos el vértice no visitado con la distancia mínima actual
            Vertice<T> u = buscarMinimoNoVisitado();

            if (u == null || u.costeAcumulado == Double.MAX_VALUE) break;

            u.visitado = true;

            if (u == fin) break; // Ya llegamos al destino de forma óptima

            // 3. Relajación de aristas
            int numArcos = u.arcosSalida.size();
            for (int j = 0; j < numArcos; j++) {
                Arco<T> arco = u.arcosSalida.dequeue();
                Vertice<T> v = arco.destino;

                if (!v.visitado) {
                    double nuevaDistancia = u.costeAcumulado + arco.coste;
                    if (nuevaDistancia < v.costeAcumulado) {
                        v.costeAcumulado = nuevaDistancia;
                        v.padre = u;
                    }
                }
                // Devolvemos el arco a su sitio
                u.arcosSalida.enqueue(arco);
            }
        }

        // 4. Reconstrucción del camino
        if (fin.costeAcumulado == Double.MAX_VALUE) return null;
        return crearObjetoCamino(fin);
    }


    private Vertice<T> buscarMinimoNoVisitado() {
        Vertice<T> minVertice = null;
        double minValor = Double.MAX_VALUE;

        for (int i = 0; i < vertices.size(); i++) {
            Vertice<T> v = vertices.get(i);
            if (!v.visitado && v.costeAcumulado < minValor) {
                minValor = v.costeAcumulado;
                minVertice = v;
            }
        }
        return minVertice;
    }
    public boolean esDisjunto() {
        if (vertices.size() == 0) return false;

        resetearPropiedades(); // Limpiar visitados

        // Lanzamos un BFS desde el primer vértice de la lista
        Vertice<T> raiz = vertices.get(0);
        Cola<Vertice<T>> q = new Cola<>();
        raiz.visitado = true;
        q.enqueue(raiz);

        int visitadosCount = 0;

        while (!q.vacia()) {
            Vertice<T> actual = q.dequeue();
            visitadosCount++;

            // Recorrer vecinos
            int n = actual.arcosSalida.size();
            for (int i = 0; i < n; i++) {
                Arco<T> a = actual.arcosSalida.dequeue();
                if (!a.destino.visitado) {
                    a.destino.visitado = true;
                    q.enqueue(a.destino);
                }
                actual.arcosSalida.enqueue(a);
            }
        }

        // Si visitamos menos nodos de los que existen, hay partes aisladas
        return visitadosCount < vertices.size();
    }
    public void cargarTripleta(String s, String p, String o) {
        Vertice<T> vOrigen = buscarOCrearVertice(s);
        Vertice<T> vDestino = buscarOCrearVertice(o);

        // El predicado 'p' (ej: "persona:nace_en") se guarda como etiqueta.
        // Le damos peso 1.0 para que Dijkstra pueda sumar distancias.
        Arco<T> nuevoArco = new Arco<>(vOrigen, vDestino, 1.0, p);

        this.addEdge(nuevoArco);
    }

    private Vertice<T> buscarOCrearVertice(String nombreFull) {
        for (int i = 0; i < vertices.size(); i++) {
            // Comparamos el string completo "persona:Albert Einstein"
            if (vertices.get(i).data.equals(nombreFull)) {
                return vertices.get(i);
            }
        }
        Vertice<T> nuevo = new Vertice<>((T) nombreFull);
        this.addVertex(nuevo);
        return nuevo;
    }
    public void cargarGrafoDesdeJson(String rutaArchivo) {
        try {
            java.io.BufferedReader br = new java.io.BufferedReader(new java.io.FileReader(rutaArchivo));
            String linea;
            String s = "", p = "", o = "";

            while ((linea = br.readLine()) != null) {
                // Buscamos las claves s, p, o en el texto
                if (linea.contains("\"s\":")) s = extraerValorJson(linea);
                if (linea.contains("\"p\":")) p = extraerValorJson(linea);
                if (linea.contains("\"o\":")) {
                    o = extraerValorJson(linea);
                    // Cuando tenemos las tres partes de la tripleta, la cargamos
                    cargarTripleta(s, p, o);
                }
            }
            br.close();
        } catch (Exception e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
    }

    // Método para limpiar las comillas y comas del JSON manual
    private String extraerValorJson(String linea) {
        String[] partes = linea.split(":");
        if (partes.length < 2) return "";

        // Unimos todas las partes después de la primera clave por si el valor tiene ":"
        StringBuilder valorRaw = new StringBuilder();
        for (int i = 1; i < partes.length; i++) {
            valorRaw.append(partes[i]);
            if (i < partes.length - 1) valorRaw.append(":");
        }

        return valorRaw.toString().trim().replace("\"", "").replace(",", "");
    }
    public Vertice<T> buscarVertice(T datoBuscado) {
        // Recorremos la lista de vértices del grafo
        for (int i = 0; i < vertices.size(); i++) {
            Vertice<T> v = vertices.get(i);

            // Comparamos el contenido del vértice con el dato buscado
            if (v.data.equals(datoBuscado)) {
                return v; // Si lo encontramos, devolvemos el objeto vértice
            }
        }
        return null; // Si terminamos el bucle y no está, devolvemos null
    }
}
