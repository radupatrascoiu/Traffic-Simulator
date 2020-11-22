package com.apd.tema1.factory;

import com.apd.tema1.entities.Car;

public class CarFactory {

    public static Car getCar(String handlerType, String[] args) {
        switch(args.length) {
            case 3:
                return new Car(Integer.parseInt(args[0]),
                        Integer.parseInt(args[1]),
                        Integer.parseInt(args[2]),
                        IntersectionHandlerFactory.getHandler(handlerType));
            case 4:
                return new Car(Integer.parseInt(args[0]),
                        Integer.parseInt(args[1]),
                        Integer.parseInt(args[2]),
                        IntersectionHandlerFactory.getHandler(handlerType),
                        Integer.parseInt(args[3]));
            default:
                return null;
        }
    }

}
