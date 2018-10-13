// UDPClient.java: A simple UDP client program.

import java.net.*;
import java.io.*;

public class UDPClient {

    static String data = "";
    final static int PORT = 5458;
    final static int BUFSIZE = 1024;

    public static void main(String args[]) {

        try ( DatagramSocket aSocket = new DatagramSocket();) {
            InetAddress aHost = InetAddress.getByName(args[0]);
            data=System.currentTimeMillis()+"";
            byte[] dataArray = data.getBytes();
            DatagramPacket requestPacket = new DatagramPacket(dataArray, dataArray.length, aHost, PORT);
            aSocket.send(requestPacket);

            byte[] buffer = new byte[BUFSIZE];
            DatagramPacket recievePacket = new DatagramPacket(buffer, buffer.length);
            aSocket.receive(recievePacket);
            long delta=System.currentTimeMillis();
            delta-=Long.parseLong(new String(recievePacket.getData()).trim());
            System.out.println("RTT "+delta+" ms");
        } catch (Exception e) {
            System.out.println("Socket: " + e.getMessage());
        } 
    }
}
