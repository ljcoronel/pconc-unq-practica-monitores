public class AccesoDatosPrioridadEscritores {

    private int cantLectores;
    private int cantEscritores;
    private int cantEscritoresEsperando;

    public synchronized void beginRead() {
        while (!this.puedeLeer()) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
                return;
            }
        }
        this.cantLectores++;
    }

    public synchronized void endRead() {
        this.cantLectores--;
        if (this.cantLectores == 0) notifyAll();
    }

    public synchronized void beginWrite() {
        this.cantEscritoresEsperando++;
        while (!this.puedeEscribir()) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
                return;
            }
        }
        this.cantEscritoresEsperando--;
        this.cantEscritores = 1;
    }

    public synchronized void endWrite() {
        this.cantEscritores = 0;
        notifyAll();
    }

    private boolean puedeLeer() { return this.cantEscritores == 0 && this.cantEscritoresEsperando == 0; }

    private boolean puedeEscribir() { return this.cantEscritores == 0 && this.cantLectores == 0; }

}