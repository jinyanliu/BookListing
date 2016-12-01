package se.sugarest.jane.booklisting;

/**
 * Created by jane on 11/30/16.
 */

import android.content.Context;
import android.util.Log;
import android.support.v4.content.AsyncTaskLoader;

import java.util.List;

/**
 * Loads a list of books by using an AsyncTask to perform the network request
 * to the given URL.
 */
public class BookLoader extends AsyncTaskLoader<List<Book>> {

    /**
     * Tag for log messages
     */
    private static final String LOG_TAG = BookLoader.class.getName();

    /**
     * Query URL
     */
    private String mUrl;

    /**
     * Constructs a new {@link BookLoader}
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

        Log.i(LOG_TAG, "TEST: onStartLoading");
    }

    /**
     * This is on a background thread.
     */
    @Override
    public List<Book> loadInBackground() {
        if (mUrl == null) {
            return null;
        }

        Log.i(LOG_TAG, "TEST: loadInBackground");

        //Perform the network request, parse the response, and extract a list of books.
        List<Book> books = QueryUtils.fetchBookData(mUrl);

        Log.i(LOG_TAG, "TEST: fetchEarthquakeData");

        return books;
    }
}
