public class Promise implements Future {

    private Object resultado;
    private boolean estaDisponible;

    public synchronized Object await() {
        while (!this.estaDisponible) {
            try {
                wait();
            } catch(InterruptedException e) {
                e.printStackTrace();
                return null;
            }
        }
        if (this.resultado instanceof Throwable) {
            ((Throwable) this.resultado).printStackTrace();
            return null;
        }
        return this.resultado;
    }

    public synchronized void set(Object resultado) {
        this.resultado = resultado;
        this.estaDisponible = true;
        notifyAll();
    }

}