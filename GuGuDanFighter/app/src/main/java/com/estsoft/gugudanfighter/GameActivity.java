package com.estsoft.gugudanfighter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

/*TODO : 메소드 정리*/

public class GameActivity extends AppCompatActivity implements View.OnClickListener {
    private final int numOfButtons = 9;
    private Button[] buttons;
    private Timer timer = new Timer();
    private int gameTotal;
    private int gameCorrect;
    private int gameWrong;
    private int hereIsTheResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        timer.schedule( new NYTimerTask(), 1000, 1000);

        final int[] buttonIds = {R.id.button1, R.id.button2, R.id.button3,
                            R.id.button4, R.id.button5, R.id.button6,
                            R.id.button7, R.id.button8, R.id.button9};
        buttons = new Button[numOfButtons];
        for( int i=0; i<numOfButtons; i++) {
            buttons[i] = (Button) findViewById(buttonIds[i]);
            buttons[i].setOnClickListener(this);
        }

        hereIsTheResult = newQuiz();
    }

    @Override
    public void onClick(View v) {
        int viewId = v.getId();
        if (viewId == buttons[hereIsTheResult].getId()) {
            gameCorrect++;
        } else {
            gameWrong++;
        }
        gameTotal++;

        hereIsTheResult = newQuiz();
    }
    //새로 짜는 newQuiz().........
    private int newQuiz() {
        int[] randomAnswers = new int[numOfButtons];
        //문제 내기
        final int leftOprnd = randomize(1, 9);
        final int rightOprnd = randomize(1, 9);
        final int result = leftOprnd * rightOprnd;

        TextView leftView = (TextView) findViewById(R.id.leftOprnd);
        leftView.setText(""+leftOprnd);
        TextView rightView = (TextView) findViewById(R.id.rightOprnd);
        rightView.setText(""+rightOprnd);

        //버튼 조정
        for(int i=0; i<numOfButtons; i++) {
            int random1 = randomize(1, 9);
            int random2 = randomize(1, 9);
            int temprandom = random1*random2;
            Boolean isAlready = whatArrayContains(randomAnswers, temprandom, result);
            if(isAlready) {
                continue;
            }
            randomAnswers[i] = temprandom;
            buttons[i].setText(""+randomAnswers[i]);
        }
        hereIsTheResult = randomize(0, 8);
        randomAnswers[hereIsTheResult] = result;
        buttons[hereIsTheResult].setText(""+randomAnswers[hereIsTheResult]);

        printScore();

        return hereIsTheResult;
    }

    private boolean whatArrayContains( int[] array, int val1, int val2 ) {
        for( final int i : array ) {
            if( i == val1 || i == val2) {
                return true;
            }
        }
        return false;
    }

    private void printScore() {
        if(gameTotal != 0) {
            TextView resultView = (TextView) findViewById(R.id.resultView);
            resultView.setText(gameCorrect + " / " + gameTotal);
        }
    }

    private int randomize( int from, int to ) {
        return (int)( Math.random() * to ) + from;
    }

    private class NYTimerTask extends TimerTask {
        private int seconds=0;
        @Override
        public void run() {
            /*
            * Game Activity는 여기서 끝납니다!
            */
            if(++seconds > 5) {
                timer.cancel();
                Intent timerIntent = new Intent( GameActivity.this, DoneActivity.class);
                timerIntent.putExtra("total", gameTotal);
                timerIntent.putExtra("correct", gameCorrect);
                timerIntent.putExtra("wrong", gameWrong);
                startActivity(timerIntent);
                /*finish는 activity를 사라지게!*/
                finish();
                /* return 있으면 에러나나..? 에러는 Done 때문이었을까 이것 때문이었을까
                *  Done 때문이었던 듯ㅋㅋㅋㅋ return은 없어도 되는 것 같다.*/
            }
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    TextView secondsView = (TextView)findViewById(R.id.timeLeft);
                    secondsView.setText(""+(30-seconds));
                }
            });
        }
    }
}
