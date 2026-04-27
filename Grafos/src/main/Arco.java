public class Arco<T> {
    Vertice<T> origen;
    Vertice<T> destino;
    double coste;

    public Arco(Vertice<T> origen, Vertice<T> destino, double coste) {
        this.origen = origen;
        this.destino = destino;
        this.coste = coste;
    }
}
