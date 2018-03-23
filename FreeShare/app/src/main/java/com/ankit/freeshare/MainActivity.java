package com.ankit.freeshare;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void toSendActivity(View view) {
        Intent changing=new Intent(this,sendFileClass.class);
        startActivityForResult(changing,1);
    }
    public void toReveiveActivity(View view) {
        Intent changeToReceive=new Intent(this,ReceiveFileClass.class);
        startActivityForResult(changeToReceive,2);
    }
}
