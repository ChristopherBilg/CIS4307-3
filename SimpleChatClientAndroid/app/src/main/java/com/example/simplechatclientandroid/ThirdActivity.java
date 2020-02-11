package com.example.simplechatclientandroid;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

import java.util.UUID;

public class ThirdActivity extends AppCompatActivity {

    Button backButton;
    Button retrieveChatLogButton;

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
}
