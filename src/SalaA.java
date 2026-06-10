public class SalaA {

    private int capacidad;
    private int cantAsistentes;
    private boolean hayCharlaEnCurso;

    public SalaA(int n) { this.capacidad = n; }

    public synchronized void entrarSala() {
        while (!this.puedeEntrar()) {
            try {
                wait();
            } catch(InterruptedException e) {
                e.printStackTrace();
                return;
            }
        }
        this.cantAsistentes++;
        if (this.cantAsistentes == 1) notify();
    }

    public synchronized void salirSala() {
        while (!this.puedeRetirarse()) {
            try {
                wait();
            } catch(InterruptedException e) {
                e.printStackTrace();
                return;
            }
        }
        this.cantAsistentes--;
        notify();
    }

    public synchronized void iniciarCharla() {
        while (!this.puedeIniciarLaCharla()) {
            try {
                wait();
            } catch(InterruptedException e) {
                e.printStackTrace();
                return;
            }
        }
        this.hayCharlaEnCurso = true;
    }

    public synchronized void finalizarCharla() {
        this.hayCharlaEnCurso = false;
        notifyAll();
    }

    private boolean puedeRetirarse() { return !this.hayCharlaEnCurso; }

    private boolean puedeIniciarLaCharla() { return !this.hayCharlaEnCurso && this.hayAsistentes(); }

    private boolean puedeEntrar() { return this.capacidad > this.cantAsistentes; }

    private boolean hayAsistentes() { return this.cantAsistentes > 0; }

}