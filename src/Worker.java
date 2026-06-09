public class Worker extends Thread {

    private Buffer buffer;
    private boolean terminado;

    public Worker(Buffer buffer) { this.buffer = buffer; }

    public void run() {
        while (!this.terminado) {
            try {
                ((Runnable) this.buffer.consumir()).run();
            } catch(PoisonException e) {
                System.out.println("terminé");
                this.terminado = true;
            }
        }
    }

}