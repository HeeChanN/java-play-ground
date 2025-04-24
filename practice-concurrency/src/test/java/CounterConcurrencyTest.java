import ex2.ConcurrencyCounter;
import ex2.Counter;
import ex4.FastCounter;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


public class CounterConcurrencyTest {

    @Test
    void UnExpectedValueTest() throws InterruptedException {
        Counter counter = new Counter();

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) counter.increment();
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) counter.increment();
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("Final count: " + counter.getCount());

        assertThat(counter.getCount()).isNotEqualTo(20000);
    }

    @Test
    void synchronizedTest() throws InterruptedException {
        ConcurrencyCounter concurrencyCounter = new ConcurrencyCounter();

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) ConcurrencyCounter.increment();
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) ConcurrencyCounter.increment();
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("Final count: " + concurrencyCounter.getCount());

        assertThat(concurrencyCounter.getCount()).isEqualTo(20000);
    }

    //Atomic(CAS)가 Synchronized 보다 빠르다
    @Test
    void compareTest() throws InterruptedException {
        System.out.println("Starting Synchronized Test...");
        FastCounter fastCounter = new FastCounter();
        long syncStart = System.nanoTime();
        runTest(fastCounter::increment);
        long syncEnd = System.nanoTime();
        System.out.printf("Synchronized Time: %d ms%n", (syncEnd - syncStart) / 1_000_000);

        System.out.println("Starting Atomic Test...");
        ConcurrencyCounter concurrencyCounter = new ConcurrencyCounter();
        long atomicStart = System.nanoTime();
        runTest(concurrencyCounter::incrementInstanceCount);
        long atomicEnd = System.nanoTime();
        System.out.printf("Atomic Time: %d ms%n", (atomicEnd - atomicStart) / 1_000_000);
    }

    private void runTest(Runnable incrementFunction) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 1000000; i++) {
                incrementFunction.run();
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 1000000; i++) {
                incrementFunction.run();
            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();
    }
}
