package Lesson7.Server;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        String address = "127.0.0.1";
        int port = Integer.parseInt(args[0]);
        int magicNumber = 321;
        List<Integer> clients = new ArrayList<>();
        try (ServerSocket server = new ServerSocket(port, 50, InetAddress.getByName(address))) {
            System.out.println("Server started!");
            try (
                    Socket socket = server.accept();
                    DataInputStream input = new DataInputStream(socket.getInputStream());
                    DataOutputStream output = new DataOutputStream(socket.getOutputStream())
            ) {
                String first = input.readUTF();
                String[] connArgs = first.split(" ");
                if(connArgs.length!=0 && connArgs[0].equals(String.valueOf(magicNumber))){
                    clients.add(Integer.parseInt(connArgs[1]));
                }

                String second = input.readUTF();
                String[] message = second.split(";");
                if(message.length!=0 && clients.contains(Integer.parseInt(message[1]))){
                    System.out.println(message[0]);
                }

            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}