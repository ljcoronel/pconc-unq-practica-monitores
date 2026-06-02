public class ThreadPool {

    private Buffer buffer;

    public ThreadPool(int capacidad, int cantWorkers) {
        this.buffer = new Buffer(capacidad);
        for (int i = 0; i < cantWorkers; i++) new Worker(this.buffer).start();
    }

    public synchronized void launch() { this.buffer.producir(new DummyTask()); }

    public synchronized void stop() { this.buffer.producir(new PoisonPill()); }

}