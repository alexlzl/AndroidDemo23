package com.example.myapplication;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class Main3Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        test();
    }

    public void test(){
        new Thread() {
            @Override
            public void run() {
                setName("thread-one");
                Looper.prepare();

                final Looper threadOneLooper = Looper.myLooper();

                new Thread() {
                    @Override
                    public void run() {
                        setName("thread-two");
                        Handler handler = new Handler(threadOneLooper);

                        handler.post(() -> {
                            Log.v("test", Thread.currentThread().getName());
                        });
                    }
                }.start();

                Looper.loop();
            }
        }.start();
    }
}
