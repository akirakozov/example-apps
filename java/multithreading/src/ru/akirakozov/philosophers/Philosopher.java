package ru.akirakozov.philosophers;

/**
 * @author akirakozov
 */
public class Philosopher implements Runnable {
    private final Fork leftFork;
    private final Fork rightFork;
    private final int number;

    public Philosopher(int number, Fork leftFork, Fork rightFork) {
        this.leftFork = leftFork;
        this.rightFork = rightFork;
        this.number = number;
    }

    @Override
    public void run() {
        while (true) {
            if (leftFork.tryAcquire()) {
                if (rightFork.tryAcquire()) {
                    System.out.println("Philosopher#" + number + " starts eating, forks: "
                            + leftFork.getName() + " " + rightFork.getName());
                    sleepSafely();
                    System.out.println("Philosopher#" + number + " finishes eating");
                    rightFork.release();
                }
                leftFork.release();
            }
        }
    }

    private void sleepSafely() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void start() {
        new Thread(this).start();
    }
}
