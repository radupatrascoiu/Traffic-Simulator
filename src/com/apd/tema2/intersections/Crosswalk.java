package com.apd.tema2.intersections;

import com.apd.tema2.Main;
import com.apd.tema2.entities.Car;
import com.apd.tema2.entities.Intersection;
import java.util.concurrent.BrokenBarrierException;

public class Crosswalk implements Intersection {
    @Override
    public void handle(Car car) throws InterruptedException, BrokenBarrierException {
        String color = "red";
        while (!Main.pedestrians.isFinished()) {
            synchronized (this) {
                color = Main.pedestrians.isPass() ? "red" : "green";
                // daca este prima trecere sau culoarea nu a fost schimbata
                if (car.getColor() == null || !car.getColor().equals(color)) {
                    // setam noua culoare
                    car.setColor(color);
                    System.out.println("Car " + car.getId() + " has now " + car.getColor() + " light");
                }
            }
        }
    }
}
