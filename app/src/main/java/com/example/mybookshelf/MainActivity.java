package com.example.mybookshelf;

import android.app.AlertDialog;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telecom.Call;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.mybookshelf.model.Book;

public class MainActivity extends AppCompatActivity implements BookListFragment.BookListListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //listAllBooks();

/*
        FragmentManager fm = getFragmentManager();
        BookDetailsFragment fragment = (BookDetailsFragment) fm.findFragmentById(R.id.details_frag);

        fragment.setBook(3);
*/

    }

    private void listAllBooks() {
        String msg = "";
        Book[] books = Book.books;

        for (Book aBook : books) {
            msg += "Title: " + aBook.getTitle() + "\n";
            msg += "Sub-title: " + aBook.getSubTitle() + "\n";
            msg += "ISBN: " + aBook.getIsbn() + "\n";
            msg += "----------------------------\n";
        }

        new AlertDialog.Builder(this)
                .setTitle("All Books")
                .setMessage(msg)
                .setPositiveButton("OK", null)
                .show();
    }

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
    public void itemClicked(long id) {
        //BookDetailsFragment fragment = new BookDetailsFragment();
        //fragment.setBook(id);

        View fragmentContainer = findViewById(R.id.fragment_container);
        if (fragmentContainer != null) {
            BookDetailsFragment fragment = BookDetailsFragment.newInstance(id);

            FragmentManager fm = getFragmentManager();
            FragmentTransaction transaction = fm.beginTransaction();
            transaction.replace(R.id.fragment_container, fragment);
            transaction.addToBackStack(null);
            transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            transaction.commit();
        } else {
            Intent intent = new Intent(this, DetailsActivity.class);
            intent.putExtra(DetailsActivity.KEY_BOOK_ID, id);
            startActivity(intent);
        }
    }
}
