import java.util.List;

public class Writer extends Thread {

    private int valor;
    private List<Integer> datos;
    private AccesoDatos monitorAccessoDatos;

    public Writer(int valor, List<Integer> datos, AccesoDatos monitor) {
        this.valor = valor;
        this.datos = datos;
        this.monitorAccessoDatos = monitor;
    }

    public void run() {
        this.monitorAccessoDatos.beginWrite();
        this.datos.add(this.valor);
        this.monitorAccessoDatos.endWrite();
    }

}