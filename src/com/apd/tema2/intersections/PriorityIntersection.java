package com.apd.tema2.intersections;
import com.apd.tema2.entities.Car;
import com.apd.tema2.entities.Intersection;
import com.apd.tema2.utils.Constants;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicInteger;

import static java.lang.Thread.sleep;

public class PriorityIntersection implements Intersection {
    private static BlockingDeque<Car> carBlockingDeque;
    AtomicInteger priorityCarsNo = new AtomicInteger(0);

    public static void build() {
        carBlockingDeque = new LinkedBlockingDeque<>();
    }

    @Override
    public void handle(Car car) throws InterruptedException, BrokenBarrierException {

        // daca avem masina cu prioritate
        if (car.getPriority() > 1) {
            System.out.println("Car " + car.getId() + " with high priority has entered the intersection");
            // incrementam si marcam faptul ca avem cel putin o masina cu prioritate in intersectie
            priorityCarsNo.incrementAndGet();
            sleep(Constants.PRIORITY_INTERSECTION_PASSING);

            System.out.println("Car " + car.getId() + " with high priority has exited the intersection");
            // decrementam si marcam faptul ca a iesit o masina cu prioritate din intersectie
            priorityCarsNo.decrementAndGet();

            // daca nu mai avem masini cu prioritate in intersectie,
            // iar coada de masini fara prioritate nu este goala
            while (priorityCarsNo.get() == 0 && !carBlockingDeque.isEmpty()) {
                // masinile fara prioritate intra in intersectie
                System.out.println("Car " + carBlockingDeque.getFirst().getId() + " with low priority has entered the intersection");
                carBlockingDeque.take();
            }
        } else { // daca avem masina fara prioritate
            System.out.println("Car " + car.getId() + " with low priority is trying to enter the intersection...");

            // daca nu avem masini cu prioritate in intersectie
            if (priorityCarsNo.get() == 0) {
                System.out.println("Car " + car.getId() + " with low priority has entered the intersection");
            } else { // altfel, asteptam pana se elibereaza intersectia
                    // si adaugam intr-o coada masinile fara prioritate
                carBlockingDeque.put(car);
            }
        }
    }
}
