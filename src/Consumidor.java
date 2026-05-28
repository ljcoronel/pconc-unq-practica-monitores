public class Consumidor extends Thread {

    private final Buffer buffer;

    public Consumidor(Buffer buffer) { this.buffer = buffer; }

    public void run() {
        Object o;
        while (true) {
            o = this.buffer.consumir();
            System.out.println("Leido " + o.toString());
        }
    }

}