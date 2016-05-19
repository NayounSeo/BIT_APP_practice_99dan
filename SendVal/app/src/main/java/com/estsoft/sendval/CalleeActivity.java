package com.estsoft.sendval;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class CalleeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_callee);

        Intent intent = getIntent();

        Integer i = intent.getIntExtra("Integer data", -1);
        String s = intent.getStringExtra("String data");

        String result = "Integer data : "+i+", String data : "+s;
        TextView tv = (TextView)findViewById(R.id.textView3);
        tv.setText(result);
    }
}
