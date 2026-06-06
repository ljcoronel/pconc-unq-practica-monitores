public class ThreadPool {

    private Buffer buffer;
    private int cantWorkers;

    public ThreadPool(int capacidad, int cantWorkers) {
        this.buffer = new Buffer(capacidad);
        this.cantWorkers = cantWorkers;
        for (int i = 0; i < cantWorkers; i++) new Worker(this.buffer).start();
    }

    public synchronized void launch() { this.buffer.producir(new DummyTask()); }

    public synchronized void stop() { for (int i = 0; i < this.cantWorkers; i++) this.buffer.producir(new PoisonPill()); }

}