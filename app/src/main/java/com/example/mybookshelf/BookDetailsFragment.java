package com.example.mybookshelf;


import android.graphics.Bitmap;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.mybookshelf.model.Book;
import com.example.mybookshelf.model.Books;


/**
 * A simple {@link Fragment} subclass.
 */
public class BookDetailsFragment extends Fragment {

    private static final String ARG_BOOK_INDEX = "book_index";
    private int mBookIndex;

    private Books mBooks;

    public BookDetailsFragment() {
        // Required empty public constructor
    }

    public static BookDetailsFragment newInstance(int bookIndex) {
        BookDetailsFragment fragment = new BookDetailsFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_BOOK_INDEX, bookIndex);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        if (getArguments() != null) {
            mBookIndex = getArguments().getInt(ARG_BOOK_INDEX);
        }

        mBooks = Books.getInstance(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_book_details, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();

        View view = getView();
        if (view != null) {
            TextView titleTextView = (TextView) view.findViewById(R.id.book_title);
            TextView subTitleTextView = (TextView) view.findViewById(R.id.book_sub_title);
            TextView isbnTextView = (TextView) view.findViewById(R.id.book_isbn);
            TextView descriptionTextView = (TextView) view.findViewById(R.id.book_description);
            ImageView coverImageView = (ImageView) view.findViewById(R.id.book_cover_image);

            //Book book = Book.books[mBookIndex];
            //Book book = MainActivity.booksDB.selectAll().get(mBookIndex);

            //Book book = MainActivity.BOOKS.get(mBookIndex);
            Book book = mBooks.getBook(mBookIndex);

            titleTextView.setText(book.getTitle());
            subTitleTextView.setText(book.getSubTitle());
            isbnTextView.setText(book.getIsbn());
            descriptionTextView.setText(book.getDescription());

//            Bitmap coverImageBitmap = Utils.getImageBitmap(getActivity(), book.getCoverImageFilename());
//            coverImageView.setImageBitmap(coverImageBitmap);

            String imageUrl = "http://192.168.56.1/mybookshelf/images/" + book.getCoverImageFilename();
            Glide.with(this).load(imageUrl).placeholder(R.mipmap.ic_launcher).into(coverImageView);
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_book_details, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_edit) {
            Book oldBook = mBooks.getBook(mBookIndex);
            Book tempBook = new Book(
                    oldBook.getId(),
                    "Hello Hello สวัสดี",
                    oldBook.getSubTitle(),
                    oldBook.getIsbn(),
                    oldBook.getDescription(),
                    oldBook.getCoverImageFilename()
            );
            mBooks.editBook(tempBook);
            return true;
        } else if (id == R.id.action_delete) {

            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /*
    public void setBook(long bookId) {
        this.mBookIndex = bookId;
    }
*/

}
