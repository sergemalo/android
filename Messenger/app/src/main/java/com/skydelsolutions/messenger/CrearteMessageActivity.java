package com.skydelsolutions.messenger;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.content.Intent;

public class CrearteMessageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creare_message);
    }

    public void onSendMessage(View view) {
        EditText msgToSend = (EditText) findViewById(R.id.messageToSend);
        //Intent intent = new Intent(this, ReceiveMessageActivity.class);
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, msgToSend.getText().toString());
        intent.putExtra(Intent.EXTRA_SUBJECT, "Mon sujet");
        //intent.putExtra(ReceiveMessageActivity.EXTRA_MESSAGE, msgToSend.getText().toString());
        Intent chosenIntent = Intent.createChooser(intent, getString(R.string.chooserTitle));
        startActivity(chosenIntent);
    }
}
