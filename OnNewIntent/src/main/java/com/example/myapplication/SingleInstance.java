package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

public class SingleInstance extends AppCompatActivity {
    private static final String TAG = "TAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e(TAG, "SingleInstance==onCreate");
        setContentView(R.layout.activity_single_instance);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.e(TAG, "SingleInstance==onRestart");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e(TAG, "SingleInstance==onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e(TAG, "SingleInstance==onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e(TAG, "SingleInstance==onPause");
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.e(TAG, "SingleInstance==onNewIntent");
    }

    public void singleInstanceChild(View view) {
        startActivity(new Intent(this, SingleInstanceChild.class));
    }
}
