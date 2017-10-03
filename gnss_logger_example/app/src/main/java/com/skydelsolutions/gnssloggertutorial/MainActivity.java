package com.skydelsolutions.gnssloggertutorial;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.graphics.Color;
import android.widget.TextView;
import android.text.Editable;
import android.Manifest;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.os.Build;
import android.app.Activity;
import android.content.pm.PackageManager;


public class MainActivity extends AppCompatActivity {
    private GnssController gnssController;
    private UiLogger uiLogger;
    private FileLogger fileLogger;
    private static final String[] REQUIRED_PERMISSIONS = {
            Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // set required runtime permissions
        requestPermissionAndSetupFragments(this);

        // Init needed loggers and Gnss Controller
        ResizableTextView resizableTextView = new ResizableTextView();
        uiLogger = new UiLogger(resizableTextView);
        fileLogger = new FileLogger(getApplicationContext());
        gnssController = new GnssController(getApplicationContext(), uiLogger, fileLogger);

        // Location ON button setup
        final Button registerLocation = (Button) findViewById(R.id.location_on);
        registerLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });

        // Location OFF button setup
        final Button unregisterLocation = (Button) findViewById(R.id.location_off);
        unregisterLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });

        // Raw GNSS measurements ON button setup
        final Button registerMeasurements = (Button) findViewById(R.id.raw_meas_on);
        registerMeasurements.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });

        // Raw GNSS measuremtns OFF button setup
        final Button unregisterMeasurements = (Button) findViewById(R.id.raw_meas_off);
        unregisterMeasurements.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });

        // Start LOG and Send File buttons setup
        final Button startLog = (Button) findViewById(R.id.start_log);
        final Button sendFile = (Button) findViewById(R.id.send_file);

        startLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startLog.setEnabled(false);
                sendFile.setEnabled(true);
                Toast.makeText(getApplicationContext(), "Starting log...", Toast.LENGTH_LONG).show();
                fileLogger.startNewLog();
            }
        });

        sendFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startLog.setEnabled(true);
                sendFile.setEnabled(false);
                Toast.makeText(getApplicationContext(), "Sending file...", Toast.LENGTH_LONG).show();
                fileLogger.send();
            }
        });
        // Init Gnss Controller
        gnssController = new GnssController(getApplicationContext());
        gnssController.registerLocation();
        gnssController.unregisterLocation();
        gnssController.registerMeasurements();
        gnssController.unregisterMeasurements();
    }

    /**
     * A class that encapsulates the TextView for setting TextView properties such as color and
     * text box size.
     */
    public class ResizableTextView {

        private static final int MAX_LENGTH = 1200;
        private static final int LOWER_THRESHOLD = (int) (MAX_LENGTH * 0.9);

        public synchronized void logTextFragment(final String tag, final String text) {
            final SpannableStringBuilder builder = new SpannableStringBuilder();
            builder.append(tag).append(" | ").append(text).append("\n\n");
            builder.setSpan(
                    new ForegroundColorSpan(Color.BLACK),
                    0 /* start */,
                    builder.length(),
                    SpannableStringBuilder.SPAN_INCLUSIVE_EXCLUSIVE);
            runOnUiThread(
                    new Runnable() {
                        @Override
                        public void run() {
                            TextView mLogView = (TextView) findViewById(R.id.text_view);
                            mLogView.append(builder);
                            Editable editable = mLogView.getEditableText();
                            int length = editable.length();
                            if (length > MAX_LENGTH) {
                                editable.delete(0, length - LOWER_THRESHOLD);
                            }
                        }
                    });
        }
    }

    private void requestPermissionAndSetupFragments(final Activity activity) {
        if (!hasPermissions(activity)) {
            ActivityCompat.requestPermissions(activity, REQUIRED_PERMISSIONS, 1 /* LOCATION REQUEST ID*/);
        }
    }

    private boolean hasPermissions(Activity activity) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            // Permissions granted at install time.
            return true;
        }
        for (String p : REQUIRED_PERMISSIONS) {
            if (ContextCompat.checkSelfPermission(activity, p) != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

}