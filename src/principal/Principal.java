package principal;

/**
 *      - El consumidor va a consumir N veces.
 *      - El productor va a producir N veces.
 *      - Siempre lo deben hacer alternativamente.
 *
 *  El recurso de lo que se consume o produce debe ser protegido, y, por tanto, realmente estamos hablando de
 *  comunicación sincronizada, pero sería el de la sincronización pura. Solucionar el problema de comunicación es tan
 *  simple como hacer sincronizados los métodos get / set del atributo a proteger.
 *
 *  El diagrama de precedencia sería:
 *
 *      A—-> B —-> A —-> B —- …..—->A ——> B
 *
 *  Este diagrama se resuelve con 2 semáforos iniciados a 0 con máximo 1. Que es lo que abstraigo en la clase
 *  SincronizacionPura con los métodos: esperar() / avisar() que corresponden al típico wait / signal de los semáforos.
 *
 *  Por ello el algoritmo para A es simplemente:
 *
 *      - Esperará a B excepto si es la primera iteración.
 *      - Se ejecuta el código A, en mi ejemplo pone el contador del for en el recurso compartido.
 *      - Se avisa a B de que ha terminado.
 *
 *  Y análogamente, el algoritmo de B:
 *
 *      - Primero esperará a A siempre.
 *      - Ejecuta el código asociado a B, que en mi caso es imprimir el valor del recurso.
 *      - Se avisa a A excepto en la última iteración.
 */



 public class Principal {

    public static void main(String[] args) {
        Recurso r1 = new Recurso(0);
        SincronizadorPuro syncProductor = new SincronizadorPuro(1);
        SincronizadorPuro syncConsumidor = new SincronizadorPuro(1);

        // produce/consume desde 0 hasta 5000 en pasos de 1.
        Productor p1 = new Productor(0, 5000, 1, r1, syncProductor, syncConsumidor);
        Consumidor c1 = new Consumidor(0, 5000, 1, r1, syncProductor, syncConsumidor);

        Thread h1 = new Thread(p1);
        Thread h2 = new Thread(c1);

        h1.start();
        h2.start();
        try {
            h1.join();
            h2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}

