package com.apd.tema1.factory;

import com.apd.tema1.Main;
import com.apd.tema1.entities.ReaderHandler;
import com.apd.tema1.entities.RoundaboutIntersection;

import java.io.BufferedReader;
import java.io.IOException;

public class ReaderHandlerFactory {

    public static ReaderHandler getHandler(String handlerType) {
        switch (handlerType) {
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


            default:
                return null;
        }
    }

}
