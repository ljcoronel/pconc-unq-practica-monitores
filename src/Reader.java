import java.util.List;

public class Reader extends Thread {

    private List<Integer> datos;
    private AccesoDatos monitorAccesoDatos;

    public Reader(List<Integer> datos, AccesoDatos monitor) {
        this.datos = datos;
        this.monitorAccesoDatos = monitor;
    }

    public void run() {
        this.monitorAccesoDatos.beginRead();
        this.datos.forEach(System.out::println);
        this.monitorAccesoDatos.endRead();
    }

}