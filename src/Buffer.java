public class Buffer {

    private Object[] datos;
    private int inicio, fin, N;

    public Buffer(int tamano) {
        this.N = tamano;
        this.datos = new Object[this.N+1];
    }

    public synchronized void producir(Object o) {
        while (this.lleno()) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
                return;
            }
        }
        this.datos[this.inicio] = o;
        this.inicio = next(this.inicio);
        notifyAll();
    }

    public synchronized Object consumir() {
        while (this.vacio()) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
                return null;
            }
        }
        Object result = this.datos[this.fin];
        this.datos[this.fin] = null;
        this.fin = next(this.fin);
        notifyAll();
        return result;
    }

    private boolean vacio() { return this.inicio == this.fin; }

    private boolean lleno() { return next(this.inicio) == this.fin; }

    private int next(int i) { return (i+1)%(this.N+1); }

}