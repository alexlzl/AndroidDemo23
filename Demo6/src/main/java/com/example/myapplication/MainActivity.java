package com.example.myapplication;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private Handler handler;
    private TextView textView;
    int i = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.test);
        handler = new MyHandler();
        Message message = Message.obtain();
        message.what = i;
        handler.sendMessage(message);
        textView.setText("tset");
        testLoop();
    }

    class MyHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            textView.setText(msg.what + "");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            i++;
            Message message=Message.obtain();
            message.what=i;
            handler.sendMessage(message);


        }
    }

    private void testLoop() {
        for (; ; ) {
            Log.e("TAG", "handle message==========");
        }
    }
}
