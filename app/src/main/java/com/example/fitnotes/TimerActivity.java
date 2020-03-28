package com.example.fitnotes;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import java.util.Locale;
import android.os.CountDownTimer;
import android.widget.Button;
import android.widget.TextView;
import java.util.Locale;

public class TimerActivity extends AppCompatActivity {

    private static final long START_TIME_IN_MILLIS = 600000;

    private TextView countdownView;
    private Button startPauseButton;
    private Button resetButton;

    private CountDownTimer restTimer;
    private boolean timerRunning;

    private long timeLeftInMillis = START_TIME_IN_MILLIS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);

        countdownView = findViewById(R.id.view_countdown);

        startPauseButton = findViewById(R.id.start_pauseBttn);
        resetButton = findViewById(R.id.reset_timerBttn);

        startPauseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (timerRunning) {
                    pauseTimer();
                } else {
                    startTimer();
                }
            }
        });

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetTimer();
            }
        });

        updateCountDownText();
    }

    private void startTimer() {
        restTimer = new CountDownTimer(timeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftInMillis = millisUntilFinished;
                updateCountDownText();
            }

            @Override
            public void onFinish() {
                timerRunning = false;
                startPauseButton.setText("Start");
                startPauseButton.setVisibility(View.INVISIBLE);
                resetButton.setVisibility(View.VISIBLE);
            }
        }.start();

        timerRunning = true;
        startPauseButton.setText("Pause");
        resetButton.setVisibility(View.INVISIBLE);
    }

    private void pauseTimer() {
        restTimer.cancel();
        timerRunning = false;
        startPauseButton.setText("Start");
        resetButton.setVisibility(View.VISIBLE);
    }

    private void resetTimer() {
        timeLeftInMillis = START_TIME_IN_MILLIS;
        updateCountDownText();
        resetButton.setVisibility(View.INVISIBLE);
        startPauseButton.setVisibility(View.VISIBLE);
    }

    private void updateCountDownText() {
        int minutes = (int) (timeLeftInMillis / 1000) / 60;
        int seconds = (int) (timeLeftInMillis / 1000) % 60;

        String timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);

        countdownView.setText(timeLeftFormatted);
    }
}
