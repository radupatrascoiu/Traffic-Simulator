package com.apd.tema1.factory;

import com.apd.tema1.entities.Intersection;
import com.apd.tema1.entities.RoundaboutIntersection;
import com.apd.tema1.utils.Constants;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

public class IntersectionFactory {
    private static Map<String, Intersection> cache = new HashMap<>();

    static {
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


        // officials passing randomly (T time to exit the intersection) - listeners


        // tramway overtake of cars - randomly spawned tram - race condition - listeners


        // a car breaks randomly - remaining a lane for 2 ways - listeners


        // a car breaks randomly - slowed traffic on multiple lanes - listeners


    }

    public static Intersection getIntersection(String handlerType) {
        return cache.get(handlerType);
    }

}
