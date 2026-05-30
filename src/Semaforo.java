public class Semaforo {

    private int permisos;

    public Semaforo(int permisosInicial) { this.permisos = permisosInicial; }

    public synchronized void acquire() {
        while (this.permisos == 0) try { wait(); } catch(InterruptedException e) { return; }
        this.permisos--;
    }

    public synchronized void release() {
        this.permisos++;
        this.notify();
    }

}