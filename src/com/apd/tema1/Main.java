package com.apd.tema1;

import com.apd.tema1.entities.Car;
import com.apd.tema1.entities.Intersection;
import com.apd.tema1.io.Reader;

import java.util.Set;

public class Main {
    public static Intersection intersection;

    public static void main(String[] args) {
        Reader fileReader = Reader.getInstance(args[0]);
        Set<Thread> cars = fileReader.getCarsFromInput();

        for(Thread car : cars) {
            car.start();
        }

        for(Thread car : cars) {
            try {
                car.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
