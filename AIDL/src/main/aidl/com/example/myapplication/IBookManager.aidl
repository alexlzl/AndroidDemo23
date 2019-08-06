// IBookManager.aidl
package com.example.myapplication;

// Declare any non-default types here with import statements

// 必须手动导包
import com.example.myapplication.Book;

interface IBookManager {
    List<Book> getBookList();
    void addBook(in Book book);
}