package com.example.myapplication;

import android.graphics.PixelFormat;
import android.net.Uri;
import android.os.Bundle;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView=findViewById(R.id.test1);
        int resId = getResources().getIdentifier("creord_camera", "drawable",  this.getPackageName());

        textView.setText("id==="+resId);
        new Thread(new Runnable() {
            @Override
            public void run() {
                Looper.prepare();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Button button = new Button(MainActivity.this);
                WindowManager wm = MainActivity.this.getWindowManager();
                WindowManager.LayoutParams params = new WindowManager.LayoutParams(WindowManager.LayoutParams.WRAP_CONTENT,
                        WindowManager.LayoutParams.WRAP_CONTENT,0, 0, WindowManager.LayoutParams.FIRST_SUB_WINDOW,
                        WindowManager.LayoutParams.TYPE_TOAST, PixelFormat.OPAQUE);
                wm.addView(button, params);
                button.setTextColor(MainActivity.this.getResources().getColor(R.color.colorPrimaryDark));
                button.setText("子线程更新UI");
                Looper.loop();
                Log.e("MyButton", "子线程更新UI");
            }
        }).start();
        String s="https://hd.m.gome.com.cn/jike_activate.html?couponCode=B31D53E30D8640C19F889FD6364D262E&activeType=2&activityId=b2241544&mobileNumber=13810389065";
//        String path = "http://www.test.com/payment_m.aspx?action=confirm&order_no=on123456&order_money=99.99&return_url=test/return_url.aspx";
        Uri uri = Uri.parse(s);
        String action = uri.getQueryParameter("couponCode"); //解析参数
//        String order_no = uri.getQueryParameter("order_no");//解析参数
//        String order_money = uri.getQueryParameter("order_money");//解析参数

        TextView textView=findViewById(R.id.test);
        textView.setText(action);
    }
}
