public class GridB {

    private int cantProductores;
    private int cantConsumidores;
    private int cantProductoresOciosos;

    public GridB(int n) { this.cantProductoresOciosos = n; }

    public synchronized void inicioConsumo() {
        while (!this.puedeComportarseComoConsumidor()) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
                return;
            }
        }
        this.cantConsumidores++;
        notifyAll();
    }

    public synchronized void finConsumo() {
        this.cantConsumidores--;
        notifyAll();
    }

    public synchronized void inicioProduccion() {
        while (!this.puedeComportarseComoProductor()) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
                return;
            }
        }
        this.cantProductores++;
        notify();
    }

    public synchronized void finProduccion() {
        while (!this.puedeDejarDeProducir()) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
                return;
            }
        }
        this.cantProductores--;
        notifyAll();
    }

    private boolean puedeComportarseComoConsumidor() { return this.cantProductores >= this.cantConsumidores; }

    private boolean puedeDejarDeProducir() { return this.cantProductores > this.cantConsumidores; }

    private boolean puedeComportarseComoProductor() { return this.cantProductoresMasQueConsumidores() < this.cantProductoresOciosos; }

    private int cantProductoresMasQueConsumidores() { return this.cantProductores - this.cantConsumidores; }

}