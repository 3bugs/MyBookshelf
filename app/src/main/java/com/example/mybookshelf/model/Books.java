package com.example.mybookshelf.model;

import android.content.Context;
import android.util.Log;

import com.example.mybookshelf.db.BooksDAO;
import com.example.mybookshelf.net.BooksHTTP;

import java.util.ArrayList;

/**
 * Created by Promlert on 9/16/2015.
 */
public class Books {

    private static final String TAG = "Books";

    private final ArrayList<Book> DATA = new ArrayList<Book>();

    private static Books mInstance;
    private BooksDAO mBooksDAO;
    private BooksHTTP mBooksHTTP;
    private Context mContext;

    public interface OnFinishListener {
        void onFinish(ArrayList<Book> books);
    }

    public static Books getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new Books(context);
        }
        return mInstance;
    }

    private Books(Context context) {
        mContext = context;
        mBooksDAO = new BooksDAO(context);
        mBooksHTTP = new BooksHTTP(context);
        //getAllBooksFromDB();
    }

    private void getAllBooksFromDB() {
/*
        ArrayList<Book> books = mBooksDAO.selectAll();
        DATA.clear();
        for (Book tempBook : books) {
            DATA.add(tempBook);
        }
*/
/*
        mBooksHTTP.selectAll(new BooksHTTP.OnQueryResponseListener() {
            @Override
            public void onSuccess(ArrayList<Book> books) {
                if (books != null) {
                    DATA.clear();
                    for (Book tempBook : books) {
                        DATA.add(tempBook);
                    }
                } else {
                    Log.e(TAG, "Error loading data from remote server.");
                }
            }
        });
*/
    }

    public void getAllBooks(final OnFinishListener listener) {
        // return cached data in memory
        //return DATA;
        mBooksHTTP.selectAll(new BooksHTTP.OnQueryResponseListener() {
            @Override
            public void onSuccess(ArrayList<Book> books) {
                if (books != null) {
                    DATA.clear();
                    for (Book tempBook : books) {
                        DATA.add(tempBook);
                    }
                    listener.onFinish(DATA);
                } else {
                    Log.e(TAG, "Error loading data from remote server.");
                }
            }
        });
    }

    public Book getBook(int bookIndex) {
        return DATA.get(bookIndex);
    }

    public boolean addBook(Book newBook) {
        boolean addResult = mBooksDAO.insert(
                newBook.getTitle(),
                newBook.getSubTitle(),
                newBook.getIsbn(),
                newBook.getDescription(),
                newBook.getCoverImageFilename()
        );
        getAllBooksFromDB();
        return addResult;
    }

    public int editBook(Book book) {
        int editResult = mBooksDAO.update(
                book.getId(),
                book.getTitle(),
                book.getSubTitle(),
                book.getIsbn(),
                book.getDescription(),
                book.getCoverImageFilename()
        );
        getAllBooksFromDB();
        return editResult;
    }

    public int deleteBook(long bookId) {
        int deleteResult = mBooksDAO.delete(bookId);
        getAllBooksFromDB();
        return deleteResult;
    }

    public int deleteBook(Book book) {
        int deleteResult = mBooksDAO.delete(book.getId());
        getAllBooksFromDB();
        return deleteResult;
    }

    public int count() {
        return DATA.size();
    }
}
