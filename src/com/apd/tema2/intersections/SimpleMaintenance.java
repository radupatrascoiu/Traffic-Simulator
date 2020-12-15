package com.apd.tema2.intersections;
import com.apd.tema2.entities.Car;
import com.apd.tema2.entities.Intersection;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

public class SimpleMaintenance implements Intersection {
    private static Semaphore semaphoreForCarsWithPriority;
    private static Semaphore semaphoreForCarsWithoutPriority;
    private static CyclicBarrier barrierForPriority;
    public static void build(int carsNo) {
        semaphoreForCarsWithPriority =  new Semaphore(carsNo);
        semaphoreForCarsWithoutPriority = new Semaphore(0);
        barrierForPriority = new CyclicBarrier(carsNo);
    }

    @Override
    public void handle(Car car) throws InterruptedException, BrokenBarrierException {
        System.out.println("Car " + car.getId() + " from side number " +
                car.getStartDirection() + " has reached the bottleneck");

        if (car.getStartDirection() == 0) {
            semaphoreForCarsWithPriority.acquire();
            // ca toate masinile de pe sensul de mers sa ajunga aici, pentru ca
            // daca nu as avea bariera, un singur release pe semaforul 2 ar face o
            // masina de pe sensul 1 sa plece, astfel nu s-ar mai respecta cerinta
            barrierForPriority.await();
            System.out.println("Car " + car.getId() + " from side number " +
                    car.getStartDirection() + " has passed the bottleneck");
            semaphoreForCarsWithoutPriority.release();
        } else {
            semaphoreForCarsWithoutPriority.acquire();
            barrierForPriority.await();
            System.out.println("Car " + car.getId() + " from side number " +
                    car.getStartDirection() + " has passed the bottleneck");
            semaphoreForCarsWithPriority.release();
        }
    }
}
