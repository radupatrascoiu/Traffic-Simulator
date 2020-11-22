package com.apd.tema1.entities;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

public abstract class Intersection {
    public CyclicBarrier cyclicBarrier = null;
    public Semaphore semaphore = null;
}
