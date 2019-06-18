package com.sujithkumar.braintrainer;

import android.os.CountDownTimer;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button startButton, choice0, choice1, choice2, choice3, playAgain;
    TextView timerTextView, resultTextView, scoreTextView, questionTextView;
    ConstraintLayout constraintLayout;
    int score = 0, questions = 0, a = 0, b = 0, wrongChoice;
    int [] choices;
    Random random;
    CountDownTimer timer;

    public void chooseAnswer(View view) {
        questions++;
        if (choices[Integer.parseInt(view.getTag().toString())] == a + b) {
            score++;
            resultTextView.setText("Correct :)");
        } else
            resultTextView.setText("Wrong :(");
        setQuestion();
        scoreTextView.setText(score + "/" + questions);

    }

    public void playAgain(View view) {

        choice0.setEnabled(true);
        choice1.setEnabled(true);
        choice2.setEnabled(true);
        choice3.setEnabled(true);
        score = 0;
        questions = 0;
        start(view);
        resultTextView.setText("");
    }

    public void start (View view) {
        startButton.setVisibility(View.INVISIBLE);
        constraintLayout.setVisibility(View.VISIBLE);
        timer = new CountDownTimer(60200, 1000) {
            @Override
            public void onTick(long l) {
                timerTextView.setText((l / 1000) + "s");
            }

            @Override
            public void onFinish() {
                playAgain.setVisibility(View.VISIBLE);
                choice0.setEnabled(false);
                choice1.setEnabled(false);
                choice2.setEnabled(false);
                choice3.setEnabled(false);
                resultTextView.setText("Done!");
            }
        }.start();
        setQuestion();
        scoreTextView.setText(score + "/" + questions);

    }

    public void setQuestion() {
        a = random.nextInt(100);
        b = random.nextInt(100);
        questionTextView.setText(a + " + " + b);
        for (int i = 0; i < 3; i++) {
            wrongChoice = random.nextInt(200);
            while (wrongChoice == (a + b)) {
                wrongChoice = random.nextInt(200);
            }
            choices[i] = wrongChoice;
        }
        choices[random.nextInt(4)] = a + b;
        choice0.setText(String.valueOf(choices[0]));
        choice1.setText(String.valueOf(choices[1]));
        choice2.setText(String.valueOf(choices[2]));
        choice3.setText(String.valueOf(choices[3]));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startButton = findViewById(R.id.startButton);
        choice0 = findViewById(R.id.choice0);
        choice1 = findViewById(R.id.choice1);
        choice2 = findViewById(R.id.choice2);
        choice3 = findViewById(R.id.choice3);
        constraintLayout = findViewById(R.id.constraintLayout);
        timerTextView = findViewById(R.id.timerTextView);
        scoreTextView = findViewById(R.id.scoreTextView);
        resultTextView = findViewById(R.id.resultTextView);
        questionTextView = findViewById(R.id.questionTextView);
        playAgain = findViewById(R.id.playAgain);
        random = new Random();
        choices = new int[4];
    }
}
