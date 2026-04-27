import java.util.*;

public class Grafo<T> {
    List<Vertice<T>> vertices = new ArrayList();
    List<Arco<T>> arcos = new ArrayList();

    public void addVertex(Vertice<T> vertice) {
        this.vertices.add(vertice);
    }

    public void addEdge(Arco<T> arco) {
        this.arcos.add(arco);
        arco.origen.addOutEdge(arco);
        arco.destino.addInEdge(arco);
    }

    public Map<Vertice<T>, Camino<T>> dijkstra(Vertice<T> origen) {
        Map<Vertice<T>, Double> distancias = new HashMap();
        Queue<Vertice<T>> colaPendientes = new LinkedList();
        Map<Vertice<T>, Vertice<T>> verticesAnteriores = new HashMap();
        this.dijkstra_init(origen, distancias, colaPendientes, verticesAnteriores);
        this.dijkstra_calcula(distancias, colaPendientes, verticesAnteriores);
        return this.dijkstra_procesaResultados(distancias, verticesAnteriores);
    }

    protected void dijkstra_init(Vertice<T> origen, Map<Vertice<T>, Double> distancias, Queue<Vertice<T>> colaPendientes, Map<Vertice<T>, Vertice<T>> verticesAnteriores) {
        for(Vertice<T> vertice : this.vertices) {
            distancias.put(vertice, Double.MAX_VALUE);
        }

        distancias.put(origen, (double)0.0F);
        colaPendientes.add(origen);
    }

    protected void dijkstra_calcula(Map<Vertice<T>, Double> distancias, Queue<Vertice<T>> colaPendientes, Map<Vertice<T>, Vertice<T>> verticesAnteriores) {
        label19:
        while(true) {
            if (!colaPendientes.isEmpty()) {
                Vertice<T> current = (Vertice)colaPendientes.poll();
                Iterator var5 = current.arcosSalida.iterator();

                while(true) {
                    if (!var5.hasNext()) {
                        continue label19;
                    }

                    Arco<T> arco = (Arco)var5.next();
                    Vertice<T> neighbor = arco.destino;
                    double newDistance = (Double)distancias.get(current) + arco.coste;
                    if (newDistance < (Double)distancias.get(neighbor)) {
                        distancias.put(neighbor, newDistance);
                        verticesAnteriores.put(neighbor, current);
                        colaPendientes.add(neighbor);
                    }
                }
            }

            return;
        }
    }

    protected Map<Vertice<T>, Camino<T>> dijkstra_procesaResultados(Map<Vertice<T>, Double> distancias, Map<Vertice<T>, Vertice<T>> verticesAnteriores) {
        Map<Vertice<T>, Camino<T>> caminos = new HashMap();

        for(Vertice<T> verticeDestino : verticesAnteriores.keySet()) {
            List<Vertice<T>> caminoCalculado = new ArrayList();

            for(Vertice<T> v = verticeDestino; v != null; v = (Vertice)verticesAnteriores.get(v)) {
                caminoCalculado.add(v);
            }

            Collections.reverse(caminoCalculado);
            caminos.put(verticeDestino, new Camino(caminoCalculado, (Double)distancias.get(verticeDestino)));
        }

        return caminos;
    }
}