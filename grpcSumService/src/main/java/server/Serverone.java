package server;

import client.RepeatedSum;
import client.SimpleSum;
import client.StreamSum;
import io.grpc.Server;
import io.grpc.ServerBuilder;

/**
 * The big daug Server class that implements all of the different services.
 */
public class Serverone {
    /**
     * Main method for the server.
     */
    public static void main(String[] args) {
        try{
            Server server = ServerBuilder.forPort(6789)
                    .addService(new SimpleSum())
                    .addService(new RepeatedSum())
                    .addService(new StreamSum())
                    .build();

            server.start();
            server.awaitTermination();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
