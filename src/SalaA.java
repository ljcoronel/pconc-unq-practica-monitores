public class SalaA {

    private boolean hayCharla;
    private int cantAsistentes;
    private int capacidad;

    public SalaA(int n) { this.capacidad = n; }

    public synchronized void entrar() {
        while (!this.puedeEntrar()) try { wait(); } catch(InterruptedException e) { return; }
        this.cantAsistentes++;
        if (this.cantAsistentes == 1) notifyAll();
    }

    public synchronized void salir() {
        while (!this.puedeRetirarse()) try { wait(); } catch(InterruptedException e) { return; }
        this.cantAsistentes--;
        notify();
    }

    public synchronized void inicioCharla() {
        while (!this.puedeIniciarLaCharla()) try { wait(); } catch(InterruptedException e) { return; }
        this.hayCharla = true;
    }

    public synchronized void finCharla() {
        this.hayCharla = false;
        notifyAll();
    }

    private boolean puedeRetirarse() { return !this.hayCharla; }

    private boolean puedeIniciarLaCharla() { return !this.hayCharla && this.hayAsistentes(); }

    private boolean puedeEntrar() { return this.capacidad > this.cantAsistentes; }

    private boolean hayAsistentes() { return this.cantAsistentes > 0; }

}