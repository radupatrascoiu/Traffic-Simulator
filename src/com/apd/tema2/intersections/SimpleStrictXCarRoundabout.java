package com.apd.tema2.intersections;

import com.apd.tema2.Main;
import com.apd.tema2.entities.Car;
import com.apd.tema2.entities.Intersection;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

import static java.lang.Thread.sleep;

public class SimpleStrictXCarRoundabout implements Intersection {
    private static int timeToWait;
    private static List<Semaphore> semaphores;
    private static CyclicBarrier startBarrier;
    private static CyclicBarrier enterInRoundaboutBarrier;
    private static CyclicBarrier exitBarrier;

    public static void build(int noMaxCars, int timeToWait, int noSemaphores) {
        startBarrier = new CyclicBarrier(Main.carsNo);
        enterInRoundaboutBarrier = new CyclicBarrier(noMaxCars * noSemaphores);
        exitBarrier = new CyclicBarrier(noMaxCars * noSemaphores);

        SimpleStrictXCarRoundabout.timeToWait = timeToWait;

        semaphores = new ArrayList<>(noSemaphores);
        for (int i = 0; i < noSemaphores; i++) {
            semaphores.add(new Semaphore(noMaxCars));
        }

    }

    @Override
    public void handle(Car car) throws InterruptedException, BrokenBarrierException {
        System.out.println("Car " + car.getId() + " has reached the roundabout, now waiting...");
        // asteptam sa ajunga toata masinile dintr-o runda
        startBarrier.await();

        // pentru fiecare directie
        semaphores.get(car.getStartDirection()).acquire();

        System.out.println("Car " + car.getId() + " was selected to enter the roundabout from lane " +
                car.getStartDirection());

        // asteptam sa iasa toate masinile dintr-o runda
        exitBarrier.await();

        System.out.println("Car " + car.getId() + " has entered the roundabout from lane " +
                car.getStartDirection());

        // asteptam sa intre toate in sens
        enterInRoundaboutBarrier.await();

        // fiecare masina sta un timp in sensul giratoriu
        sleep(timeToWait);

        System.out.println("Car " + car.getId() + " has exited the roundabout after " + (timeToWait / 1000) +
                        " seconds");
        // dupa ce a iesit o masina se da release
        semaphores.get(car.getStartDirection()).release();
    }
}
