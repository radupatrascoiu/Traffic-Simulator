package com.apd.tema1.io;

import com.apd.tema1.entities.ReaderHandler;
import com.apd.tema1.factory.CarFactory;
import com.apd.tema1.factory.ReaderHandlerFactory;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

public class Reader {
    private BufferedReader br;

    private static Reader instance;

    private Reader(final String filePath) {
        try {
            File file = new File(filePath);
            br = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Reader getInstance(final String filePath) {
        if(instance == null) {
            instance = new Reader(filePath);
        }

        return instance;
    }

    public Set<Thread> getCarsFromInput() {
        Set<Thread> cars = new HashSet<>();

        try {
            String handlerType = br.readLine();
            ReaderHandler readerHandler = ReaderHandlerFactory.getHandler(handlerType);

            readerHandler.handle(handlerType, br);

            br.lines().forEach(line -> cars.add(new Thread(CarFactory.getCar(handlerType, line.split(" ")))));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return cars;
    }


}
