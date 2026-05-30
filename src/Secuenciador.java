public class Secuenciador {

    private int proximo;

    public synchronized void primero() { this.invocar(0); }

    public synchronized void segundo() { this.invocar(1); }

    public synchronized void tercero() { this.invocar(2); }

    private synchronized void invocar(int valor) {
        while (this.proximo != valor) try { wait(); } catch(InterruptedException e) { return; }
        this.proximo = (valor + 1) % 3;
        notifyAll();
    }

}