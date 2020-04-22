package com.nitt.edu.factor;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Vibrator;
import android.text.TextUtils;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.Button;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity implements View.OnClickListener {

    public int checkThis = 0;
    public int theChosenOne = 0;
    public int score;
    public Button firstButton;
    public Button secondButton;
    public Button thirdButton;
    public Button enterButton;
    public Button myTryAgain;
    public EditText myNumber;
    public TextView myInput, myTitle, myFinalAnswer, scoreStreak, firstTextBox;
    public TextView myTimer;
    private CountDownTimer countDownTimer;
    private long timeLeftInMilliSeconds = 10000;
    View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        firstButton = findViewById(R.id.firstButton);
        secondButton = findViewById(R.id.secondButton);
        thirdButton = findViewById(R.id.thirdButton);
        myTryAgain = findViewById(R.id.myTryAgain);
        myTitle = findViewById(R.id.myTitle);
        scoreStreak = findViewById(R.id.myBest);
        myInput = findViewById(R.id.myInputText);
        myNumber = findViewById(R.id.myInputNumber);
        enterButton = findViewById(R.id.enterButton);
        myFinalAnswer = findViewById(R.id.myFinalAnswer);
        firstTextBox = findViewById(R.id.firstTextBox);
        myTimer = findViewById(R.id.myTimer);

        loadData();
        scoreStreak.setText("BEST : " + score);

        firstButton.setVisibility(View.INVISIBLE);
        secondButton.setVisibility(View.INVISIBLE);
        thirdButton.setVisibility(View.INVISIBLE);
        myTryAgain.setVisibility(View.INVISIBLE);
        myFinalAnswer.setVisibility(View.INVISIBLE);
        firstTextBox.setVisibility(View.INVISIBLE);
        myTimer.setVisibility(View.INVISIBLE);
        myTryAgain.setOnClickListener(this);
        enterButton.setOnClickListener(this);
        firstButton.setOnClickListener(this);
        secondButton.setOnClickListener(this);
        thirdButton.setOnClickListener(this);


    }

    public void saveData() {
        SharedPreferences sharedPreferences = getSharedPreferences("scoreInfo", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("score", score);
        editor.apply();
    }

    public void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences("scoreInfo", Context.MODE_PRIVATE);
        score = sharedPreferences.getInt("score", 0);
    }

    public boolean isPrime(int i) {
        int count = 0;
        for (int j = 1; j <= i; j++) {
            if (i % j == 0)
                count++;
        }
        if (count == 2)
            return true;
        else
            return false;
    }

    @Override
    public void onClick(View v) {

        final Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        final long patt[] = new long[]{0, 500};
        Intent intent = getIntent();
        view = this.getWindow().getDecorView();

        switch (v.getId()) {
            case (R.id.myTryAgain):
                finish();
                startActivity(intent);
                break;

            case (R.id.secondButton):
                if (checkThis == 2) {
                    choiceClicked();
                    myFinalAnswer.setText("CORRECT!");
                    stopTimer();
                    score++;
                    saveData();
                    scoreStreak.setText("BEST : " + score);
                    view.setBackgroundResource(R.color.green);
                    saveData();
                } else {
                    choiceClicked();
                    stopTimer();
                    vibrator.vibrate(patt, -1);
                    score = 0;
                    saveData();
                    myFinalAnswer.setText("WRONG ! ANSWER IS : " + theChosenOne);
                    scoreStreak.setText("BEST : " + score);
                    view.setBackgroundResource(R.color.red);
                    saveData();
                }
                break;
            case (R.id.firstButton):
                if (checkThis == 1) {
                    choiceClicked();
                    stopTimer();
                    score++;
                    saveData();
                    scoreStreak.setText("BEST : " + score);
                    view.setBackgroundResource(R.color.green);
                    myFinalAnswer.setText("CORRECT!");
                    saveData();
                } else {
                    choiceClicked();
                    stopTimer();
                    vibrator.vibrate(patt, -1);
                    score = 0;
                    saveData();
                    view.setBackgroundResource(R.color.red);
                    scoreStreak.setText("BEST : " + score);
                    myFinalAnswer.setText("WRONG ! ANSWER IS : " + theChosenOne);
                    saveData();
                }
                break;

            case R.id.thirdButton:
                if (checkThis == 3) {
                    choiceClicked();
                    stopTimer();
                    score++;
                    saveData();
                    myFinalAnswer.setText("CORRECT!");
                    scoreStreak.setText("BEST : " + score);
                    view.setBackgroundResource(R.color.green);
                    saveData();
                } else {
                    choiceClicked();
                    stopTimer();
                    vibrator.vibrate(patt, -1);
                    score = 0;
                    saveData();
                    scoreStreak.setText("BEST : " + score);
                    myFinalAnswer.setText("WRONG ! ANSWER IS : " + theChosenOne);
                    view.setBackgroundResource(R.color.red);
                    saveData();
                }
                break;

            case (R.id.enterButton):
                if (TextUtils.isEmpty(myNumber.getText().toString())) {
                    Toast.makeText(MainActivity.this, "Enter a number first!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (Integer.valueOf(myNumber.getText().toString()) == 0) {
                    Toast.makeText(MainActivity.this, "You cannot find factors of 1!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (Integer.valueOf(myNumber.getText().toString()) == 1) {
                    Toast.makeText(MainActivity.this, "You want to know factors for 1?", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (isPrime(Integer.valueOf(myNumber.getText().toString()))) {
                    Toast.makeText(MainActivity.this, "A prime number has only 2 factors, you should know that", Toast.LENGTH_SHORT).show();
                    return;
                }

                firstTextBox.setText("Now, find the factor of " + (myNumber.getText().toString()));
                visibilityEnter();
                startTimer();
                int no;
                no = Integer.valueOf(myNumber.getText().toString());

                int not1 = 0, not2 = 0;
                while (true) {
                    theChosenOne = (int) (100000 * Math.random());
                    if (theChosenOne != 0 && no % theChosenOne == 0 && theChosenOne != 1)
                        break;
                }

                while (true) {
                    not1 = (int) (100000 * Math.random());
                    if (not1 != 0 && no >= not1 && no % not1 != 0)
                        break;
                }

                while (true) {
                    not2 = (int) (100000 * Math.random());
                    if (not2 != 0 && no >= not2 && no % not2 != 0)
                        break;
                }

                int chose = (int) (100 * Math.random());
                if (chose < 33) {
                    firstButton.setText(String.valueOf(theChosenOne));
                    secondButton.setText(String.valueOf(not1));
                    thirdButton.setText(String.valueOf(not2));
                    checkThis = 1;
                } else if (chose >= 33 || chose < 66) {
                    secondButton.setText(String.valueOf(theChosenOne));
                    firstButton.setText(String.valueOf(not1));
                    thirdButton.setText(String.valueOf(not2));
                    checkThis = 2;
                } else {
                    thirdButton.setText(String.valueOf(theChosenOne));
                    secondButton.setText(String.valueOf(not1));
                    firstButton.setText(String.valueOf(not2));
                    checkThis = 3;
                }
                break;
        }
    }

    public void startTimer() {
        countDownTimer = new CountDownTimer(timeLeftInMilliSeconds, 1000) {
            @Override
            public void onTick(long l) {
                timeLeftInMilliSeconds = l;
                String time = String.valueOf(timeLeftInMilliSeconds / 1000);
                myTimer.setText(time);
            }

            @Override
            public void onFinish() {
                final Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
                final long patt[] = new long[]{0, 500};
                visibilityFinish();
                score = 0;
                saveData();
                vibrator.vibrate(patt, -1);
                myFinalAnswer.setText("TIME'S UP!");
                view.setBackgroundResource(R.color.red);
            }
        }.start();
    }

    public void stopTimer() {
        countDownTimer.cancel();
    }

    public void visibilityFinish() {
        firstTextBox.setVisibility(View.INVISIBLE);
        firstButton.setVisibility(View.INVISIBLE);
        secondButton.setVisibility(View.INVISIBLE);
        thirdButton.setVisibility(View.INVISIBLE);
        myTryAgain.setVisibility(View.VISIBLE);
    }

    public void visibilityEnter() {
        enterButton.setVisibility(View.INVISIBLE);
        myInput.setVisibility(View.INVISIBLE);
        myNumber.setVisibility(View.INVISIBLE);
        firstButton.setVisibility(View.VISIBLE);
        secondButton.setVisibility(View.VISIBLE);
        firstTextBox.setVisibility(View.VISIBLE);
        thirdButton.setVisibility(View.VISIBLE);
        myTimer.setVisibility(View.VISIBLE);
    }

    public void choiceClicked() {

        firstButton.setVisibility(View.INVISIBLE);
        secondButton.setVisibility(View.INVISIBLE);
        thirdButton.setVisibility(View.INVISIBLE);
        myTryAgain.setVisibility(View.VISIBLE);
        firstTextBox.setVisibility(View.INVISIBLE);
        myFinalAnswer.setVisibility(View.VISIBLE);
        myTimer.setVisibility(View.INVISIBLE);
    }
}
