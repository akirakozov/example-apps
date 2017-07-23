package ru.akirakozov.philosophers;


import java.util.ArrayList;
import java.util.List;

/**
 * @author akirakozov
 */
public class Dinner {
    public static void main(String[] args) throws InterruptedException {
        int philosophersCount = 5;
        List<Fork> forks = new ArrayList<>(philosophersCount);
        List<Philosopher> philosophers = new ArrayList<>(philosophersCount);
        for (int i = 0; i < philosophersCount; i++) {
            forks.add(new Fork(i));
        }
        for (int i = 0; i < philosophersCount; i++) {
            philosophers.add(new Philosopher(i, forks.get(i), forks.get((i + 1) % philosophersCount)));
        }

        philosophers.forEach(Philosopher::start);
        Thread.currentThread().join();
    }
}
