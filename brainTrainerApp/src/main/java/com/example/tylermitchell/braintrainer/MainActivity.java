package com.example.tylermitchell.braintrainer;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.VideoView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    // Declaring our object variables
    Button goButton;
    /*
    Button button1;
    Button button2;
    Button button3;
    Button button4;
    */
    Button restart;
    TextView countDown;
    TextView question;
    TextView score;
    TextView rightOrWrong;

    // Declaring other variables
    Boolean correct; // tells you if the selected answer is correct or not
    Integer randNum1;
    Integer randNum2;
    Integer correctAns;
    Integer timeRemaining;
    Integer operator; // a random number between 0 and 2 which determines which operator is selected
    Integer correctButton; // a random number between 0 and 3 which determines which button will have the correct answer
    ArrayList<Integer> incorrectButtons = new ArrayList<Integer>(); // contains the indices of the buttons which have incorrect answers
    Integer soNonZero;
    Integer currentScore;
    Integer numTries;
    Button[] buttons = new Button[4];
    String[] operation = {"+","-","*"};
    Random rand = new Random(); // our random number class

    public void go(View view){
        // Starts the process off
        goButton = findViewById(R.id.goButton);
        countDown = findViewById(R.id.countDown);
        question = findViewById(R.id.question);
        score = findViewById(R.id.score);
        rightOrWrong = findViewById(R.id.rightOrWrong);
        restart = findViewById(R.id.restart);
        buttons[0] = findViewById(R.id.button1);
        buttons[1] = findViewById(R.id.button2);
        buttons[2] = findViewById(R.id.button3);
        buttons[3] = findViewById(R.id.button4);
        currentScore = 0;
        numTries = 0;
        timeRemaining = 30;

        // Making our object variables visible/invisible
        goButton.setVisibility(View.GONE); // Means the object is no longer clickable
        buttons[0].setVisibility(View.VISIBLE);
        buttons[1].setVisibility(View.VISIBLE);
        buttons[2].setVisibility(View.VISIBLE);
        buttons[3].setVisibility(View.VISIBLE);
        countDown.setVisibility(View.VISIBLE);
        question.setVisibility(View.VISIBLE);
        score.setVisibility(View.VISIBLE);
        restart.setVisibility(View.INVISIBLE);

        generateQuestion();
        new CountDownTimer(timeRemaining*1000,1000){
            @Override
            public void onTick(long millisUntilFinished) {
                timeRemaining-=1;
                countDown.setText(Integer.toString(timeRemaining)+"S");
            }

            @Override
            public void onFinish() {
                rightOrWrong.setText("Done!");
                restart.setVisibility(View.VISIBLE);

            }
        }.start();



    }

    public void generateQuestion(){
        // Generates a new question

        // Generating our random numbers
        randNum1 = rand.nextInt(31);
        randNum2 = rand.nextInt(31);
        operator = rand.nextInt(3);
        correctButton = rand.nextInt(4);

        //Preparing buttons
        incorrectButtons.addAll(Arrays.asList(0,1,2,3));
        incorrectButtons.remove(correctButton);


        // Calculating the answer
        if(operator==0){
            correctAns = randNum1+randNum2;
        }else if(operator == 1){
            correctAns = randNum1 - randNum2;
        }else if(operator == 2){
            correctAns = randNum1 * randNum2;
        }

        // Posing the question to the user
          for(Integer wrong : incorrectButtons){
            soNonZero=0;
            while(soNonZero==0){
                soNonZero = rand.nextInt(20)-10;
            }
            buttons[wrong].setText(Integer.toString(correctAns + soNonZero));
        }
        buttons[correctButton].setText(Integer.toString(correctAns));
        buttons[correctButton].setText("dcobl");
        question.setText(Integer.toString(randNum1) + " " + operation[operator] + " " +Integer.toString(randNum2));



    }


    public void answer(View view){
        numTries ++;
        //Log.i("num1: ",Integer.toString(randNum1));
        //Log.i("num2: ",Integer.toString(randNum2));
        //Log.i("operator",Integer.toString(operator));
        Log.i("correctButton",Integer.toString(correctButton));
        Log.i("correct Ans",Integer.toString(correctAns));

        Button clickedButton = (Button) view;
        Log.i("Tag", clickedButton.getTag().toString());

        if(clickedButton.getTag().toString().equals(Integer.toString(correctButton))){
            currentScore +=1;
            rightOrWrong.setText("Correct!");
            rightOrWrong.setVisibility(View.VISIBLE);
        }else{
            rightOrWrong.setText("Wrong :(");
            rightOrWrong.setVisibility(View.VISIBLE);
        }
        score.setText(Integer.toString(currentScore) + "/" + Integer.toString(numTries));
        generateQuestion();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }
}
