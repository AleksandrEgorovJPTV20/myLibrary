package interfaces;

import entity.Book;
import entity.History;
import entity.Reader;

public interface Keeping {
    public void saveBooks(Book[] books);
    public Book[] loadBooks();
    public void saveReaders(Reader[] readers);
    public Reader[] loadReaders();    
    public void saveHistories(History[] histories);
    public History[] loadHistories();    
}
