package com.apd.tema1.factory;

import com.apd.tema1.Main;
import com.apd.tema1.entities.Car;
import com.apd.tema1.entities.Intersection;
import com.apd.tema1.entities.IntersectionHandler;
import com.apd.tema1.utils.Constants;

import java.util.concurrent.BrokenBarrierException;

public class IntersectionHandlerFactory {

    public static IntersectionHandler getHandler(String handlerType) {
        switch (handlerType) {
            // simple semaphore intersection


            // max random N cars roundabout (s time to exit each of them)
            

            // roundabout with exactly one car from each lane simultaneously
            

            // roundabout with exactly 3 cars from each lane simultaneously
            

            // roundabout with at most 5 cars from each lane simultaneously


            // big roundabout composed of 5 smaller roundabouts with max number of cars (s[x] time each car to exit a specific roundabout)


            // entering a road without any priority


            // crosswalk activated on at least a number of people (s time to finish all of them)


            // road in maintenance - 1 lane 2 ways, X cars at a time


            // road in maintenance - N lanes 2 ways, X cars at a time


            // railroad blockage for s seconds for all the cars


            // officials passing randomly (s time to exit the intersection) - doable with listeners


            // tramway overtake of cars - randomly spawned tram - race condition


            // a car breaks randomly - remaining a lane for 2 ways


            // a car breaks randomly - slowed traffic on multiple lanes


            // unmarked intersection with 4 cars (guaranteed no race condition)


            default:
                return null;
        }
    }

}
