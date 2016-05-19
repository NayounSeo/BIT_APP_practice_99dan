package com.estsoft.implicityintent;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private static final String ACTION_SHARED_ACTIVITY = "com.estsoft.android.action.SHARE_ACTION";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button1 = (Button)findViewById(R.id.button1);
        Button button2 = (Button)findViewById(R.id.button2);
        Button button3 = (Button)findViewById(R.id.button3);
        Button button4 = (Button)findViewById(R.id.button4);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        int viewId = v.getId();
        switch(viewId) {
            case R.id.button1 :
                Intent intent = new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:010-8523-6378"));
                startActivity(intent);
                break;
            case R.id.button2 :
                Intent intent1 = new Intent(Intent.ACTION_PICK);
                intent1.setData(Uri.parse("content://com.android.contacts/data/phones"));
                startActivity(intent1);
                break;
            case R.id.button3 :
                Intent intent2 = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.estsoft.com"));
                startActivity(intent2);
                break;
            case R.id.button4 :
                /*설정만 되어 있다면 다른 어플리케이션의 ACTIVITY도 사용 가능..!*/
                Intent intent3 = new Intent(ACTION_SHARED_ACTIVITY);
                startActivity(intent3);
            default :
                break;
        }

    }
}
