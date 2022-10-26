package principal;

public class Consumidor implements Runnable {
    int inicio;
    int fin;
    int inc;
    Recurso recurso;
    SincronizadorPuro syncProductor;
    SincronizadorPuro syncConsumidor;

    public Consumidor(int inicio, int fin, int inc, Recurso recurso, SincronizadorPuro syncProductor, SincronizadorPuro syncConsumidor) {
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
            syncProductor.esperar();

            System.out.println("consume " + recurso.getX());

            if (fin != i) {
                syncConsumidor.avisar();
            }

            i += inc;
        }
    }
}
