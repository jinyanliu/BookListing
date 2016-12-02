package se.sugarest.jane.booklisting;

import java.util.Set;

/**
 * Created by jane on 11/30/16.
 * Represents a book.
 * It contains the title and the author.
 */

public class Book {

    /**
     * Title of the book
     */
    private String mTitle;

    /**
     * Authors of the book
     */
    private Set<String> mAuthors;

    /**
     * Constructs a new {@Link Book} object.
     *
     * @param title   is the title of the book
     * @param authors are the authors of the book
     */
    public Book(String title, Set<String> authors) {
        mTitle = title;
        mAuthors = authors;
    }

    /**
     * Get the title of the book
     */
    public String getTitle() {
        return mTitle;
    }

    /**
     * Get the authors of the book
     */
    public Set<String> getAuthors() {
        return mAuthors;
    }

    /**
     * Get the names of the authors as strings output
     *
     * @return authors names as strings
     */
    public String getAuthorsText() {
        StringBuilder authorsText = new StringBuilder();
        for (String author : getAuthors()) {
            if (authorsText.length() > 0) {
                authorsText.append('\n' + author);
            } else {
                authorsText.append(author);
            }
        }
        return authorsText.toString();
    }

    @Override
    public String toString() {
        return "Book{" +
                "mTitle='" + mTitle + '\'' +
                ", mAuthors=" + mAuthors +
                '}';
    }
}
