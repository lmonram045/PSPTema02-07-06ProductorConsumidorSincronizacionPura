package principal;

public class Productor implements Runnable {
    int inicio;
    int fin;
    int inc;
    Recurso recurso;
    SincronizadorPuro syncProductor;
    SincronizadorPuro syncConsumidor;

    public Productor(int inicio, int fin, int inc, Recurso recurso, SincronizadorPuro syncProductor, SincronizadorPuro syncConsumidor) {
        this.inicio = inicio;
        this.fin = fin;
        this.inc = inc;
        this.recurso = recurso;
        this.syncProductor = syncProductor;
        this.syncConsumidor = syncConsumidor;
    }

    @Override
    public void run() {
        int i = inicio;

        while (i <= fin) {
            if (inicio != i) {
                syncConsumidor.esperar();
            }

            recurso.setX(i);
            System.out.println("produce " + recurso.getX());

            syncProductor.avisar();

            i += inc;
        }
    }
}
