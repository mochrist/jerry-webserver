package de.mochrist;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        Server server = new Server();
        try {
            server.start(4221);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }
}
