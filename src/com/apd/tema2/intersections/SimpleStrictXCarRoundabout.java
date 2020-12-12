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
    private final CyclicBarrier startBarrier = new CyclicBarrier(Main.carsNo);
    private static CyclicBarrier endBarrier;
    private static Semaphore generalSemaphore;

    public static void build(int noMaxCars, int timeToWait, int noSemaphores) {
        SimpleStrictXCarRoundabout.timeToWait = timeToWait;
        semaphores = new ArrayList<>(noSemaphores);
        for (int i = 0; i < noSemaphores; i++) {
            semaphores.add(new Semaphore(noMaxCars));
        }

        generalSemaphore = new Semaphore(noMaxCars * noSemaphores);
        endBarrier = new CyclicBarrier(noMaxCars * noSemaphores);
    }

    @Override
    public void handleCar(Car car) throws InterruptedException, BrokenBarrierException {
        System.out.println("Car " + car.getId() + " has reached the roundabout, now waiting...");
        // asteptam sa ajunga toata masinile dintr-o runda
        startBarrier.await();

        // pentru fiecare directie
        semaphores.get(car.getStartDirection()).acquire();
        generalSemaphore.acquire();

        System.out.println("Car " + car.getId() + " was selected to enter the roundabout from lane " +
                        car.getStartDirection());

        // asteptam sa intre toate in sens
        endBarrier.await();
        System.out.println("Car " + car.getId() + " has entered the roundabout from lane " +
                car.getStartDirection());

        // fiecare masina sta un timp in sensul giratoriu
        sleep(timeToWait);

        System.out.println("Car " + car.getId() + " has exited the roundabout after " + (timeToWait / 1000) +
                        " seconds");
        // dupa ce a iesit o masina se da release
        semaphores.get(car.getStartDirection()).release();

        // se asteapta iesirea tuturor masinilor din sens
        endBarrier.await();

        // se anunta semaforul ca toate masinile dintr-o runda au terminat
        generalSemaphore.release();
    }
}
