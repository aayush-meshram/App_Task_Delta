package com.nitt.edu.factor;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Vibrator;
import android.text.InputType;
import android.text.TextUtils;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.Button;
import android.graphics.Color;
import android.view.View;
import android.content.res.Resources;
import android.util.TypedValue;
import android.widget.Toast;

public class MainActivity extends Activity implements View.OnClickListener {

    public int checkThis = 0;
    public int theChosenOne = 0;
    public int score;
    public Button myFirst;
    public Button mySecond;
    public Button myThird;
    public Button myEnter;
    public Button myTryAgain;
    public EditText myNumber;
    public TextView myInput, myTitle, myFinalAnswer, scoreStreak, myFake;
    public TextView myTimer;
    private CountDownTimer countDownTimer;
    private long timeLeftInMilliSeconds = 10000;
    View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        myFirst = findViewById(R.id.myFirst);
        mySecond = findViewById(R.id.mySecond);
        myThird = findViewById(R.id.myThird);
        myTryAgain = findViewById(R.id.myTryAgain);
        myTitle = findViewById(R.id.myTitle);
        scoreStreak = findViewById(R.id.myBest);
        myInput = findViewById(R.id.myInputText);
        myNumber = findViewById(R.id.myInputNumber);
        myEnter = findViewById(R.id.myEnter);
        myFinalAnswer = findViewById(R.id.myFinalAnswer);
        myFake = findViewById(R.id.myFake);
        myTimer = findViewById(R.id.myTimer);

        loadData();
        scoreStreak.setText("BEST : "+score);

        myFirst.setVisibility(View.INVISIBLE);
        mySecond.setVisibility(View.INVISIBLE);
        myThird.setVisibility(View.INVISIBLE);
        myTryAgain.setVisibility(View.INVISIBLE);
        myFinalAnswer.setVisibility(View.INVISIBLE);
        myFake.setVisibility(View.INVISIBLE);
        myTimer.setVisibility(View.INVISIBLE);
        myTryAgain.setOnClickListener(this);
        myEnter.setOnClickListener(this);
        myFirst.setOnClickListener(this);
        mySecond.setOnClickListener(this);
        myThird.setOnClickListener(this);


    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
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

