public class ProgramaPool {

    public static void main(String[] args) {
        ThreadPool pool = new ThreadPool(100, 8);

        for (int i = 0; i < 100; i++) pool.launch();
    }

}