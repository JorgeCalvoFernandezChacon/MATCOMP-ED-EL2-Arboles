import EstructurasDeDatos.Cola;

public class Vertice<T> {
    T data;
    Cola<Arco<T>> arcosEntrada;
    Cola<Arco<T>> arcosSalida;

    public Vertice(T data) {
        this.data = data;
        this.arcosEntrada = new Cola<>();
        this.arcosSalida = new Cola<>();
    }

    public void addOutEdge(Arco<T> arco) {
        this.arcosSalida.enqueue(arco);
    }

    public void addInEdge(Arco<T> arco) {
        this.arcosEntrada.enqueue(arco);
    }
}
