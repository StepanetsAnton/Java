package Lesson7.Client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        String[] params = args[0].split(":");
        String address = params[0];
        int port = Integer.parseInt(params[1]);
        int id = 11;
        int magicNumber = 321;
        try (
                Socket socket = new Socket(InetAddress.getByName(address), port);
                DataInputStream input = new DataInputStream(socket.getInputStream());
                DataOutputStream output = new DataOutputStream(socket.getOutputStream())
        ) {
            String connection = magicNumber+" "+id;
            output.writeUTF(connection);

            Scanner sc = new Scanner(System.in);
            String msg = sc.nextLine();
            String message = msg+";"+id;
            output.writeUTF(message);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
