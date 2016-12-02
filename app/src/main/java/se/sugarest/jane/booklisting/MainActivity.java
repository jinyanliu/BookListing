package se.sugarest.jane.booklisting;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Book>> {

    public static final String LOG_TAG = MainActivity.class.getName();

    /**
     * Constant value for the book loader ID. Can choose any integer.
     * This really only comes into play if using multiple loaders.
     */
    private static final int BOOK_LOADER_ID = 1;

    /**
     * URL for book data from the Google Books
     */
    private String google_books_request_url;

    /**
     * Adapter for the list of books
     */
    private BookAdapter mAdapter;

    /**
     * TextView that is displayed when the list is empty
     */
    private TextView mEmptyStateTextView;

    /**
     * ProgressBar when loading data from web server
     */
    private View loadingIndicator;

    @Override
    public Loader<List<Book>> onCreateLoader(int id, Bundle args) {
        //Create a new loader for the given URL
        return new BookLoader(this, google_books_request_url);
    }

    @Override
    public void onLoadFinished(Loader<List<Book>> loader, List<Book> books) {

        //Hide loading indicator because the data has been loaded
        View loadingIndicator = findViewById(R.id.loading_spinner);
        loadingIndicator.setVisibility(View.GONE);

        // Clear the adapter of previous book data
        mAdapter.clear();

        // If there is a valid list of {@link Book}s, then add them to the adapter's
        // data set. This will trigger the ListView to update.
        // Else if there is a valid search key word, then show text with an ID no_books to the emptyTextView.
        // Else there isn't an user input in the EditText View, then show text with an ID no_key_word to the emptyTextView.
        if (books != null && !books.isEmpty()) {
            mAdapter.addAll(books);
        } else if (getSearchItem() != null && !getSearchItem().isEmpty()) {
            mEmptyStateTextView.setText(R.string.no_books);
        } else {
            mEmptyStateTextView.setText(R.string.no_key_word);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<Book>> loader) {
        mAdapter.clear();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initLoader();

        //Hide loading indicator when start the app
        loadingIndicator = findViewById(R.id.loading_spinner);
        loadingIndicator.setVisibility(View.GONE);

        //When user is done editing, touch outside the editText on the screen, the keyboard disappear.
        EditText editText = (EditText) findViewById(R.id.search_item);
        editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    hideKeyboard(v);
                }
            }
        });

        //Find a reference to the {@Link ListView} in the layout
        ListView bookListView = (ListView) findViewById(R.id.list);

        //Create a new adapter that takes an empty list of earthquakes as input
        mAdapter = new BookAdapter(this, new ArrayList<Book>());

        //Set the adapter on the {@Link ListView}
        //So the list can be populated in the user interface
        bookListView.setAdapter(mAdapter);

        mEmptyStateTextView = (TextView) findViewById(R.id.empty_view);
        bookListView.setEmptyView(mEmptyStateTextView);

        Button button = (Button) findViewById(R.id.search_button);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                // Clear the adapter of previous book data
                mAdapter.clear();

                // Set empty state to blank screen.
                mEmptyStateTextView.setText("");

                //Show loading indicator after clicking search button
                loadingIndicator = findViewById(R.id.loading_spinner);
                loadingIndicator.setVisibility(View.VISIBLE);

                if (getSearchItem() == null) {
                    google_books_request_url = "";
                } else {
                    google_books_request_url = "https://www.googleapis.com/books/v1/volumes?q=" + getSearchItem().replace(" ", "");
                }

                //Get a reference to the ConnectivityManager to check state of network connectivity
                ConnectivityManager connMgr = (ConnectivityManager)
                        getSystemService(Context.CONNECTIVITY_SERVICE);

                //Get details on the currently active default data network
                NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

                //If there is a network connection, fetch data
                if (networkInfo != null && networkInfo.isConnected()) {

                    //Get a reference to the LoaderManager, in order to interact with loaders.
                    LoaderManager loaderManager = getSupportLoaderManager();

                    //Check if the loader is null, initialize the loader. Pass in the int ID constant defined above and pass in null for
                    //the bundle. Pass in this activity for the LoaderCallbacks parameter (which is valid
                    //because this activity implements the LoaderCallbacks interface).
                    //Otherwise, restart to use a different url with a different search key word.
                    if (loaderManager.getLoader(BOOK_LOADER_ID) == null) {
                        initLoader();
                    } else {
                        restartLoader();
                    }

                } else {
                    //Otherwise, display error
                    //First, hide loading indicator so error message will be visible
                    loadingIndicator = findViewById(R.id.loading_spinner);
                    loadingIndicator.setVisibility(View.GONE);

                    //Update empty state with no connection error message
                    mEmptyStateTextView.setText(R.string.no_internet);
                }
            }
        });
    }

    //To hide Keyboard on the screen
    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    /**
     * This method is called to get search item.
     */
    public String getSearchItem() {
        EditText editText = (EditText) findViewById(R.id.search_item);
        return editText.getText().toString();
    }

    private void initLoader() {
        //Get a reference to the LoaderManager, in order to interact with loaders.
        LoaderManager loaderManager = getSupportLoaderManager();
        loaderManager.initLoader(BOOK_LOADER_ID, null, this);
    }

    private void restartLoader() {
        //Get a reference to the LoaderManager, in order to interact with loaders.
        LoaderManager loaderManager = getSupportLoaderManager();
        loaderManager.restartLoader(BOOK_LOADER_ID, null, this);
    }
}
