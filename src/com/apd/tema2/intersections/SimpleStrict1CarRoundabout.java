package com.apd.tema2.intersections;

import com.apd.tema2.entities.Car;
import com.apd.tema2.entities.Intersection;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

import static java.lang.Thread.sleep;

public class SimpleStrict1CarRoundabout implements Intersection {
    private static int timeToWait;
    private static List<Semaphore> semaphores;

    public static void build(int timeToWait, int noSemapahores) {
        SimpleStrict1CarRoundabout.timeToWait = timeToWait;
        semaphores = new ArrayList<>(noSemapahores);
        for (int i = 0; i < noSemapahores; i++) {
            // este permisa o singura masina per directie
            semaphores.add(new Semaphore(1));
        }
    }

    @Override
    public void handleCar(Car car) throws InterruptedException {
        System.out.println("Car " + car.getId() + " has reached the roundabout");
        semaphores.get(car.getStartDirection()).acquire();
        System.out.println("Car " + car.getId() + " has entered the roundabout from lane " +
                car.getStartDirection());
        sleep(timeToWait);
        System.out.println("Car " + car.getId() + " has exited the roundabout after " +
                (timeToWait / 1000) + " seconds");
        semaphores.get(car.getStartDirection()).release();
    }
}
