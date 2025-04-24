import org.junit.jupiter.api.Test;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

class ConcurrentHashMapTest {

    //atomic이 compute 연산보다 빠르다.
    @Test
    void compareSpeed()throws InterruptedException{
        System.out.println("compute test");
        long computeTime = runComputeBenchmark();
        System.out.println("Elapsed time: " + computeTime + " ms\n");

        System.out.println("AtomicInteger test");
        long atomicTime = runAtomicBenchmark();
        System.out.println("Elapsed time: " + atomicTime + " ms");
    }

     private long runComputeBenchmark() throws InterruptedException {
         ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<>();
         ExecutorService executor = Executors.newFixedThreadPool(5);

         long start = System.currentTimeMillis();

         Runnable task = () -> {
             for (int i = 0; i < 1000000; i++) {
                 map.compute("apple", (k, v) -> v == null ? 1 : v + 1);
             }
         };

         for (int i = 0; i < 5; i++) {
             executor.submit(task);
         }

         executor.shutdown();
         executor.awaitTermination(1, TimeUnit.MINUTES);

         long end = System.currentTimeMillis();
         System.out.println("Final count: " + map.get("apple"));
         return end - start;
     }

    private static long runAtomicBenchmark() throws InterruptedException {
        ConcurrentHashMap<String, AtomicInteger> map = new ConcurrentHashMap<>();
        ExecutorService executor = Executors.newFixedThreadPool(5);

        long start = System.currentTimeMillis();

        Runnable task = () -> {
            for (int i = 0; i < 1000000; i++) {
                map.computeIfAbsent("apple", k -> new AtomicInteger(0)).incrementAndGet();
            }
        };

        for (int i = 0; i < 5; i++) {
            executor.submit(task);
        }

        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.MINUTES);

        long end = System.currentTimeMillis();
        System.out.println("Final count: " + map.get("apple").get());
        return end - start;
    }
}
