package com.example.simplechatclientandroid;

import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class AsyncRetrieveChatLog extends AsyncTask<String, String, String> {

    private String username;
    private String hostname;
    private int port;
    private String uuid;
    private TextView chatLog;

    public AsyncRetrieveChatLog(String username, String hostname, int port, String uuid, TextView chatLog) {
        this.username = username;
        this.hostname = hostname;
        this.port = port;
        this.uuid = uuid;
        this.chatLog = chatLog;
    }

    @Override
    protected String doInBackground(String... params) {
        try {
            getChatLogsFromServer(this.username, this.hostname, this.port, this.uuid, this.chatLog);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void getChatLogsFromServer(String username, String hostname, int port, String uuid, TextView chatLog) throws IOException {
        DatagramSocket socket = new DatagramSocket();
        InetAddress address = InetAddress.getByName(hostname);

        String registrationString = "{\"header\":{\"username\":\"" + username + "\",\"uuid\":\"" + uuid + "\",\"timestamp\":\"{}\",\"type\":\"retrieve_chat_log\"},\"body\":{}}";

        byte buf[] = registrationString.getBytes();
        byte buf1[] = new byte[1024];
        DatagramPacket dp = new DatagramPacket(buf, registrationString.length(), address, port);
        DatagramPacket dptorec = new DatagramPacket(buf1, 1024);

        socket.connect(address, port);
        socket.setSoTimeout(1000);

        socket.send(dp);
        Log.d("CHATCLIENT", "Packet sent successfully.");

        for (int i = 0; i < 7; i++) {
            socket.receive(dptorec);
            String message = new String(dptorec.getData(), dptorec.getOffset(), dptorec.getLength());
            Log.d("CHATCLIENT", message);
            this.publishProgress(message);
        }
    }

    @Override
    protected void onProgressUpdate(String... values) {
        super.onProgressUpdate(values);
        this.chatLog.setText(this.chatLog.getText() + "\n" + values[0]);
    }
}
