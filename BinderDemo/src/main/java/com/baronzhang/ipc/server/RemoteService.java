package com.baronzhang.ipc.server;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import com.baronzhang.ipc.Book;

import java.util.ArrayList;
import java.util.List;

public class RemoteService extends Service {

    private List<Book> books = new ArrayList<>();

    public RemoteService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("TAG", "RemoteService"+String.valueOf(android.os.Process.myPid()));
        Book book = new Book();
        book.setName("三体");
        book.setPrice(88);
        books.add(book);
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.e("TAG", "onBind");
        return bookManager;
    }

    private final Stub bookManager = new Stub() {
        @Override
        public List<Book> getBooks() throws RemoteException {
            Log.e("TAG", "RemoteService===getBooks"+String.valueOf(android.os.Process.myPid()));
            synchronized (this) {
                if (books != null) {
                    return books;
                }
                return new ArrayList<>();
            }
        }

        @Override
        public void addBook(Book book) throws RemoteException {
            Log.e("TAG", "RemoteService==addBook"+String.valueOf(android.os.Process.myPid()));
            synchronized (this) {
                if (books == null) {
                    books = new ArrayList<>();
                }

                if (book == null)
                    return;

                book.setPrice(book.getPrice() * 2);
                books.add(book);

                Log.e("TAG", "RemoteService==addBook: " + book.toString());
            }
        }
    };
}
