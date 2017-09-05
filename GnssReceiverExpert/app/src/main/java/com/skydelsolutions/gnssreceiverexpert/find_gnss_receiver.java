package com.skydelsolutions.gnssreceiverexpert;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Spinner;
import java.util.List;

public class find_gnss_receiver extends AppCompatActivity {

    private GnssExpert myExpert = new GnssExpert();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gnss_receiver_finder);
    }


    public void onClickFindGnssReceiver(View view)
    {
        TextView gnssReceiversList = (TextView) findViewById(R.id.gnssReceiverList);
        Spinner  gnssReceiverTypes = (Spinner) findViewById(R.id.gnssReceiverTypes);

        //String gnssReceivers = String.valueOf(gnssReceiverTypes.getSelectedItem());
        //String gnssReceivers = "toto";
        List<String> gnssReceiversResult = myExpert.getGnssReceivers(String.valueOf(gnssReceiverTypes.getSelectedItem()));
        StringBuilder gnssReceriversFormatted = new StringBuilder();
        for (String rec : gnssReceiversResult)
        {
            gnssReceriversFormatted.append(rec).append("\n");
        }
        gnssReceiversList.setText(gnssReceriversFormatted);
    }
}

