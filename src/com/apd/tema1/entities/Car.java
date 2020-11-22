package com.apd.tema1.entities;


public class Car implements Runnable {
    private final int id;
    private final int startDirection;
    private final int endDirection;
    private final int priority;
    private final IntersectionHandler intersectionHandler;

    public Car(final int id, final int startDirection, final int endDirection, IntersectionHandler intersectionHandler) {
        this(id, startDirection, endDirection, intersectionHandler, 1);
    }

    public Car(final int id, final int startDirection, final int endDirection, IntersectionHandler intersectionHandler, final int priority) {
        this.id = id;
        this.startDirection = startDirection;
        this.endDirection = endDirection;
        this.intersectionHandler = intersectionHandler;
        this.priority = priority;
    }

    @Override
    public void run() {
        intersectionHandler.handle(this);
    }

    public int getId() {
        return id;
    }

    public int getStartDirection() {
        return startDirection;
    }

    public int getEndDirection() {
        return endDirection;
    }

    public int getPriority() {
        return priority;
    }
}
