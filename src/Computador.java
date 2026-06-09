public class Computador {

    Future async(Object x) {
        Promise promise = new Promise();
        new Thread(() -> {
            try {
                // computo costo en funcion de x que genera un resultado
                Object resultado = x;
                Thread.sleep(10000);
                promise.set(resultado);
            } catch(Exception e) {
                promise.set(e);
            }
        }).start();
        return promise;
    }

}