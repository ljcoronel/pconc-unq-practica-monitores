public class AccesoDatos {

    private int cantLectores;
    private int cantEscritores;

    public synchronized void beginRead() {
        while (!this.puedeLeer()) try { wait(); } catch(InterruptedException e) { return; }
        this.cantLectores++;
    }

    public synchronized void endRead() {
        this.cantLectores--;
        if (this.cantLectores == 0) notify();
    }

    public synchronized void beginWrite() {
        while (!this.puedeEscribir()) try { wait(); } catch(InterruptedException e) { return; }
        this.cantEscritores = 1;
    }

    public synchronized void endWrite() {
        this.cantEscritores = 0;
        notifyAll();
    }

    private boolean puedeLeer() { return this.cantEscritores == 0; }

    private boolean puedeEscribir() { return this.cantEscritores == 0 && this.cantLectores == 0; }

}