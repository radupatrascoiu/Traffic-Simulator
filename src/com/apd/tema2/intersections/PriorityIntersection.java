package com.apd.tema2.intersections;
import com.apd.tema2.entities.Car;
import com.apd.tema2.entities.Intersection;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.LinkedBlockingDeque;

import static java.lang.Thread.sleep;

public class PriorityIntersection implements Intersection {
    private static int noCarsWithPriority;
    private static int noCarWithoutPriority;
    private static BlockingDeque<Car> carBlockingDeque;

    public static void build(int noCarWithoutPriority, int noCarsWithPriority) {
        PriorityIntersection.noCarWithoutPriority = noCarWithoutPriority;
        PriorityIntersection.noCarsWithPriority = noCarsWithPriority;
        carBlockingDeque = new LinkedBlockingDeque<>();
    }

    @Override
    public void handle(Car car) throws InterruptedException, BrokenBarrierException {

        System.out.println(car.getId());

        if (car.getPriority() > 1) {
            synchronized (this) {
                sleep(car.getWaitingTime());
                System.out.println("Car " + car.getId() + " with high priority has entered the intersection");
                sleep(2000);
                System.out.println("Car " + car.getId() + " with high priority has exited the intersection");
            }
        } else {
            sleep(car.getWaitingTime());
            carBlockingDeque.put(car);
            System.out.println("Car " + car.getId() + " with low priority is trying to enter the intersection...");
            while (!carBlockingDeque.isEmpty()) {
                System.out.println("Car " + carBlockingDeque.getFirst().getId() + " with low priority has entered the intersection");
                carBlockingDeque.take();
            }
        }
    }
}
