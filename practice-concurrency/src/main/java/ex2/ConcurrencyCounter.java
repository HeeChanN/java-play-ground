package ex2;

public class ConcurrencyCounter {

    private static int count = 0;
    private final Object lock = new Object();
    private int instanceCount = 0;

    public static synchronized void increment() {
        count++;
    }

    public synchronized void incrementInstanceCount() {
        instanceCount++;
    }

    public void lockIncrement(){
        synchronized (lock) {
            instanceCount++;
        }
    }

    public int getCount() {
        return count;
    }
}
