package com.example.myapplication;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
     private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView=findViewById(R.id.text);
    }

    public void test(View view){
        Toast.makeText(this,"test",Toast.LENGTH_LONG).show();
        Context context;
        try {
            context = createPackageContext("com.example.demo2", Context.CONTEXT_INCLUDE_CODE
                    | Context.CONTEXT_IGNORE_SECURITY);
            textView.setText(context.getResources().getText(R.string.message));
        } catch (PackageManager.NameNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            Toast.makeText(this,"error",Toast.LENGTH_LONG).show();
        }
    }
}
