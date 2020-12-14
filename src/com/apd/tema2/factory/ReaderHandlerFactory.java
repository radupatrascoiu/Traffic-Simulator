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
        // simple semaphore intersection
        // max random N cars roundabout (s time to exit each of them)
        // roundabout with exactly one car from each lane simultaneously
        // roundabout with exactly X cars from each lane simultaneously
        // roundabout with at most X cars from each lane simultaneously
        // entering a road without any priority
        // crosswalk activated on at least a number of people (s time to finish all of them)
        // road in maintenance - 1 lane 2 ways, X cars at a time
        // road in maintenance - N lanes 2 ways, X cars at a time
        // railroad blockage for T seconds for all the cars
        // unmarked intersection
        // cars racing
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
                PriorityIntersection.build(Integer.parseInt(line[1]), Integer.parseInt(line[0]));
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

            };
            default -> null;
        };
    }

}
