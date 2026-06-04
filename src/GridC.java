public class GridC {

    private int cantProductores;
    private int cantConsumidores;
    private int cantProductoresOciosos;
    private int cantProductoresEsperando;

    public GridC(int n) { this.cantProductoresOciosos = n; }

    public synchronized void inicioConsumo() {
        while (!this.puedeComportarseComoConsumidor()) try { wait(); } catch(InterruptedException e) { return; }
        this.cantConsumidores++;
        notifyAll();
    }

    public synchronized void finConsumo() {
        this.cantConsumidores--;
        notifyAll();
    }

    public synchronized void inicioProduccion() {
        while (!this.puedeComportarseComoProductor()) try { wait(); } catch(InterruptedException e) { return; }
        this.cantProductores++;
        notifyAll();
    }

    public synchronized void finProduccion() {
        this.cantProductoresEsperando++;
        while (!this.puedeDejarDeProducir()) try { wait(); } catch(InterruptedException e) { return; }
        this.cantProductoresEsperando--;
        this.cantProductores--;
        notify();
    }

    private boolean puedeComportarseComoConsumidor() { return this.hayCantidadIgualOMayorDeProductores() && this.cantProductoresEsperando == 0; }

    private boolean puedeDejarDeProducir() { return this.cantProductores > this.cantConsumidores; }

    private boolean puedeComportarseComoProductor() { return this.cantProductoresMasQueConsumidores() < this.cantProductoresOciosos; }

    private boolean hayCantidadIgualOMayorDeProductores() { return this.cantProductores >= this.cantConsumidores; }

    private int cantProductoresMasQueConsumidores() { return this.cantProductores - this.cantConsumidores; }

}