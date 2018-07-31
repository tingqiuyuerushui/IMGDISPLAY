package com.mine;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.mine.myapplication.Book;
import com.mine.myapplication.BookController;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private final String TAG = "Client";

    private BookController bookController;

    private boolean connected;

    private List<Book> bookList;

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            bookController = BookController.Stub.asInterface(service);
            connected = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            connected = false;
        }
    };

    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn_getBookList:
                    if (connected) {
                        try {
                            bookList = bookController.getBookList();
                        } catch (RemoteException e) {
                            e.printStackTrace();
                        }
                        log();
                    }
                    break;
                case R.id.btn_addBook_inOut:
                    if (connected) {
                        Book book = new Book("这是一本新书 InOut");
                        try {
                            bookController.addBookInOut(book);
                            Log.e(TAG, "向服务器以InOut方式添加了一本新书");
                            Log.e(TAG, "新书名：" + book.getName());
                        } catch (RemoteException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                case R.id.btn_addBook_in:
                    if (connected) {
                        Book book = new Book("这是一本新书 In");
                        try {
                            bookController.addBookIn(book);
                            Log.e(TAG, "向服务器以In方式添加了一本新书");
                            Log.e(TAG, "新书名：" + book.getName());
                        } catch (RemoteException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                case R.id.btn_addBook_out:
                    if (connected) {
                        Book book = new Book("这是一本新书 Out");
                        try {
                            bookController.addBookOut(book);
                            Log.e(TAG, "向服务器以Out方式添加了一本新书");
                            Log.e(TAG, "新书名：" + book.getName());
                        } catch (RemoteException e) {
                            e.printStackTrace();
                        }
                    }
                    break;

            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn_getBookList).setOnClickListener(clickListener);
        findViewById(R.id.btn_addBook_inOut).setOnClickListener(clickListener);
        findViewById(R.id.btn_addBook_out).setOnClickListener(clickListener);
        findViewById(R.id.btn_addBook_in).setOnClickListener(clickListener);
        bindService();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (connected) {
            unbindService(serviceConnection);
        }
    }

    private void bindService() {
        Intent intent = new Intent();
        intent.setPackage("com.mine.myapplication");
        intent.setAction("com.mine.myapplication.action");
        bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
    }

    private void log() {
        for (Book book : bookList) {
            Log.e(TAG, book.toString());
        }
    }
}
