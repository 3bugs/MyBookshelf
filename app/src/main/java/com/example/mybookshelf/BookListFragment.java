package com.example.mybookshelf;


import android.app.Activity;
import android.app.Fragment;
import android.app.ListFragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.mybookshelf.model.Book;
import com.example.mybookshelf.model.Books;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class BookListFragment extends ListFragment {

    private static final String TAG = "BookListFragment";

    private ArrayAdapter<Book> mAdapter;
    private Books mBooks;

    interface BookListListener {
        void itemClicked(int id);
    }

    private BookListListener mListener;

    public BookListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        mBooks = Books.getInstance(getActivity());
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

        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();

        mBooks.getAllBooks(new Books.OnFinishListener() {
            @Override
            public void onFinish(ArrayList<Book> books) {
                mAdapter = new ArrayAdapter<Book>(
                        getActivity(),
                        android.R.layout.simple_list_item_1,
                        books
                );
                setListAdapter(mAdapter);
            }
        });

        getListView().setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Log.i(TAG, "position: " + position + ", id: " + id);

                Book oldBook = mAdapter.getItem(position);
                Book tempBook = new Book(
                        oldBook.getId(),
                        "Hello Hello สวัสดี",
                        oldBook.getSubTitle(),
                        oldBook.getIsbn(),
                        oldBook.getDescription(),
                        oldBook.getCoverImageFilename()
                );
                mBooks.editBook(tempBook);

                //mBooks.deleteBook(bookToDelete);
                mAdapter.notifyDataSetChanged();
                return true;
            }
        });
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
            mListener.itemClicked(position);
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_book_list, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_add) {
            Book book = new Book(
                    0,
                    "title",
                    "subtitle",
                    "isbn",
                    "description",
                    "image.jpg"
            );

/*
            BooksDAO booksDAO = new BooksDAO(getActivity());
            booksDAO.insert(book);

            ArrayList<Book> books = booksDAO.selectAll();
            MainActivity.BOOKS.clear();
            for (Book tempBook : books) {
                MainActivity.BOOKS.add(tempBook);
            }
*/
            mBooks.addBook(book);
            mAdapter.notifyDataSetChanged();

            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
