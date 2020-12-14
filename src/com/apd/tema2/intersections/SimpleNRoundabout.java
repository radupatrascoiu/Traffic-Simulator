package com.apd.tema2.intersections;

import com.apd.tema2.entities.Car;
import com.apd.tema2.entities.Intersection;

import java.util.concurrent.Semaphore;

import static java.lang.Thread.sleep;

public class SimpleNRoundabout implements Intersection {
    private static int timeToWait;
    private static Semaphore semaphore;

    public static void build(int timeToWait, Semaphore semaphore) {
        SimpleNRoundabout.timeToWait = timeToWait;
        SimpleNRoundabout.semaphore = semaphore;
    }

    @Override
    public void handle(Car car) throws InterruptedException {
        System.out.println("Car " + car.getId() + " has reached the roundabout, now waiting...");
        semaphore.acquire();
        System.out.println("Car " + car.getId() + " has entered the roundabout");
        sleep(timeToWait);
        System.out.println("Car " + car.getId() + " has exited the roundabout after " +
                (timeToWait / 1000) + " seconds");
        semaphore.release();
    }
}
