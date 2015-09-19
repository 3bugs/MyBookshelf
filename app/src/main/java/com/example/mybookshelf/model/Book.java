package com.example.mybookshelf.model;

import com.example.mybookshelf.R;

/**
 * Created by Promlert on 9/7/2015.
 */
public class Book {
    private long mId;
    private String mTitle;
    private String mSubTitle;
    private String mIsbn;
    private String mDescription;
    private String mCoverImageFilename;

    public Book(long id, String title, String subTitle, String isbn, String description, String coverImageFilename) {
        this.mId = id;
        this.mTitle = title;
        this.mSubTitle = subTitle;
        this.mIsbn = isbn;
        this.mDescription = description;
        this.mCoverImageFilename = coverImageFilename;
    }

    public long getId() {
        return mId;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getSubTitle() {
        return mSubTitle;
    }

    public String getIsbn() {
        return mIsbn;
    }

    public String getDescription() {
        return mDescription;
    }

    public String getCoverImageFilename() {
        return mCoverImageFilename;
    }

    @Override
    public String toString() {
        return getTitle();
    }

/*
    public static final Book[] books = {
            new Book(
                    "คู่มือเขียนแอพ Android ด้วย Android Studio",
                    "",
                    "9786162045585",
                    "สอนเขียนแอพ Android โดยใช้ Android Studio ตั้งแต่การติดตั้งซอฟต์แวร์ต่างๆที่จำเป็น ไปจนถึงการส่งแอพเข้าสู่ Google Play อธิบายเนื้อหาอย่างเป็นลำดับขั้นตอน เพื่อให้คุณมีพื้นฐานที่แข็งแรงสำหรับการก้าวไปเป็นนักพัฒนามืออาชีพต่อไป",
                    R.drawable.android_studio
            ),
            new Book(
                    "Head First Android Development",
                    "A Brain-Friendly Guide",
                    "9781449362188",
                    "If you have an idea for a killer Android app, this book will help you build your first working application in a jiffy. You’ll learn hands-on how to structure your app, design interfaces, create a database, make your app work on various smartphones and tablets, and much more. It’s like having an experienced Android developer sitting right next to you! All you need is some Java know-how to get started.",
                    R.drawable.head_first_android_development
            ),
            new Book(
                    "Code",
                    "The Hidden Language of Computer Hardware and Software",
                    "9780735611313",
                    "What do flashlights, the British invasion, black cats, and seesaws have to do with computers? In CODE, they show us the ingenious ways we manipulate language and invent new means of communicating with each other. And through CODE, we see how this ingenuity and our very human compulsion to communicate have driven the technological innovations of the past two centuries.\n\n" +
                            "Using everyday objects and familiar language systems such as Braille and Morse code, author Charles Petzold weaves an illuminating narrative for anyone who’s ever wondered about the secret inner life of computers and other smart machines.\n\n" +
                            "It’s a cleverly illustrated and eminently comprehensible story—and along the way, you’ll discover you’ve gained a real context for understanding today’s world of PCs, digital media, and the Internet. No matter what your level of technical savvy, CODE will charm you—and perhaps even awaken the technophile within.",
                    R.drawable.code
            ),
            new Book(
                    "The Moscow Puzzles",
                    "359 Mathematical Recreations",
                    "9780486270784",
                    "This is, quite simply, the best and most popular puzzle book ever published in the Soviet Union. Since its first appearance in 1956 there have been eight editions as well as translations from the original Russian into Ukrainian, Estonian, Lettish, and Lithuanian. Almost a million copies of the Russian version alone have been sold.\n\n" +
                            "Part of the reason for the book's success is its marvelously varied assortment of brainteasers ranging from simple \"catch\" riddles to difficult problems (none, however, requiring advanced mathematics). Many of the puzzles will be new to Western readers, while some familiar problems have been clothed in new forms. Often the puzzles are presented in the form of charming stories that provide non-Russian readers with valuable insights into contemporary Russian life and customs. In addition, Martin Gardner, former editor of the Mathematical Games Department, Scientific American, has clarified and simplified the book to make it as easy as possible for an English-reading public to understand and enjoy. He has been careful, moreover, to retain nearly all the freshness, warmth, and humor of the original.\n\n" +
                            "Lavishly illustrated with over 400 clear diagrams and amusing sketches, this inexpensive edition of the first English translation will offer weeks or even months of stimulating entertainment. It belongs in the library of every puzzlist or lover of recreational mathematics.",
                    R.drawable.the_moscow_puzzles
            )
    };
*/
}
