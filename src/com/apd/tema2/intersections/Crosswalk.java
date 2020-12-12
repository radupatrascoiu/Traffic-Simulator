package com.apd.tema2.intersections;

import com.apd.tema2.Main;
import com.apd.tema2.entities.Car;
import com.apd.tema2.entities.Intersection;
import java.util.concurrent.BrokenBarrierException;

public class Crosswalk implements Intersection {
    @Override
    public void handleCar(Car car) throws InterruptedException, BrokenBarrierException {
        String color;
        while (!Main.pedestrians.isFinished()) {
            synchronized (this) {
                color = Main.pedestrians.isPass() ? "red" : "green";
                if (car.getColor() == null || !car.getColor().equals(color)) {
                    car.setColor(color);
                    System.out.println("Car " + car.getId() + " has now " + car.getColor() + " light");
                }
            }
        }
    }
}
