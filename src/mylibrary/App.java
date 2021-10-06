package mylibrary;

import java.util.Calendar;
import java.util.GregorianCalendar;
import myclasses.Author;
import myclasses.Book;
import myclasses.History;
import myclasses.Reader;


public class App {
    
    public void run(){
        Reader reader1 = new Reader();
        reader1.setFirstName("Petr");
        reader1.setSurname("Petrov");
        reader1.setPhone("533267534");
        
        //Даём значение переменной при помощи set.  Читатель 1.
        Reader reader2 = new Reader();
        reader2.setFirstName("Olga");
        reader2.setSurname("Tamme");
        reader2.setPhone("572838794");
        
        //Книга и автор 1
        Book book1 = new Book();
        book1.setBookName("Voina i mir");
        book1.setPublishedYear(2021);
        
        Author author1 = new Author();
        author1.setFirstName("Lev");
        author1.setSurname("Tolstoy");
        author1.setBirthYear(1828);
        
        Author[] authors = new Author[1];
        authors[0] = author1;
        book1.setAuthor(authors);
        
        //Книга и автор 2
        Book book2 = new Book();
        book2.setBookName("Otsi ya Deti");
        book2.setPublishedYear(1862);
        
        Author author2 = new Author();
        author2.setFirstName("Ivan");
        author2.setSurname("Turgenev");
        author2.setBirthYear(1818);
        
        Author[] authors2 = new Author[1];
        authors2[0] = author2;
        book2.setAuthor(authors2);
        
        //Первая история 
        History history1 = new History();
        history1.setBook(book1);
        history1.setReader(reader1);
        Calendar c = new GregorianCalendar();
        history1.setGivenDate(c.getTime());
        System.out.println("history 1 = "+history1.toString());
        
        History history2 = new History();
        history2.setBook(book2);
        history2.setReader(reader2);
        c = new GregorianCalendar();
        history2.setGivenDate(c.getTime());
        System.out.println("history 2 = "+history2.toString());
        
        System.out.println();
        history1.setReturnedDate(c.getTime());
        System.out.println("history 1 = "+history1.toString());
        history2.setReturnedDate(c.getTime());
        System.out.println("history 2 = "+history2.toString());
    }
}
