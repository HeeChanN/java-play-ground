package ex3;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BoundedBuffer {
    private final Queue<Integer> buffer = new LinkedList<>();
    private final int capacity;
    private final Lock lock = new ReentrantLock();
    private final Condition notFull = lock.newCondition();
    private final Condition notEmpty = lock.newCondition();

    public BoundedBuffer(int capacity) {
        this.capacity = capacity;
    }

    public void produce(Integer item) throws InterruptedException {
        lock.lock();
        try {
            while (buffer.size() == capacity) {
                System.out.println("Buffer is full");
                notFull.await();
            }
            buffer.add(item);
            System.out.println("Produce: " + item);
            notEmpty.signal();
        } finally {
            lock.unlock();
        }
    }

    public Integer consume() throws InterruptedException {
        lock.lock();
        try {
            while (buffer.isEmpty()) {
                System.out.println("Buffer is empty");
                notEmpty.await();
            }
            Integer item = buffer.poll();
            System.out.println("Consume: " + item);
            notFull.signal();
            return item;
        } finally {
            lock.unlock();
        }
    }
}
