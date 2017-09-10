package com.skydelsolutions.stopwatch;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.os.Handler;

public class StopWatchActivity extends AppCompatActivity {

    private int  m_elapsedSeconds = 0;
    private boolean m_running = false;
    private boolean m_wasRunning = false;
    private TextView m_timeTextView;
    private TextView m_debugTextView;
    private String m_debugString = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stop_watch);
        m_timeTextView = (TextView) findViewById(R.id.textViewTimeId);
        m_debugTextView = (TextView) findViewById(R.id.textViewDebugId);

        if (savedInstanceState != null) {
            m_elapsedSeconds = savedInstanceState.getInt("seconds");
            m_running = savedInstanceState.getBoolean("running");
            m_wasRunning = savedInstanceState.getBoolean("wasrunning");
            m_debugString = savedInstanceState.getString("debugStr");
        }
        m_debugString += "1,";
        printTime();
        runTimer();
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        m_debugString += "8,";
    }

    @Override
    protected void onStart() {
        super.onStart();
        m_debugString += "2,";
        m_running = m_wasRunning;
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        m_debugString += "3,";
    }

    @Override
    protected void onStop() {
        super.onStop();
        m_debugString += "4,";
        m_wasRunning = m_running;
        m_running = false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        m_debugString += "5,";
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        m_debugString += "9,";
        outState.putInt("seconds", m_elapsedSeconds);
        outState.putBoolean("running", m_running);
        outState.putBoolean("wasrunning", m_wasRunning);
        outState.putString("debugStr", m_debugString);
    }

    @Override
    protected void onPause() {
        super.onPause();
        m_debugString += "6,";
    }

    @Override
    protected void onResume() {
        super.onResume();
        m_debugString += "7,";
    }

    private void printTime() {
        String timeStr = String.format("%d:%02d:%02d", m_elapsedSeconds/3600, ((m_elapsedSeconds % 3600)/60) % 60, m_elapsedSeconds % 60);
        m_timeTextView.setText(timeStr);
        m_debugTextView.setText(m_debugString);
    }

    private void runTimer() {
        final Handler myHandler = new Handler();
        myHandler.post(new Runnable() {
            @Override
            public void run() {
                printTime();
                if (m_running) {
                    ++m_elapsedSeconds;
                }
                myHandler.postDelayed(this, 1000);
            }
        });
    }

    public void onClickStart(View view) {
        m_debugString += "b1,";
        m_running = true;
    }

    public void onClickStop(View view) {
        m_debugString += "b2";
        m_running = false;
        printTime();
    }

    public void onClickReset(View view) {
        m_debugString = "";
        m_running = false;
        m_elapsedSeconds = 0;
        printTime();
    }
}
