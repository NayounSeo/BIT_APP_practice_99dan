package com.estsoft.getresult;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class CalleeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_callee);

        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("data-int", 10);
                intent.putExtra("data-string", "Hello back");
                //Result_OK : 값을 저장하고 종료한다는 듯
                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });
    }
}
