public class Arco<T> {
    Vertice<T> origen;
    Vertice<T> destino;
    double coste;
    String etiqueta;

    public Arco(Vertice<T> origen, Vertice<T> destino, double coste, String etiqueta) {
        this.origen = origen;
        this.destino = destino;
        this.coste = coste;
        this.etiqueta = etiqueta;
    }
}
