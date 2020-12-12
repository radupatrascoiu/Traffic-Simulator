package com.apd.tema2.intersections;

import com.apd.tema2.entities.Car;
import com.apd.tema2.entities.Intersection;

import java.util.concurrent.BrokenBarrierException;

public class PriorityIntersection implements Intersection {
    @Override
    public void handleCar(Car car) throws InterruptedException, BrokenBarrierException {
        System.out.println("Car " + car.getId() + " from side number " + car.getStartDirection() +  " has passed the bottleneck");
        System.out.println("Car " + car.getId() + " from side number " + car.getStartDirection() +  " has reached the bottleneck");
    }
}
