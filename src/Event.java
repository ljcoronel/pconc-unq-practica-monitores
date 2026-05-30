public class Event {

    private int ultimoPublish;

    public synchronized void publish() {
        this.ultimoPublish++;
        notifyAll();
    }

    public synchronized void suscribe() {
        int miPublish = this.ultimoPublish + 1;
        while (this.ultimoPublish < miPublish) try { wait(); } catch(InterruptedException e) { return; }
    }

}