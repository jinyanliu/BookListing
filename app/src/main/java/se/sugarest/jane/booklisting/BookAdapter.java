package se.sugarest.jane.booklisting;

/**
 * Created by jane on 11/30/16.
 */

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * BookAdapter is an ArrayAdapter that can provide the layout for the list based on a data source, which is a list of Book objects.
 */
public class BookAdapter extends ArrayAdapter<Book> {

    /**
     * This is my own custom constructor (it doesn't mirror a superclass constructor).
     * The context is used to inflate the layout file, and the list is the data I want to populate into the list.
     *
     * @param context The current context. Used to inflate the layout file.
     * @param book    A list of Book objects to display in a list.
     */
    public BookAdapter(Activity context, ArrayList<Book> book) {
        super(context, 0, book);
    }

    /**
     * Provides a view for an AdapterView(ListView, GridView, etc.)
     *
     * @param position    is the position in the list of data that should be displayed in the ListItem view.
     * @param convertView is the recycled view to populate.
     * @param parent      is the parent ViewGroup that is used for inflation.
     * @return the view for the position in the AdapterView.
     */
    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //Check is the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        //Get the Book object located at this position in the list
        Book currentBook = getItem(position);

        //Find the TextView in the list_item.xml layout with the ID book_title
        TextView titleTextView = (TextView) listItemView.findViewById(R.id.book_title);
        //Display the title of the current book in that TextView.
        titleTextView.setText(currentBook.getTitle());

        //Find the TextView in the list_item.xml layout with the ID book_author
        TextView authorTextView = (TextView) listItemView.findViewById(R.id.book_author);
        //Display the author of the current book in that TextView.
        authorTextView.setText(currentBook.getAuthorsText());

        //Return the list_item view layout (containing 2 TextViews)
        //so that it can be shown in the ListView
        return listItemView;
    }
}
