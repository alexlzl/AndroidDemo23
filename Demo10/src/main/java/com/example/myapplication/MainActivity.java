package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    public static String  value="mainactivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView textView=findViewById(R.id.tv1);
        textView.setText("pid=="+android.os.Process.myPid());
    }

    public void test(View view){
        Intent intent=new Intent(this,Main2Activity.class);
        intent.putExtra("test","alex");
        startActivity(intent);
    }
}
