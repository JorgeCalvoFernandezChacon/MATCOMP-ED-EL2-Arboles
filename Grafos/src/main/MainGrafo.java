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

        // Añadimos arcos con pesos
        mapa.addEdge(new Arco<>(vA, vB, 15,"etiqueta"));
        mapa.addEdge(new Arco<>(vB, vD, 10,"etiqueta"));
        mapa.addEdge(new Arco<>(vB, vE, 1,"etiqueta"));
        mapa.addEdge(new Arco<>(vD, vC, 1,"etiqueta"));
        mapa.addEdge(new Arco<>(vE, vC, 4, "etiqueta"));

        // Estamos buscando el camino que recorra menos aristas, no el que tenga menos peso
        System.out.println("Calculando ruta de A a C...");
        Camino<String> resultado = mapa.buscarCaminoMinimo(vA, vC);

        if (resultado != null) {
            System.out.println(resultado.toString());
        }
        // Estamos buscando el camino que tenga menos peso, no el que tenga menos aristas
        System.out.println("Calculando ruta de A a C...");
        Camino<String> dijkstra = mapa.obtenerCaminoDijkstra(vA, vC);

        if (resultado != null) {
            System.out.println(dijkstra.toString());
        }

        Grafo<String> miGrafojunto = new Grafo<>();
        Grafo<String> miGrafodisjunto = new Grafo<>();

        // Ruta del archivo .json
        miGrafojunto.cargarGrafoDesdeJson("C:\\Users\\jcalv\\IdeaProjects\\MATCOMP-ED-EL2-Arboles\\Grafos\\src\\main\\junto.json");

        // Para ver si es disjunto:
        System.out.println("¿Es el grafo disjunto? " + miGrafojunto.esDisjunto());

        // Ruta del archivo .json
        miGrafodisjunto.cargarGrafoDesdeJson("C:\\Users\\jcalv\\IdeaProjects\\MATCOMP-ED-EL2-Arboles\\Grafos\\src\\main\\disjunto.json");

        // Para ver si es disjunto:
        System.out.println("¿Es el grafo disjunto? " + miGrafodisjunto.esDisjunto());

        // Probamos a ver si funciona Dijkstra:
        Vertice<String> inicio = miGrafojunto.buscarVertice("persona:Albert Einstein");
        Vertice<String> fin = miGrafojunto.buscarVertice("persona:Marie Curie");

        if (inicio != null && fin != null) {
            Camino<String> resultado3 = miGrafojunto.obtenerCaminoDijkstra(inicio, fin);
            System.out.println(resultado3.toString());
        }
        // Lo probamos un poco más a fondo
        Vertice<String> inicio2 = miGrafojunto.buscarVertice("ciencia:Relatividad");
        Vertice<String> fin2 = miGrafojunto.buscarVertice("pais:Suiza");

        if (inicio != null && fin != null) {
            Camino<String> resultado4 = miGrafojunto.obtenerCaminoDijkstra(inicio2, fin2);
            System.out.println(resultado4.toString());
        }
        Grafo<String> miGrafoEinstein = new Grafo<>();

        // 1. Cargamos el grafo
        miGrafoEinstein.cargarGrafoDesdeJson("C:\\Users\\jcalv\\IdeaProjects\\MATCOMP-ED-EL2-Arboles\\Grafos\\src\\main\\Nobel.json");

        // 2. Situamos el orígen en Einstein
        Vertice<String> einstein = miGrafoEinstein.buscarVertice("persona:Albert Einstein");

        // Buscamos la ciudad de Einstein explorando sus arcos
        String ciudadEinstein = "";
        int numArcos = einstein.arcosSalida.size();
        for (int i = 0; i < numArcos; i++) {
            Arco<String> arco = einstein.arcosSalida.dequeue();
            if (arco.etiqueta.equals("nace_en")) {
                ciudadEinstein = arco.destino.data;
            }
            einstein.arcosSalida.enqueue(arco); // No olvidar rotar
        }

        System.out.println("Einstein nació en: " + ciudadEinstein);

        // 3. Buscamos quién más nació allí
        Vertice<String> ciudad = miGrafoEinstein.buscarVertice(ciudadEinstein);

        int numEntrada = ciudad.arcosEntrada.size();
        for (int i = 0; i < numEntrada; i++) {
            Arco<String> arco = ciudad.arcosEntrada.dequeue();
            if (!arco.origen.data.equals("persona:Albert Einstein")) {
                System.out.println("Otro famoso que nació en " + ciudadEinstein + " es: " + arco.origen.data);
            }
            ciudad.arcosEntrada.enqueue(arco);
        }

        // 1. Cargamos el grafo JSON
        Grafo<String> miGrafoNacimiento = new Grafo<>();
        miGrafoNacimiento.cargarGrafoDesdeJson("C:\\Users\\jcalv\\IdeaProjects\\MATCOMP-ED-EL2-Arboles\\Grafos\\src\\main\\NacimientoNobel");

        // 2. Añadimos la tripleta de Antonio
        miGrafoNacimiento.cargarTripleta("persona:Antonio", "nace_en", "lugar:Villarrubia de los Caballeros");

        System.out.println("--- Nacimientos Nobel.json ---");

        // 3. Buscamos
        for (int i = 0; i < miGrafoNacimiento.vertices.size(); i++) {
            Vertice<String> v = miGrafoNacimiento.vertices.get(i);

            if (v.data.startsWith("persona:")) {
                boolean esNobel = false;
                String lugarNacimiento = "Desconocido";

                int numArcos2 = v.arcosSalida.size();
                for (int j = 0; j < numArcos2; j++) {
                    Arco<String> arco = v.arcosSalida.dequeue();

                    // Verificamos si la etiqueta es "gano" y el destino es un Nobel.json
                    if (arco.etiqueta.equals("gano") && arco.destino.data.contains("premio:Nobel.json")) {
                        esNobel = true;
                    }
                    // Guardamos el lugar si la etiqueta es "nace_en"
                    if (arco.etiqueta.equals("nace_en")) {
                        lugarNacimiento = arco.destino.data;
                    }

                    v.arcosSalida.enqueue(arco); // Rotación
                }

                // Solo imprimimos si confirma que es Nobel.json
                if (esNobel) {
                    System.out.println("El laureado " + v.data + " nació en: " + lugarNacimiento);
                }
            }
        }
    }
}