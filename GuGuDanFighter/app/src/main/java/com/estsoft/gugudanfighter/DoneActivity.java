package com.estsoft.gugudanfighter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

//게임이 끝나고 결과를 받아올 액티비티
public class DoneActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_done);

        Button button_replay = (Button)findViewById(R.id.button_replay);
        Button button_done = (Button)findViewById(R.id.button_done);
        button_replay.setOnClickListener(this);
        button_done.setOnClickListener(this);

        Intent getterIntent = getIntent();
        Integer total = getterIntent.getIntExtra("total", -1);
        Integer correct = getterIntent.getIntExtra("correct", -1);
        Integer wrong = getterIntent.getIntExtra("wrong", -1);
        Double percent = ((double)correct / (double)total) * 100;

        TextView recordTotal = (TextView) findViewById(R.id.record_total);
        TextView recordCorrect = (TextView) findViewById(R.id.record_correct);
        TextView recordWrong = (TextView) findViewById(R.id.record_wrong);
        recordTotal.setText(total+"번");
        recordCorrect.setText(""+correct);
        recordWrong.setText(""+wrong);
    }

    public void onClick(View v) {
        int viewId = v.getId();
        switch(viewId) {
            case R.id.button_replay :
                Intent playIntent = new Intent( DoneActivity.this, GameActivity.class);
                startActivity(playIntent);
                break;
            case R.id.button_done :
                Intent doneIntent = new Intent( DoneActivity.this, MainActivity.class);
                startActivity(doneIntent);
                break;
        }
        finish();
    }
}
