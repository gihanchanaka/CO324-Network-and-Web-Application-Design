// UDPServer.java: A simple UDP server program.

import java.net.*;
import java.io.*;
import java.util.*;

public class TPServer {
    final static int BUFSIZE = 1024, COUNT=100000;
    
    public static void main(String args[]) {
        long start=Integer.MAX_VALUE;
        byte[] bufferRecieve = new byte[BUFSIZE];          
        DatagramPacket recievePacket = new DatagramPacket(bufferRecieve, BUFSIZE);

        for (int i=0 ; i<COUNT; i++)
        try (DatagramSocket aSocket = new DatagramSocket(Integer.parseInt(args[0]));) {
 			aSocket.receive(recievePacket);

			if (i==0) {
			  start = System.currentTimeMillis();              
            }
            // System.out.println("Got packet "+i);
        } catch (Exception e) {            
            System.out.println("Socket: " + e.getMessage());            
        } 
        long delta = System.currentTimeMillis() - start;
        System.out.println("Throughput =" + 1000*COUNT/delta);
    }
}
