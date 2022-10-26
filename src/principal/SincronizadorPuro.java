package principal;

import java.util.concurrent.Semaphore;

public class SincronizadorPuro {
    Semaphore sync;

    public SincronizadorPuro(int max) {
        sync = new Semaphore(max); // crea un semáforo en (max, max)

        // lo dejamos como (0, max) para que el primer avisar() no lo libere
        for (int i = 0; i < max; i++) {
            try {
                sync.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /** Pseudo-implementación conceptual
     * WAIT / P / ESPERAR / ACQUIRE
     * {
     *         cont--;
     *         if (cont < 0)
     *         {
     *             while (true)
     *             {
     *                 try
     *                 {
     *                     wait();
     *                     break;
     *                 }
     *                 catch (InterruptedException e)
     *                 {
     *                     if (cont >= 0)
     *                         break;
     *                     else
     *                         continue;
     *                 }
     *             }
     *         }
     * }
     */

    public void esperar() {
        try {
            sync.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /** Pseudo-implementación conceptual
     * SIGNAL / V / AVISAR / RELEASE
     * {
     *         cont++;
     *         if (cont <= 0)
     *             notify();
     *         if (cont > 1)
     *             cont = 1;
     *
     * }
     */

    public void avisar() {
        sync.release();
    }

    public int getContador() {
        return sync.availablePermits();
    }

}

