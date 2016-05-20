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
    }

    @Override
    public void onClick(View v) {
        newQuiz();

    }

    /*OnClick의 내용이 되어줄 메소드.
    * ☆클릭할때마다☆ 실행
    * ===================================
    * 홀.... if(버튼 개수만큼) 안 넣어주면 버튼이 안변하네?!;; 왜지!*/
   /* private void newQuiz() {
        /* TODO
        * A  )
        * 뭔가 이렇게 하면 별로일까!
        * 답은 랜덤한 버튼 아이디에 넣어주고
        * == null 버튼들에 대해 랜덤 값 생성.
        * 이래도 중복 검사는 해야겠지만...
        * B  )
        * 모든 버튼에 대해서 랜덤 값 생성 후
        * 버튼 하나 골라서 답을 넣어주는 건?
        * 중복검사 해야겠지만 ㅜㅠㅜ
        * 이따가 생각해보자!*/
/*                  A
        int[] randomAnswers = new int[numOfButtons];
        int hereIsTheResult = randomize(1, 9);

        System.out.println();
        System.out.println("here is the result : "+hereIsTheResult );

        final int leftOprnd = randomize(1, 9);
        final int rightOprnd = randomize(1, 9);
        final int result = leftOprnd * rightOprnd;

        System.out.println("result : "+result);
        System.out.println();

        TextView leftView = (TextView) findViewById(R.id.leftOprnd);
        leftView.setText(""+leftOprnd);
        TextView rightView = (TextView) findViewById(R.id.rightOprnd);
        rightView.setText(""+rightOprnd);

        for(int i=0; i<numOfButtons; i++) {
           if(i != hereIsTheResult) {
               randomAnswers[hereIsTheResult] = result;
               buttons[hereIsTheResult].setText("" + randomAnswers[hereIsTheResult]);
           } else {
               randomAnswers[i] = randomize(1, 9)*randomize(1, 9);
               //TODO
               //사실 여기서 중복까지 검사해서 넣어주는 것이 좋겠어.
               //그런 메소드를 하나 구현합시다!//
               Boolean isAlreadyExist = intArrayContains(randomAnswers, randomAnswers[i]);
               if (isAlreadyExist) {
                    //이거 아닌 것 같아...
                    // randomAnswers에 넣어줄 값들의 중복검사가 선행되어야만 하겠어ㅋㅋㅋㅋ
                   randomAnswers[i] = randomize(1, 9)*randomize(1, 9);
               }
               buttons[i].setText(""+randomAnswers[i]);
            }
        }
        printScore();
    }*/

    private boolean intArrayContains( int[] array, int val ) {
        for( final int i : array ) {
            if( i == val ) {
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
