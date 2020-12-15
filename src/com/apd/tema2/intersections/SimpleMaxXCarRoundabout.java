package com.apd.tema2.intersections;

import com.apd.tema2.Main;
import com.apd.tema2.entities.Car;
import com.apd.tema2.entities.Intersection;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.Semaphore;

import static java.lang.Thread.sleep;

public class SimpleMaxXCarRoundabout implements Intersection {
    private static int timeToWait;
    private static List<Semaphore> semaphores;

    public static void build(int noMaxCars, int timeToWait, int noSemaphores) {
        SimpleMaxXCarRoundabout.timeToWait = timeToWait;
        semaphores = new ArrayList<>(noSemaphores);
        for (int i = 0; i < noSemaphores; i++) {
            semaphores.add(new Semaphore(noMaxCars));
        }
    }

    @Override
    public void handle(Car car) throws InterruptedException, BrokenBarrierException {
        System.out.println("Car " + car.getId() +
                " has reached the roundabout from lane " +
                car.getStartDirection());

        // pentru fiecare directie
        semaphores.get(car.getStartDirection()).acquire();

        System.out.println("Car " + car.getId() +
                " has entered the roundabout from lane " +
                car.getStartDirection());

        // fiecare masina sta un timp in sensul giratoriu
        sleep(timeToWait);

        System.out.println("Car " + car.getId() +
                " has exited the roundabout after " + (timeToWait / 1000) +
                " seconds");

        // dupa ce a iesit o masina se da release
        semaphores.get(car.getStartDirection()).release();
    }
}
