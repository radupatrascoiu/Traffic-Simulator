package com.apd.tema2.intersections;

import com.apd.tema2.Main;
import com.apd.tema2.entities.Car;
import com.apd.tema2.entities.Intersection;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.LinkedBlockingDeque;

public class RailRoad implements Intersection {
    private static CyclicBarrier barrier;
    private static BlockingDeque<Car> carQueueLane0;
    private static BlockingDeque<Car> carQueueLane1;
    volatile boolean ok = false;

    public static void build() {
        barrier = new CyclicBarrier(Main.carsNo);
        carQueueLane0 = new LinkedBlockingDeque<>();
        carQueueLane1 = new LinkedBlockingDeque<>();
    }
    @Override
    public void handle(Car car) throws InterruptedException, BrokenBarrierException {
        synchronized (this) {
            System.out.println("Car " + car.getId() + " from side number " + car.getStartDirection() + " has stopped by the railroad");
            if (car.getStartDirection() == 0) {
                carQueueLane0.put(car);
            } else {
                carQueueLane1.put(car);
            }
        }

        barrier.await();

        if (!ok) {
            System.out.println("The train has passed, cars can now proceed");
            ok = true;
        }

        synchronized (this) {
            if (!carQueueLane0.isEmpty()) {
                System.out.println("Car " + carQueueLane0.getFirst().getId() + " from side number " + carQueueLane0.take().getStartDirection() + " has started driving");
            }

            if (!carQueueLane1.isEmpty()) {
                System.out.println("Car " + carQueueLane1.getFirst().getId() + " from side number " + carQueueLane1.take().getStartDirection() + " has started driving");
            }
        }
    }
}
