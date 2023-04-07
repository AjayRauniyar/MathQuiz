package com.example.mathquiz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;

public class MainActivity extends AppCompatActivity {

  ConstraintLayout firstconstraintlayout,secondconstraintlayout;
  TextView QuestiontextView,scoreTextView,answerTextView,TimerTextView;
  ArrayList<Integer> answerArrays;
  int CorrectAnswer;
  int score,numofquestion;
  Button button0,button1,button2,button3,playAgainButton;
  public  void OnClick(View view){
      firstconstraintlayout.setVisibility(View.INVISIBLE);
      secondconstraintlayout.setVisibility(View.VISIBLE);
      play();

  }


    private void play() {
      //set Question
        SetQuestion();
      //set score
        score=0;
        numofquestion=0;
        setScore();
      //set empty value in answer  Text view
        answerTextView.setText("");
      //set Timer
        SetTimer();
        
    }

    private void SetTimer() {
      new CountDownTimer(30000,1000){

          @Override
          public void onTick(long millisUntilFinished) {

              TimerTextView.setText(String.valueOf(millisUntilFinished/1000)+"s");
          }

          @Override
          public void onFinish() {
              answerTextView.setText("Game Over !");
              playAgainButton.setVisibility(View.VISIBLE);
          }
      }.start();
    }

    private void setScore() {
      scoreTextView.setText(Integer.toString(score)+"/"+Integer.toString(numofquestion));

    }

    private void SetQuestion() {
        Random random=new Random() ;
        int a=random.nextInt(50);
        int b=random.nextInt(50);
        QuestiontextView.setText(Integer.toString(a)+" + "+Integer.toString(b)+" = ?");
        CorrectAnswer=random.nextInt(4);
        answerArrays.clear();
        for(int i=0;i<4;i++){
            if(i==CorrectAnswer){
                answerArrays.add(a+b);
            }else{
                int wronganswer=random.nextInt(100);
                while(wronganswer==a+b){
                    wronganswer=random.nextInt(100);
                }
                answerArrays.add(wronganswer);
            }
        }
        button0.setText(Integer.toString(answerArrays.get(0)));
        button1.setText(Integer.toString(answerArrays.get(1)));
        button2.setText(Integer.toString(answerArrays.get(2)));
        button3.setText(Integer.toString(answerArrays.get(3)));

    }
    public void OnOptionButton(View view){
        if(Integer.toString(CorrectAnswer).equals( view.getTag().toString())){
            score++;
            answerTextView.setText("Correct !");
        }else{
            answerTextView.setText("Wrong !");

        }
        numofquestion++;
        setScore();
        SetQuestion();

    }

    public void  OnPlayAgainButton(View view){
      playAgainButton.setVisibility(View.INVISIBLE);
      play();

  }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firstconstraintlayout=findViewById(R.id.firstConstraintLayout);
        secondconstraintlayout=findViewById(R.id.secondConstraintLayout);
        QuestiontextView=findViewById(R.id.questiontextview);
        answerArrays=new ArrayList<>();
        scoreTextView=findViewById(R.id.scoretextView);
        answerTextView=findViewById(R.id.answerTextView);
        button0=findViewById(R.id.button0);
        button1=findViewById(R.id.button1);
        button2=findViewById(R.id.button2);
        button3=findViewById(R.id.button3);
        TimerTextView=findViewById(R.id.timertextView);
        playAgainButton= findViewById(R.id.playagainbutton);

    }
}