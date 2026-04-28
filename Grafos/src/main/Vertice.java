import EstructurasDeDatos.Cola;

public class Vertice<T> {
    T data;
    boolean visitado;
    int distancia;
    Cola<Arco<T>> arcosEntrada;
    Cola<Arco<T>> arcosSalida;
    public Vertice<T> padre;
    double costeAcumulado;

    public Vertice(T data) {
        this.data = data;
        this.visitado = false;
        this.distancia = -1;
        this.arcosEntrada = new Cola<>();
        this.arcosSalida = new Cola<>();
        this.padre = null;
        this.costeAcumulado = 0;
    }
    public void setPadre(Vertice<T> newPadre){
        this.padre = newPadre;
    }
    public void setVisitado(boolean v) { this.visitado = v; }
    public boolean isVisitado() { return visitado; }
    public void setDistancia(int d) { this.distancia = d; }
    public int getDistancia() { return distancia; }
    public void addOutEdge(Arco<T> arco) {
        this.arcosSalida.enqueue(arco);
    }
    public void addInEdge(Arco<T> arco) {
        this.arcosEntrada.enqueue(arco);
    }
}
