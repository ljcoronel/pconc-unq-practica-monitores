public class Contador {

    private int valor;

    public Contador() {
        this.valor = 0;
    }

    public synchronized int incrementar() {
        return ++this.valor;
    }

    public synchronized int decrementar() {
        return --this.valor;
    }

}