// BookController.aidl
package com.mine.myapplication;

// Declare any non-default types here with import statements
import com.mine.myapplication.Book;
interface BookController {
    List<Book> getBookList();

    void addBookInOut(inout Book book);
    void addBookIn(in Book book);
    void addBookOut(out Book book);
}
