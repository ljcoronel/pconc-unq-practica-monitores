public class Incrementador extends Thread {

    private Contador miContador;

    public Incrementador (Contador cont) {
        this.miContador = cont;
    }

    public void run() {
        for (int i=0; i<10000; i++) this.miContador.incrementar();
        System.out.println("El valor es: " + this.miContador.incrementar());
    }

}