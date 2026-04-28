public class MainGrafo {
    public static void main(String[] args) {
        Grafo<String> mapa = new Grafo<>();

        Vertice<String> vA = new Vertice<>("A");
        Vertice<String> vB = new Vertice<>("B");
        Vertice<String> vC = new Vertice<>("C");
        Vertice<String> vD = new Vertice<>("D");
        Vertice<String> vE = new Vertice<>("E");

        mapa.addVertex(vA);
        mapa.addVertex(vB);
        mapa.addVertex(vC);
        mapa.addVertex(vD);
        mapa.addVertex(vE);

        // Añadimos arcos con pesos: origen, destino, peso
        mapa.addEdge(new Arco<>(vA, vB, 15,"etiqueta"));
        mapa.addEdge(new Arco<>(vB, vD, 10,"etiqueta"));
        mapa.addEdge(new Arco<>(vB, vE, 1,"etiqueta"));
        mapa.addEdge(new Arco<>(vD, vC, 1,"etiqueta"));
        mapa.addEdge(new Arco<>(vE, vC, 4, "etiqueta"));

        System.out.println("Calculando ruta de A a C...");
        Camino<String> resultado = mapa.buscarCaminoMinimo(vA, vC);

        if (resultado != null) {
            // Esto mostrará el coste total: 25.7
            System.out.println(resultado.toString());
        }
        System.out.println("Calculando ruta de A a C...");
        Camino<String> dijkstra = mapa.obtenerCaminoDijkstra(vA, vC);

        if (resultado != null) {
            // Esto mostrará el coste total: 25.7
            System.out.println(dijkstra.toString());
        }

        Grafo<String> miGrafojunto = new Grafo<>();
        Grafo<String> miGrafodisjunto = new Grafo<>();

        // Le pasas la ruta de tu archivo .json
        miGrafojunto.cargarGrafoDesdeJson("C:\\Users\\jcalv\\IdeaProjects\\MATCOMP-ED-EL2-Arboles\\Grafos\\src\\main\\junto.json");

        // Para ver si es disjunto:
        System.out.println("¿Es el grafo disjunto? " + miGrafojunto.esDisjunto());
        // Le pasas la ruta de tu archivo .json
        miGrafodisjunto.cargarGrafoDesdeJson("C:\\Users\\jcalv\\IdeaProjects\\MATCOMP-ED-EL2-Arboles\\Grafos\\src\\main\\disjunto.json");

        // Para ver si es disjunto:
        System.out.println("¿Es el grafo disjunto? " + miGrafodisjunto.esDisjunto());

        // Para probar Dijkstra:
        // Recuerda que debes pasar el String exacto como está en el JSON
        Vertice<String> inicio = miGrafojunto.buscarVertice("persona:Albert Einstein");
        Vertice<String> fin = miGrafojunto.buscarVertice("persona:Marie Curie");


        if (inicio != null && fin != null) {
            Camino<String> resultado3 = miGrafojunto.obtenerCaminoDijkstra(inicio, fin);
            System.out.println(resultado3.toString());
        }
        Vertice<String> inicio2 = miGrafojunto.buscarVertice("ciencia:Relatividad");
        Vertice<String> fin2 = miGrafojunto.buscarVertice("pais:Suiza");


        if (inicio != null && fin != null) {
            Camino<String> resultado4 = miGrafojunto.obtenerCaminoDijkstra(inicio2, fin2);
            System.out.println(resultado4.toString());
        }
    }
}