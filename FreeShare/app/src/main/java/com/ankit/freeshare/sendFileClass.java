package com.ankit.freeshare;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.view.View;

/**
 * Created by Ankit on 3/23/2018.
 */

public class sendFileClass extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.send_file_layout);
    }
    public void goBack(View view)
    {
        Intent goingBack=new Intent();
        setResult(RESULT_OK,goingBack);
        finish();
    }

}
