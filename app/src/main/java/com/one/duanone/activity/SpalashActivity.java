package com.one.duanone.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.one.duanone.R;

public class SpalashActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spalash);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Intent intent=new Intent(SpalashActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        }).start();
    }
}
