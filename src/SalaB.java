public class SalaB {

    private boolean hayCharla;
    private int cantAsistentes;
    private int capacidad;
    private int charlaActual;

    public SalaB(int n) { this.capacidad = n; }

    public synchronized void entrar(int numeroDeCharla) {
        while (!this.puedeEntrar(numeroDeCharla)) try { wait(); } catch(InterruptedException e) { return; }
        this.cantAsistentes++;
        if (this.cantAsistentes == 1) notifyAll();
    }

    public synchronized void salir(int numeroDeCharla) {
        while (!this.puedeRetirarse(numeroDeCharla)) try { wait(); } catch(InterruptedException e) { return; }
        this.cantAsistentes--;
        notify();
    }

    public synchronized void inicioCharla(int numeroDeCharla) {
        while (!this.puedeIniciarLaCharla(numeroDeCharla)) try { wait(); } catch(InterruptedException e) { return; }
        this.hayCharla = true;
    }

    public synchronized void finCharla() {
        this.hayCharla = false;
        this.rotarCharla();
        notifyAll();
    }

    private boolean puedeRetirarse(int numeroDeCharla) { return !this.hayCharla || this.esCharlaCorrespondiente(numeroDeCharla); }

    private boolean puedeIniciarLaCharla(int numeroDeCharla) { return !this.hayCharla && this.hayAsistentes() && this.esCharlaCorrespondiente(numeroDeCharla); }

    private boolean puedeEntrar(int numeroDeCharla) { return this.hayLugar() && this.esCharlaCorrespondiente(numeroDeCharla); }

    private boolean hayAsistentes() { return this.cantAsistentes > 0; }

    private boolean hayLugar() { return this.capacidad > this.cantAsistentes; }

    private void rotarCharla() { this.charlaActual = (this.charlaActual + 1) % 3; }

    private boolean esCharlaCorrespondiente(int numeroDeCharla) { return this.charlaActual == numeroDeCharla; }

}