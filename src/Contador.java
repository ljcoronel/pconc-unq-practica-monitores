public class Contador {

    private int valor;

    public synchronized int incrementar() { return ++this.valor; }

    public synchronized int decrementar() { return --this.valor; }

}