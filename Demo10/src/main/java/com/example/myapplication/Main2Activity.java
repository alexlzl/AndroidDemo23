package com.example.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        String v=getIntent().getStringExtra("test");
         textView=findViewById(R.id.name);
        textView.setText(v);

    }

    public void test(View view){
        textView.setText(MainActivity.value+"=="+ android.os.Process.myPid());
    }
}
