package com.apd.tema2.factory;

import com.apd.tema2.Main;
import com.apd.tema2.entities.Pedestrians;
import com.apd.tema2.entities.ReaderHandler;
import com.apd.tema2.intersections.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.Semaphore;

/**
 * Returneaza sub forma unor clase anonime implementari pentru metoda de citire din fisier.
 */
public class ReaderHandlerFactory {

    public static ReaderHandler getHandler(String handlerType) {
        return switch (handlerType) {
            case "simple_semaphore" -> (handlerType12, br) -> {
                Main.intersection = IntersectionFactory.getIntersection("simpleIntersection");
            };
            case "simple_n_roundabout" -> (handlerType1, br) -> {
                String[] line = br.readLine().split(" ");
                Main.intersection = IntersectionFactory.getIntersection("simpleNRoundabout");
                SimpleNRoundabout.build(Integer.parseInt(line[1]), new Semaphore(Integer.parseInt(line[0])));
            };
            case "simple_strict_1_car_roundabout" -> (handlerType13, br) -> {
                String[] line = br.readLine().split(" ");
                Main.intersection = IntersectionFactory.getIntersection("simpleStrict1CarRoundabout");
                SimpleStrict1CarRoundabout.build(Integer.parseInt(line[1]), Integer.parseInt(line[0]));
            };
            case "simple_strict_x_car_roundabout" -> (handlerType14, br) -> {
                String[] line = br.readLine().split(" ");
                Main.intersection = IntersectionFactory.getIntersection("simpleStrictXCarRoundabout");
                SimpleStrictXCarRoundabout.build(Integer.parseInt(line[2]), Integer.parseInt(line[1]), Integer.parseInt(line[0]));
            };
            case "simple_max_x_car_roundabout"-> (handlerType14, br) -> {
                String[] line = br.readLine().split(" ");
                Main.intersection = IntersectionFactory.getIntersection("simpleMaxXCarRoundabout");
                SimpleMaxXCarRoundabout.build(Integer.parseInt(line[2]), Integer.parseInt(line[1]), Integer.parseInt(line[0]));
            };
            case "priority_intersection" -> (ReaderHandler) (handlerType19, br) -> {
                String[] line = br.readLine().split(" ");
                Main.intersection = IntersectionFactory.getIntersection("priorityIntersection");
                PriorityIntersection.build();
            };
            case "crosswalk" -> (handlerType15, br) -> {
                String[] line = br.readLine().split(" ");
                Main.intersection = IntersectionFactory.getIntersection("crosswalk");
                Main.pedestrians = new Pedestrians(Integer.parseInt(line[0]), Integer.parseInt(line[1]));
            };
            case "simple_maintenance" -> (handlerType16, br) -> {
                String[] line = br.readLine().split(" ");
                Main.intersection = IntersectionFactory.getIntersection("simpleMaintenance");
                SimpleMaintenance.build(Integer.parseInt(line[0]));
            };
            case "complex_maintenance" -> (handlerType17, br) -> {

            };
            case "railroad" -> (handlerType18, br) -> {
                Main.intersection = IntersectionFactory.getIntersection("railRoad");
                RailRoad.build();
            };
            default -> null;
        };
    }

}
