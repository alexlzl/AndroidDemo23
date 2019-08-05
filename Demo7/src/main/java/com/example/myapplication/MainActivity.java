package com.example.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView top,bottom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        top=findViewById(R.id.top);
//        bottom=findViewById(R.id.bottom);
//       new Thread(new Runnable() {
//           @Override
//           public void run() {
//               bottom.setText("bottom====");
//           }
//       }).start();
//        bottom.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                bottom.getParent().bringChildToFront(bottom);
//            }
//        });

    }

    @Override
    protected void onResume() {
        super.onResume();


    }

    @Override
    protected void onPause() {
        super.onPause();
        setContentView(R.layout.activity_main);
        top=findViewById(R.id.top);
        bottom=findViewById(R.id.bottom);
        new Thread(new Runnable() {
            @Override
            public void run() {
                bottom.setText("bottom====");
            }
        }).start();
        bottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottom.getParent().bringChildToFront(bottom);
            }
        });
    }
}
