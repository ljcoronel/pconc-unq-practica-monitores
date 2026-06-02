import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class ProgramaDatos {

    public static void main(String[] args) {
        List<Integer> datos = new ArrayList<>();
        AccesoDatos monitorAccesoDatos = new AccesoDatos();

        for (int i = 0; i < 4; i++) {
            Writer escritor = new Writer(ThreadLocalRandom.current().nextInt(1,10), datos, monitorAccesoDatos);
            escritor.start();
        }
        for (int i = 0; i < 10; i++) {
            Reader lector = new Reader(datos, monitorAccesoDatos);
            lector.start();
        }
    }

}//Consultar cantidad de instancias hechas