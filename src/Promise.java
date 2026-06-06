import java.util.concurrent.Future;

public class Promise implements Future {

    private Object resultado;
    private boolean estaDisponible;

    public synchronized Object await() {
        while (!this.estaDisponible) try { wait(); } catch(InterruptedException e) { return e; }
        return this.resultado;
    }

    public synchronized void set(Object resultado) {
        this.resultado = resultado;
        this.estaDisponible = true;
        notifyAll();
    }

}