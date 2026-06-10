public class Barrera {

    private int restantes;

    public Barrera(int total) { this.restantes = total; }

    public synchronized void esperar() {
        if (this.restantes > 0) this.restantes--;
        while (this.restantes > 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
                return;
            }
        }
        notify();
    }
}