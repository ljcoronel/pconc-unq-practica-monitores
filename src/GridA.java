public class GridA {

    private int cantProductores;
    private int cantConsumidores;

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
    }

    public synchronized void finConsumo() {
        this.cantConsumidores--;
        notifyAll();
    }

    public synchronized void inicioProduccion() {
        this.cantProductores++;
        notifyAll();
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
    }

    private boolean puedeComportarseComoConsumidor() { return this.cantProductores >= this.cantConsumidores; }

    private boolean puedeDejarDeProducir() { return this.cantProductores > this.cantConsumidores; }

}