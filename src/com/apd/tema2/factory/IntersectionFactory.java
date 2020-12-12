package com.apd.tema2.factory;

import com.apd.tema2.entities.Intersection;
import com.apd.tema2.intersections.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Prototype Factory: va puteti crea cate o instanta din fiecare tip de implementare de Intersection.
 */
public class IntersectionFactory {
    private static final Map<String, Intersection> cache = new HashMap<>();

    static {
        cache.put("simpleIntersection", new SimpleIntersection() {});
        cache.put("simpleNRoundabout", new SimpleNRoundabout() {});
        cache.put("simpleStrict1CarRoundabout", new SimpleStrict1CarRoundabout() {});
        cache.put("simpleStrictXCarRoundabout", new SimpleStrictXCarRoundabout() {});
        cache.put("simpleMaxXCarRoundabout", new SimpleMaxXCarRoundabout() {});
        cache.put("priorityIntersection", new PriorityIntersection() {});
        cache.put("crosswalk", new Crosswalk() {});
        cache.put("simpleMaintenance", new SimpleMaintenance() {});
        cache.put("complexMaintenance", new ComplexMaintenance() {});
        cache.put("railRoad", new RailRoad() {});
    }

    public static Intersection getIntersection(String handlerType) {
        return cache.get(handlerType);
    }

}
