package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

public class SingleTop extends AppCompatActivity {
    private static final String TAG = "TAG";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e(TAG, "SingleTop==onCreate");
        setContentView(R.layout.activity_single_top);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.e(TAG, "SingleTop==onRestart");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e(TAG, "SingleTop==onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e(TAG, "SingleTop==onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e(TAG, "SingleTop==onPause");
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.e(TAG, "SingleTop==onNewIntent");
    }
    public void singleTop(View view) {
        startActivity(new Intent(this, SingleTop.class));
    }
}
