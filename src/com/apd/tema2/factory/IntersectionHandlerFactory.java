package com.apd.tema2.factory;

import com.apd.tema2.Main;
import com.apd.tema2.entities.*;

import java.util.concurrent.BrokenBarrierException;

import static java.lang.Thread.sleep;

/**
 * Clasa Factory ce returneaza implementari ale InterfaceHandler sub forma unor
 * clase anonime.
 */
public class IntersectionHandlerFactory {

    public static IntersectionHandler getHandler(String handlerType) {
        return switch (handlerType) {
            case "simple_semaphore", "simple_n_roundabout", "simple_strict_1_car_roundabout", "simple_strict_x_car_roundabout", "crosswalk", "simple_maintenance", "railroad" -> car -> {
                Intersection intersection = Main.intersection;
                intersection.handle(car);
            };
            case "simple_max_x_car_roundabout", "priority_intersection" -> car -> {
                Intersection intersection = Main.intersection;

                try {
                    sleep(car.getWaitingTime());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } // NU MODIFICATI

                intersection.handle(car);
            };
            case "complex_maintenance" -> new IntersectionHandler() {
                @Override
                public void handle(Car car) {
                    
                }
            };
            default -> null;
        };
    }
}
