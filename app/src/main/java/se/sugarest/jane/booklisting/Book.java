package se.sugarest.jane.booklisting;

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
     * Author of the book
     */
    private String mAuthor;

    /**
     * Constructs a new {@Link Book} object.
     *
     * @param title  is the title of the book
     * @param author is the author of the book
     */
    public Book(String title, String author) {
        mTitle = title;
        mAuthor = author;
    }

    /**
     * Get the title of the book
     */
    public String getTitle() {
        return mTitle;
    }

    /**
     * Get the author of the book
     */
    public String getAuthor() {
        return mAuthor;
    }

    @Override
    public String toString() {
        return "Book{" +
                "mTitle='" + mTitle + '\'' +
                ", mAuthor='" + mAuthor + '\'' +
                '}';
    }

}
