package com.apd.tema1.entities;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Set;

public interface ReaderHandler {
    void handle(final String handlerType, final BufferedReader br) throws IOException;
}
