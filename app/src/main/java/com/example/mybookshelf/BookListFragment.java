package com.example.mybookshelf;


import android.app.Activity;
import android.app.ListFragment;
import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.mybookshelf.model.Book;

/**
 * A simple {@link Fragment} subclass.
 */
public class BookListFragment extends ListFragment {

    private static final String TAG = "BookListFragment";

    interface BookListListener {
        void itemClicked(long id);
    }

    private BookListListener mListener;

    public BookListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
/*
        String[] names = new String[Book.books.length];
        for (int i = 0; i < names.length; i++) {
            names[i] = Book.books[i].getTitle();
        }
*/

        ArrayAdapter<Book> adapter = new ArrayAdapter<>(
                inflater.getContext(),
                android.R.layout.simple_list_item_1,
                Book.books
        );
        setListAdapter(adapter);

        return super.onCreateView(inflater, container, savedInstanceState);
    }

    // onAttach(Activity) deprecated แล้ว; ใช้ onAttach(Context) แทน
    // แต่ปัญหาคือถ้า target API 23; onAttach(Context) จะไม่ทำงาน ดังนั้นจึงใช้ onAttach(Activity) ไปก่อน
    // http://stackoverflow.com/questions/32077086/android-onattachcontext-not-called-for-api-23
    @Override
    public void onAttach(Activity context) {
        super.onAttach(context);
        try {
            mListener = (BookListListener) context;
        } catch (Exception e) {
            throw new ClassCastException(context.toString() + " must implement BookListListener");
        }
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        Log.d(TAG, "ID of item clicked: " + id);

        if (mListener != null) {
            mListener.itemClicked(id);
        }
    }
}
