package myclasses;

import java.util.Arrays;


public class Book {
    private String bookName;
    private int publishedYear;
    private Author[] author;

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public int getPublishedYear() {
        return publishedYear;
    }

    public void setPublishedYear(int publishedYear) {
        this.publishedYear = publishedYear;
    }

    public Author[] getAuthor() {
        return author;
    }

    public void setAuthor(Author[] author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "Book{"
                + "bookName=" + bookName
                + ", publishedYear=" + publishedYear
                + ", author=" + Arrays.toString(author) + '}';
    }
    
}
