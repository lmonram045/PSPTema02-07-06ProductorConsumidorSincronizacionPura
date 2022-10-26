package principal;

public class Recurso {
    private int x;

    public Recurso(int x) {
        this.x = x;
    }

    public synchronized void setX(int x) {
        this.x = x;
    }

    public synchronized int getX() {
        return x;
    }
}
