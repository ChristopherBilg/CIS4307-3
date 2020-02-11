package com.example.simplechatclientandroid;

import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Arrays;
import java.util.UUID;

public class AsyncRegister extends AsyncTask<Void, Void, Void> {

    private String username;
    private String hostname;
    private int port;
    private String uuid;

    public AsyncRegister(String username, String hostname, int port, String uuid) {
        this.username = username;
        this.hostname = hostname;
        this.port = port;
        this.uuid = uuid;
    }

    @Override
    protected Void doInBackground(Void... params) {
        try {
            registerUsernameWithServer(this.username, this.hostname, this.port, this.uuid);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void registerUsernameWithServer(String username, String hostname, int port, String uuid) throws IOException {
        DatagramSocket socket = new DatagramSocket();
        InetAddress address = InetAddress.getByName(hostname);

        String registrationString = "{\"header\":{\"username\":\"" + username + "\",\"uuid\":\"" + uuid + "\",\"timestamp\":\"{}\",\"type\":\"register\"},\"body\":{}}";

        byte buf[] = registrationString.getBytes();
        byte buf1[] = new byte[1024];
        DatagramPacket dp = new DatagramPacket(buf, registrationString.length(), address, port);
        DatagramPacket dptorec = new DatagramPacket(buf1, 1024);

        socket.connect(address, port);
        socket.setSoTimeout(1000);

        socket.send(dp);
        Log.d("CHATCLIENT", "Packet sent successfully.");

        socket.receive(dptorec);
        Log.d("CHATCLIENT", "Received packet data : " + Arrays.toString(dptorec.getData()));
        String message = new String(dptorec.getData(), dptorec.getOffset(), dptorec.getLength());
        Log.d("CHATCLIENT", message);
    }
}
