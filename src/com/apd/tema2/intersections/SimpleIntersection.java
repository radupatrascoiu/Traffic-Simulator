package com.apd.tema2.intersections;

import com.apd.tema2.entities.Car;
import com.apd.tema2.entities.Intersection;

import static java.lang.Thread.sleep;

public class SimpleIntersection implements Intersection {

    @Override
    public void handle(Car car) throws InterruptedException {
        System.out.println("Car " + car.getId() + " has reached the semaphore, now waiting...");
        sleep(car.getWaitingTime());
        System.out.println("Car " + car.getId() + " has waited enough, now driving...");
    }
}
