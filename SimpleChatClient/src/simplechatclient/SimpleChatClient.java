/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simplechatclient;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Arrays;

/**
 *
 * @author chris
 */
public class SimpleChatClient {
    
    public static String server_hostname = "127.0.0.1";
    public static int server_port = 4446;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // Constructor to create a datagram socket 
        DatagramSocket socket = new DatagramSocket(); 
        InetAddress address = InetAddress.getByName(server_hostname); 
        int port = server_port; 
        byte buf[] = { 12, 13 }; 
        byte buf1[] = new byte[2]; 
        DatagramPacket dp = new DatagramPacket(buf, 2, address, port); 
        DatagramPacket dptorec = new DatagramPacket(buf1, 2); 
          
        // connect() method 
        socket.connect(address, port); 
      
        // isBound() method 
        System.out.println("IsBound : " + socket.isBound()); 
  
        // isConnected() method 
        System.out.println("isConnected : " + socket.isConnected()); 
  
        // getInetAddress() method 
        System.out.println("InetAddress : " + socket.getInetAddress()); 
  
        // getPort() method 
        System.out.println("Port : " + socket.getPort()); 
  
        // getRemoteSocketAddress() method 
        System.out.println("Remote socket address : " +  
                         socket.getRemoteSocketAddress()); 
  
        // getLocalSocketAddress() method 
        System.out.println("Local socket address : " +  
                          socket.getLocalSocketAddress()); 
  
        // send() method 
        socket.send(dp); 
        System.out.println("...packet sent successfully...."); 
  
        // receive() method 
        socket.receive(dptorec); 
        System.out.println("Received packet data : " +  
                          Arrays.toString(dptorec.getData())); 
  
        // getLocalPort() method 
        System.out.println("Local Port : " + socket.getLocalPort()); 
  
        // getLocalAddress() method 
        System.out.println("Local Address : " + socket.getLocalAddress()); 
  
        // setSOTimeout() method 
        socket.setSoTimeout(50); 
  
        // getSOTimeout() method 
        System.out.println("SO Timeout : " + socket.getSoTimeout()); 
    }
    
}
