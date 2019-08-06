package com.example.myapplication;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ServiceConnection conn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        start();
    }

    private void start(){
        Intent intent = new Intent(this, BookManagerService.class);
        intent.setAction("com.baronzhang.ipc");
        if(conn == null){
            conn = new ServiceConnection() {
                @Override
                public void onServiceConnected(ComponentName name, IBinder service) {
                    IBookManager bookManager = IBookManager.Stub.asInterface(service);
                    try {
                        // 添加书籍
                        Log.e("TAG","client==pid===before"+android.os.Process.myPid());
                        bookManager.addBook(new Book(3, "book3"));
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }

                    try {
                        // 获取书籍
                        List<Book> bookList = bookManager.getBookList();
                        for (Book book : bookList) {
                            Log.e("TAG", "---->book:" + book.toString()+"client==pid===after"+android.os.Process.myPid());
                        }
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }

                }

                @Override
                public void onServiceDisconnected(ComponentName name) {

                }
            };
        }

        // 绑定服务
        bindService(intent, conn, BIND_AUTO_CREATE);
    }
}
