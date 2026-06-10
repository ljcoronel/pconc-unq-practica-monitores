public class SalaB {

    private int capacidad;
    private int cantAsistentes;
    private boolean hayCharlaEnCurso;
    private int charlaActual;

    public SalaB(int n) { this.capacidad = n; }

    public synchronized void entrarSala(int numeroDeCharla) {
        while (!this.puedeEntrar(numeroDeCharla)) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
                return;
            }
        }
        this.cantAsistentes++;
        if (this.cantAsistentes == 1) notifyAll();
    }

    public synchronized void salirSala(int numeroDeCharla) {
        while (!this.puedeRetirarse(numeroDeCharla)) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
                return;
            }
        }
        this.cantAsistentes--;
        notify();
    }

    public synchronized void iniciarCharla(int numeroDeCharla) {
        while (!this.puedeIniciarLaCharla(numeroDeCharla)) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
                return;
            }
        }
        this.hayCharlaEnCurso = true;
    }

    public synchronized void finalizarCharla() {
        this.hayCharlaEnCurso = false;
        this.rotarCharla();
        notifyAll();
    }

    private boolean puedeRetirarse(int numeroDeCharla) { return !this.hayCharlaEnCurso || this.esCharlaCorrespondiente(numeroDeCharla); }

    private boolean puedeIniciarLaCharla(int numeroDeCharla) { return !this.hayCharlaEnCurso && this.hayAsistentes() && this.esCharlaCorrespondiente(numeroDeCharla); }

    private boolean puedeEntrar(int numeroDeCharla) { return this.hayLugar() && this.esCharlaCorrespondiente(numeroDeCharla); }

    private boolean hayAsistentes() { return this.cantAsistentes > 0; }

    private boolean hayLugar() { return this.capacidad > this.cantAsistentes; }

    private void rotarCharla() { this.charlaActual = (this.charlaActual + 1) % 3; }

    private boolean esCharlaCorrespondiente(int numeroDeCharla) { return this.charlaActual == numeroDeCharla; }

}