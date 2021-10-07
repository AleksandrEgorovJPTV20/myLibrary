package mylibrary;

import java.util.Scanner;
import myclasses.Author;
import myclasses.Book;
import myclasses.Reader;

public class App {
    private Scanner scanner = new Scanner(System.in);
    private Book[] books = new Book[10];
    private Reader[] readers = new Reader[10];
    public void run(){
        String repeat = "r";
        do{
            System.out.println("Выберите номер задачи: "); 
            System.out.println("0: Выход из программы");
            System.out.println("1: Добавить книгу");
            System.out.println("2: Список книг");
            System.out.println("3: Добавить читателя");            
            System.out.println("4: Список читателей");           
            int task = scanner.nextInt(); scanner.nextLine();       
            switch (task) {
                case 0:
                    repeat = "q";
                    System.out.println("Пока! :)");
                    break;
                case 1:
                    System.out.println("----- Добавление книги -----");
                    for (int i = 0;i < books.length; i++){
                        if (books[i] == null){
                            books[i] = addBook();
                            break;
                            
                        }
                    }
                    break;
                case 2:
                    System.out.println("----- Список книг -----");
                    for (int i = 0; i < books.length; i++){
                        if(books[i] != null){
                            System.out.println(books[i].toString());
                        }
                    }
                    break;
                case 3:
                    System.out.println("----- Добавление читателей -----");
                    for (int i = 0; i < readers.length;i++){
                        if (readers[i] == null){
                            readers[i] = addReader();
                            break;
                        }
                    }
                    break;
                case 4:
                    System.out.println("----- Список читателей -----");
                    for (int i = 0; i < readers.length; i++){
                        if(readers[i] != null){
                            System.out.println(readers[i].toString());
                            break;
                        }
                    }
                    break;
                default:
                    System.out.println("Введите номер из списка!");
            }
            
        }while("r".equals(repeat));
        
    }
    private Book addBook(){
        Book book = new Book();
        System.out.println("Введите название книги: ");
        book.setBookName(scanner.nextLine());
        System.out.println("Введите год публикации книги: ");
        book.setPublishedYear(scanner.nextInt()); scanner.nextLine();
        System.out.println("Автор книги: ");
        System.out.println("Количество авторов: ");
        int countAuthors = scanner.nextInt(); scanner.nextLine();
        
        Author[] authors = new Author[countAuthors];
        
        for(int i = 0; i < authors.length; i++){
            Author author = new Author();
            System.out.println("Введите имя автора "+(i+1)+": ");
            author.setFirstName(scanner.nextLine());
            System.out.println("Введите фамилию автора: ");
            author.setSurname(scanner.nextLine());
            System.out.println("Введите год рождения автора: ");
            author.setBirthYear(scanner.nextInt()); scanner.nextLine();
            authors[i] = author;
        }
        book.setAuthor(authors);
        
        return book;
    }
    private Reader addReader(){
        Reader reader = new Reader();
        System.out.println("Количество читателей: ");
        int countReaders = scanner.nextInt(); scanner.nextLine();
        Reader[] readers = new Reader[countReaders];
        for(int i = 0; i < readers.length; i++){
            System.out.println("Введите имя читателя "+(i+1)+": ");
            reader.setFirstName(scanner.nextLine());
            System.out.println("Введите фамилию читателя "+(i+1)+": ");
            reader.setSurname(scanner.nextLine());
            System.out.println("Введите телефон читателя "+(i+1)+": ");
            reader.setPhone(scanner.nextLine());
        }    
        return reader;
    }
}
