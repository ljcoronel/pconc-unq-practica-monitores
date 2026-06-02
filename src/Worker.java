public class Worker extends Thread {

    private Buffer buffer;

    public Worker(Buffer buffer) { this.buffer = buffer; }

    public void run() { while (true) ((Runnable) this.buffer.consumir()).run(); }

}