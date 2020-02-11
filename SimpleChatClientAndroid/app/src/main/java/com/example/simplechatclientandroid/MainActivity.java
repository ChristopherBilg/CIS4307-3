package com.example.simplechatclientandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private Button joinButton;
    private Button settingsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize the join button
        joinButton = findViewById(R.id.mainActivityJoinButton);
        joinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openJoinActivity();
            }
        });

        // Initialize the settings button
        settingsButton = findViewById(R.id.mainActivitySettingsButton);
        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSecondActivity();
            }
        });
    }

    public void openJoinActivity() {
        Intent intent = new Intent(this, ThirdActivity.class);

        EditText usernameText = findViewById(R.id.usernameInput);
        EditText hostnameText = findViewById(R.id.mainActivityHostname);
        EditText portText = findViewById(R.id.mainActivityPort);

        String username = usernameText.getText().toString();
        String hostname = hostnameText.getText().toString();
        int port = Integer.parseInt(portText.getText().toString());

        intent.putExtra("USERNAME", username);
        intent.putExtra("HOSTNAME", hostname);
        intent.putExtra("PORT", port);
        startActivity(intent);
    }

    public void openSecondActivity() {
        Intent intent = new Intent(this, SecondActivity.class);
        startActivity(intent);
    }
}
