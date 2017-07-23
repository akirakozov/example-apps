package ru.akirakozov.philosophers;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author akirakozov
 */
public class Fork {
    private final ReentrantLock lock;
    private final int number;

    public Fork(int number) {
        this.lock = new ReentrantLock();
        this.number = number;
    }

    public boolean tryAcquire() {
        return lock.tryLock();
    }

    public void release() {
        lock.unlock();
    }

    public String getName() {
        return "Fork#" + number;
    }
}
