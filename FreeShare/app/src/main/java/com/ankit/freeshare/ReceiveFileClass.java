package com.ankit.freeshare;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

/**
 * Created by Ankit on 3/23/2018.
 */

public class ReceiveFileClass extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.receive_file_layout);
    }

    public void receiveData(View view)
    {
        Toast.makeText(getApplicationContext(),"Will configure that part",Toast.LENGTH_SHORT).show();
    }
    public void goBackToMain(View view)
    {
        Intent toGo = new Intent();
        setResult(RESULT_OK,toGo);
        finish();
    }
}
