package com.example.mybookshelf;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mybookshelf.model.Book;


/**
 * A simple {@link Fragment} subclass.
 */
public class BookDetailsFragment extends Fragment {

    private static final String ARG_BOOK_ID = "book_id";
    private long mBookId;

    public BookDetailsFragment() {
        // Required empty public constructor
    }

    public static BookDetailsFragment newInstance(long bookId) {
        BookDetailsFragment fragment = new BookDetailsFragment();
        Bundle args = new Bundle();
        args.putLong(ARG_BOOK_ID, bookId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mBookId = getArguments().getLong(ARG_BOOK_ID);
        }
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

            Book book = Book.books[(int) mBookId];
            titleTextView.setText(book.getTitle());
            subTitleTextView.setText(book.getSubTitle());
            isbnTextView.setText(book.getIsbn());
            descriptionTextView.setText(book.getDescription());
            coverImageView.setImageResource(book.getCoverImageFilename());
        }
    }

/*
    public void setBook(long bookId) {
        this.mBookId = bookId;
    }
*/

}
