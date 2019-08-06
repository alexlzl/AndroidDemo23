package com.example.myapplication;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class BookManagerService extends Service {
    private ArrayList<Book> mBookList = new ArrayList<>();
    private Binder mBinder = new IBookManager.Stub(){
        @Override
        public List<Book> getBookList() throws RemoteException {
            Log.e("TAG","server==pid==getBookList"+android.os.Process.myPid());
            return mBookList;
        }

        @Override
        public void addBook(Book book) throws RemoteException {

            Log.e("TAG","server==pid===addBook"+android.os.Process.myPid());

            mBookList.add(book);
        }
    };

    public BookManagerService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("TAG", "---->远程服务启动");
//        mBookList.add(new Book(1, "book1"));
//        mBookList.add(new Book(2, "book2"));
    }
}
