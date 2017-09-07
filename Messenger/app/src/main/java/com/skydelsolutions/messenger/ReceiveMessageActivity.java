package com.skydelsolutions.messenger;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.content.Intent;

public class ReceiveMessageActivity extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "message";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receive_message);

        Intent intent = getIntent();
        TextView recMsgView = (TextView) findViewById(R.id.receivedMessage);
        recMsgView.setText(intent.getStringExtra(EXTRA_MESSAGE));
    }
}
