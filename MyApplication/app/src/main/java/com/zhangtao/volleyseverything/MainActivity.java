package com.zhangtao.volleyseverything;

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
    public void stringRequestTest(View view){
        startActivity(new Intent().setClass(this, StringRequestDemoActivity.class));
    }

    public void imageRequestTest(View view){
        startActivity(new Intent().setClass(this,ImageRequestDemoActivity.class));
    }

    public void imageLoader(View view){
        startActivity(new Intent().setClass(this,ImageLoaderActivity.class));
    }
}
