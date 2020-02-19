package com.example.simplechatclientandroid;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.UUID;

import ch.ethz.inf.vs.a3.message.Message;
import ch.ethz.inf.vs.a3.message.MessageComparator;

public class ThirdActivity extends AppCompatActivity {

    Button backButton;
    Button retrieveChatLogButton;
    Button chatLogOrderingButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        // initialize the username placeholder TextView area
        Intent intent = getIntent();
        final String username = intent.getStringExtra("USERNAME");
        final String hostname = intent.getStringExtra("HOSTNAME");
        final int port = intent.getIntExtra("PORT", 4446);

        // generate the uuid
        final String uuid = UUID.randomUUID().toString();

        TextView usernamePlaceholder = findViewById(R.id.usernamePlaceholderTextView);
        usernamePlaceholder.setText(username);

        // initialize the back button (also the deregister button)
        backButton = findViewById(R.id.thirdActivityBackButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMainActivity(username, hostname, port, uuid);
            }
        });

        // initialize the chat log retrieval button
        retrieveChatLogButton = findViewById(R.id.retrieveChatLogButton);
        retrieveChatLogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                retrieveChatLogs(username, hostname, port, uuid, (TextView) findViewById(R.id.chatLogTextView));
            }
        });

        // initialize the chat log ordering button
        chatLogOrderingButton = findViewById(R.id.chatLogOrderButton);
        chatLogOrderingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                orderChatLogs();
            }
        });

        // REGISTER WITH THE SERVER
        new AsyncRegister(username, hostname, port, uuid).execute();
    }

    public void openMainActivity(String username, String hostname, int port, String uuid) {
        // DEREGISTER WITH THE SERVER
        new AsyncDeregister(username, hostname, port, uuid).execute();

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void retrieveChatLogs(String username, String hostname, int port, String uuid, TextView chatLog) {
        chatLog.setText("");
        new AsyncRetrieveChatLog(username, hostname, port, uuid, chatLog).execute();
    }

    public void orderChatLogs() {
        TextView allMessages = (TextView) findViewById(R.id.chatLogTextView);
        String messages = allMessages.getText().toString();

        if (messages.startsWith("Ordered Chat Logs") || messages.startsWith("No chat logs have been found!"))
            return;

        if (messages == null || messages == "") {
            allMessages.setText("No chat logs have been found!");
            return;
        }
        
        String[] individualMessages = messages.trim().split("\n");
        Log.d("CHATCLIENT", "Number of messages found: " + String.valueOf(individualMessages.length));

        Message[] iMessages = new Message[individualMessages.length];
        for (int i = 0; i < individualMessages.length; i++)
            iMessages[i] = new Message(individualMessages[i]);

        Comparator<Message> comparator = new MessageComparator();
        PriorityQueue<Message> priorityQueue = new PriorityQueue<Message>(iMessages.length, comparator);
        for (Message m : iMessages)
            priorityQueue.add(m);

        allMessages.setText("Ordered Chat Logs\n");
        for (Message m : priorityQueue) {
            Log.d("CHATCLIENT", m.getBodyContents() + " : " + m.getTimestampContents());
            allMessages.setText(allMessages.getText() + "\n" + m.getBodyContents() + " : " + m.getTimestampContents());
        }
    }
}
