public class Worker extends Thread {

    private Buffer buffer;

    public Worker(Buffer buffer) { this.buffer = buffer; }

    public void run() {
        while (true) {
            try {
                ((Runnable) this.buffer.consumir()).run();
            } catch(PoisonException e) {
                System.out.println("termine");
                return;
            }
        }
    }

}