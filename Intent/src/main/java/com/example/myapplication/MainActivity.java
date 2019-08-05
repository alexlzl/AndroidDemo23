package com.example.myapplication;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.MessageQueue;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {
private   Bitmap bitmap;
MessageQueue messageQueue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Handler handler=new Handler();
         bitmap= BitmapFactory.decodeResource(getResources(),R.drawable.bg);
    }

    public void test(View view){
       Intent intent= new Intent(this,Main3Activity.class);

       intent.putExtra("bitmap",bitmap);
        startActivity(intent);
    }
}