            case (R.id.mySecond):
                if (checkThis == 2) {
                    myFirst.setVisibility(View.INVISIBLE);
                    mySecond.setVisibility(View.INVISIBLE);
                    myThird.setVisibility(View.INVISIBLE);
                    myTryAgain.setVisibility(View.VISIBLE);
                    myFake.setVisibility(View.INVISIBLE);
                    myFinalAnswer.setVisibility(View.VISIBLE);
                    myFinalAnswer.setText("CORRECT!");
                    myTimer.setVisibility(View.INVISIBLE);
                    stopTimer();
                    score++;
                    saveData();
                    scoreStreak.setText("BEST : " + score);
                    view.setBackgroundResource(R.color.green);
                    saveData();
                }
                else {
                    myFirst.setVisibility(View.INVISIBLE);
                    mySecond.setVisibility(View.INVISIBLE);
                    myThird.setVisibility(View.INVISIBLE);
                    myTryAgain.setVisibility(View.VISIBLE);
                    myFake.setVisibility(View.INVISIBLE);
                    myFinalAnswer.setVisibility(View.VISIBLE);
                    myTimer.setVisibility(View.INVISIBLE);
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
            case (R.id.myFirst):
                if (checkThis == 1) {
                    myFirst.setVisibility(View.INVISIBLE);
                    mySecond.setVisibility(View.INVISIBLE);
                    myThird.setVisibility(View.INVISIBLE);
                    myTryAgain.setVisibility(View.VISIBLE);
                    myFake.setVisibility(View.INVISIBLE);
                    myFinalAnswer.setVisibility(View.VISIBLE);
                    myTimer.setVisibility(View.INVISIBLE);
                    stopTimer();
                    score++;
                    saveData();
                    scoreStreak.setText("BEST : " + score);
                    view.setBackgroundResource(R.color.green);
                    myFinalAnswer.setText("CORRECT!");
                    saveData();
                }
                else {
                    myFirst.setVisibility(View.INVISIBLE);
                    mySecond.setVisibility(View.INVISIBLE);
                    myThird.setVisibility(View.INVISIBLE);
                    myTryAgain.setVisibility(View.VISIBLE);
                    myFake.setVisibility(View.INVISIBLE);
                    myFinalAnswer.setVisibility(View.VISIBLE);
                    myTimer.setVisibility(View.INVISIBLE);
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

            case R.id.myThird:
                if (checkThis == 3) {
                    myFirst.setVisibility(View.INVISIBLE);
                    mySecond.setVisibility(View.INVISIBLE);
                    myThird.setVisibility(View.INVISIBLE);
                    myTryAgain.setVisibility(View.VISIBLE);
                    myFake.setVisibility(View.INVISIBLE);
                    myFinalAnswer.setVisibility(View.VISIBLE);
                    myTimer.setVisibility(View.INVISIBLE);
                    stopTimer();
                    score++;
                    saveData();
                    myFinalAnswer.setText("CORRECT!");
                    scoreStreak.setText("BEST : " + score);
                    view.setBackgroundResource(R.color.green);
                    saveData();
                } else {
                    myFirst.setVisibility(View.INVISIBLE);
                    mySecond.setVisibility(View.INVISIBLE);
                    myThird.setVisibility(View.INVISIBLE);
                    myTryAgain.setVisibility(View.VISIBLE);
                    myFake.setVisibility(View.INVISIBLE);
                    myFinalAnswer.setVisibility(View.VISIBLE);
                    myTimer.setVisibility(View.INVISIBLE);
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

        case (R.id.myEnter):
        if (TextUtils.isEmpty(myNumber.getText().toString())) {
            Toast.makeText(MainActivity.this, "Enter a number first!", Toast.LENGTH_SHORT).show();
            return;
        }

        if(Integer.valueOf(myNumber.getText().toString()) == 1) {
            Toast.makeText(MainActivity.this, "You want to know factors for 1?", Toast.LENGTH_SHORT).show();
            return;
        }

        if (isPrime(Integer.valueOf(myNumber.getText().toString()))) {
            Toast.makeText(MainActivity.this, "A prime number has only 2 factors, you should know that", Toast.LENGTH_SHORT).show();
            return;
        }

        myFake.setText("Now, find the factor of " + (myNumber.getText().toString()));
        myEnter.setVisibility(View.INVISIBLE);
        myInput.setVisibility(View.INVISIBLE);
        myNumber.setVisibility(View.INVISIBLE);
        myFirst.setVisibility(View.VISIBLE);
        mySecond.setVisibility(View.VISIBLE);
        myFake.setVisibility(View.VISIBLE);
        myThird.setVisibility(View.VISIBLE);
        myTimer.setVisibility(View.VISIBLE);
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
        if (chose < 33 ) {
            myFirst.setText(String.valueOf(theChosenOne));
            mySecond.setText(String.valueOf(not1));
            myThird.setText(String.valueOf(not2));
            checkThis = 1;
        } else if (chose >= 33 || chose < 66) {
            mySecond.setText(String.valueOf(theChosenOne));
            myFirst.setText(String.valueOf(not1));
            myThird.setText(String.valueOf(not2));
            checkThis = 2;
        } else {
            myThird.setText(String.valueOf(theChosenOne));
            mySecond.setText(String.valueOf(not1));
            myFirst.setText(String.valueOf(not2));
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
                String time = String.valueOf(timeLeftInMilliSeconds/1000);
                myTimer.setText(time);
            }

            @Override
            public void onFinish() {
                final Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
                final long patt[] = new long[]{0, 500};
                myFake.setVisibility(View.INVISIBLE);
                myFirst.setVisibility(View.INVISIBLE);
                mySecond.setVisibility(View.INVISIBLE);
                myThird.setVisibility(View.INVISIBLE);
                myTryAgain.setVisibility(View.VISIBLE);
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
}