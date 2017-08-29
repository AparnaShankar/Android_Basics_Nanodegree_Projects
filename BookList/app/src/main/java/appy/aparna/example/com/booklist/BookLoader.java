package appy.aparna.example.com.booklist;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.ArrayList;

/**
 * Created by Administrator on 5/27/2017.
 */

public class BookLoader extends AsyncTaskLoader<ArrayList<Book>> {


    /**
     * Tag for log messages
     */
    private static final String LOG_TAG = Book.class.getName();

    /**
     * Query URL
     */
    private String mUrl;

    /**
     * Constructs a new {@link BookLoader}.
     *
     * @param context of the activity
     * @param url     to load data from
     */
    public BookLoader(Context context, String url) {
        super(context);
        mUrl = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    /**
     * This is on a background thread.
     */
    @Override
    public ArrayList<Book> loadInBackground() {
        if (mUrl == null) {
            return null;
        }

        // Perform the network request, parse the response, and extract a list of books.
        ArrayList<Book> books = QueryUtils.fetchBookData(mUrl);
        return books;
    }
}

