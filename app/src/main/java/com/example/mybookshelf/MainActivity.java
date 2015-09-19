package com.example.mybookshelf;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.mybookshelf.db.BooksDAO;
import com.example.mybookshelf.model.Book;
import com.example.mybookshelf.model.Books;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements BookListFragment.BookListListener {

    private static final String TAG = "MainActivity";

    //public static final ArrayList<Book> BOOKS = new ArrayList<Book>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //addSampleBookData();

        setContentView(R.layout.activity_main);

        //listAllBooks();

/*
        FragmentManager fm = getFragmentManager();
        BookDetailsFragment fragment = (BookDetailsFragment) fm.findFragmentById(R.id.details_frag);

        fragment.setBook(3);
*/
    }

    private void addSampleBookData() {
        String[] titleArray = getResources().getStringArray(R.array.title_array);
        String[] subTitleArray = getResources().getStringArray(R.array.subtitle_array);
        String[] isbnArray = getResources().getStringArray(R.array.isbn_array);
        String[] descriptionArray = getResources().getStringArray(R.array.description_array);
        String[] coverImageFilenameArray = getResources().getStringArray(R.array.cover_image_filename_array);

        try {
            for (int i = 0; i < titleArray.length; i++) {
                Book book = new Book(i, titleArray[i], subTitleArray[i], isbnArray[i], descriptionArray[i], coverImageFilenameArray[i]);
                //BOOKS.add(book);

                Utils.copyFileFromAssetsToImagesDir(this, coverImageFilenameArray[i]);
            }
        } catch (IOException e) {
            Log.e(TAG, "Error copying image file from assets to images dir.");
            e.printStackTrace();
        }
    }

/*    private void listAllBooks() {
        String msg = "";
        Book[] books = Book.books;

        for (Book aBook : books) {
            msg += "Title: " + aBook.getTitle() + "\n";
            msg += "Sub-title: " + aBook.getSubTitle() + "\n";
            msg += "ISBN: " + aBook.getIsbn() + "\n";
            msg += "----------------------------\n";
        }

        new AlertDialog.Builder(this)
                .setTitle("All BooksDAO")
                .setMessage(msg)
                .setPositiveButton("OK", null)
                .show();
    }*/

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intent = new Intent(this, Test.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() > 0 ){
            getFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void itemClicked(int index) {
        //BookDetailsFragment fragment = new BookDetailsFragment();
        //fragment.setBook(id);

        View fragmentContainer = findViewById(R.id.fragment_container);
        if (fragmentContainer != null) {
            BookDetailsFragment fragment = BookDetailsFragment.newInstance(index);

            FragmentManager fm = getFragmentManager();
            FragmentTransaction transaction = fm.beginTransaction();
            transaction.replace(R.id.fragment_container, fragment);
            transaction.addToBackStack(null);
            transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            transaction.commit();
        } else {
            Intent intent = new Intent(this, DetailsActivity.class);
            intent.putExtra(DetailsActivity.KEY_BOOK_INDEX, index);
            startActivity(intent);
        }
    }
}
