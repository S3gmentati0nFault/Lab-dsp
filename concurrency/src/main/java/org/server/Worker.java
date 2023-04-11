package org.server;

import Data.Tickets;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class Worker implements Runnable{
    private Socket connectionSocket;
    private DataOutputStream outputStream;
    private BufferedReader inputStream;
    private Tickets ticketsRemaining;

    public Worker(Socket socket, Tickets tickets) throws IOException {
        System.out.println("Costruzione Worker");
        connectionSocket = socket;
        outputStream =
                new DataOutputStream(socket.getOutputStream());
        inputStream =
                new BufferedReader(
                        new InputStreamReader(socket.getInputStream()));
        ticketsRemaining = tickets;
    }

    @Override
    public void run() {
        System.out.println("Inizio processing biglietti");
        if(ticketsRemaining.getNumberOfTickets() > 0){
            ticketsRemaining.purchaseTickets();
            try {
                outputStream.writeBytes(ticketsRemaining.getNumberOfTickets() + "\n");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        else{
            System.out.println("Non ci sono piú biglietti!");
            try {
                outputStream.writeBytes("-1");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        try {
            connectionSocket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
