public class PracticaMonitores {

    public static void main (String[] args) {
        Buffer buffer = new Buffer(2);
        Consumidor consumidor = new Consumidor(buffer);
        Productor  productor = new Productor(buffer);

        productor.start();
        consumidor.start();
    }

}