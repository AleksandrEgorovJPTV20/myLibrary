package mylibrary;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;
import entity.Author;
import entity.Book;
import entity.History;
import entity.Reader;
import interfaces.Keeping;
import java.util.ArrayList;
import java.util.List;
import tools.SaverToFile;

public class App {
    private Scanner scanner = new Scanner(System.in);
    private List<Book> books = new ArrayList<>();
    private List<Reader> readers = new ArrayList<>();
    private List<History> histories = new ArrayList<>();
    private Keeping keeper = new SaverToFile();

    public App() {
        books = keeper.loadBooks();
        readers = keeper.loadReaders();
        histories = keeper.loadHistories();
        
    }
    
    public void run(){
        String repeat = "r";
        do{
            System.out.println("Выберите номер задачи: "); 
            System.out.println("0: Выход из программы");
            System.out.println("1: Добавить книгу");
            System.out.println("2: Список книг");
            System.out.println("3: Добавить читателя");            
            System.out.println("4: Список читателей");   
            System.out.println("5: Выдать книгу");
            System.out.println("6: Список выданных книг");
            System.out.println("7: Возврат книг");
            int task = scanner.nextInt(); scanner.nextLine();       
            switch (task) {
                case 0:
                    repeat = "q";
                    System.out.println("Пока! :)");
                    break;
                case 1:
                    System.out.println("----- Добавление книги -----");
                    books.add(addBook());
                    keeper.saveBooks(books);                  
                    break;
                case 2:
                    System.out.println("----- Список книг -----");
                    printListBooks();
                    break;
                case 3:
                    System.out.println("----- Добавление читателей -----");
                    readers.add(addReader());
                    keeper.saveReaders(readers);
                    break;
                case 4:
                    System.out.println("----- Список читателей -----");
                    for (int i = 0; i < readers.size(); i++){
                        if(readers.get(i) != null){
                            System.out.println(readers.get(i).toString());
                        }
                    }
                    break;
                case 5:
                    System.out.println("----- Выдать книгу -----");
                    histories.add(addHistory());
                    keeper.saveHistories(histories);
                    break;
                case 6:
                    System.out.println("----- Список выданных книг -----");
                    printGivenBooks();
                    break;
                case 7:
                    System.out.println("----- Возврат книги -----");
                    System.out.println("Список выданных книг: ");
                    printGivenBooks();
                    System.out.println("Выберите номер возращаемой книги: ");
                    int numberHistory = scanner.nextInt(); scanner.nextLine();
                    Calendar c = new GregorianCalendar();
                    if(histories.get(numberHistory - 1).getBook().getCount()
                            < histories.get(numberHistory -1).getBook().getQuantity()){
                        histories.get(numberHistory - 1).setReturnedDate(c.getTime());
                        histories.get(numberHistory - 1).getBook().setCount(histories.get(numberHistory - 1).getBook().getCount()+1);
                    }else{
                        System.out.println("Все экземпляры книги в библиотеке");
                    }
                    System.out.printf("Книга \"%s\" возращена%n", histories.get(numberHistory-1).getBook().getBookName());
                    System.out.println("");
                    break;
                default:
                    System.out.println("Введите номер из списка!");
            }
            
        }while("r".equals(repeat));
        
    }
    private void printGivenBooks(){
        int n = 0;
        for (int i = 0; i < histories.size(); i++){
            if(histories.get(i) != null
                    && histories.get(i).getReturnedDate() == null
                    && histories.get(i).getBook().getCount() < histories.get(i).getBook().getQuantity()){
                System.out.printf(i +1+". "+"Книгу %s читает %s %s. Выдана книга: %s%n",
                        histories.get(i).getBook().getBookName(),
                        histories.get(i).getReader().getFirstName(),
                        histories.get(i).getReader().getSurname(),
                        histories.get(i).getGivenDate().toString());        
                n++;
                }
            }
            if(n<1){
                System.out.println("Нет выданных книг!");        
            }
            System.out.println();  
        
    }
    private Book addBook(){
        Book book = new Book();
        System.out.println("Введите название книги: ");
        book.setBookName(scanner.nextLine());
        System.out.println("Введите год публикации книги: ");
        book.setPublishedYear(scanner.nextInt()); scanner.nextLine();
        System.out.println("Введите количество экземпляров книги: ");
        book.setQuantity(scanner.nextInt()); scanner.nextLine();
        book.setCount(book.getQuantity());
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
        System.out.print("Введите имя читателя: ");
        reader.setFirstName(scanner.nextLine());
        System.out.print("Введите фамилию читателя: ");
        reader.setSurname(scanner.nextLine());
        System.out.print("Введите телефон читателя: ");
        reader.setPhone(scanner.nextLine());
        return reader;
    }
    private History addHistory() {
        History history = new History();
        /**
         * 1 Вывсти пронумерованный список книг библиотеки
         * 2 Попросить пользователя выбрать номер книги
         * 3 Вывести пронумерованный список читателей 
         * 4 Попросить пользователя выбрать номер читателя
         * 5 Сгенерировать текущую дату выдачи 
         * 6 Иницирировать обьект history 
         */
        System.out.println("Список книг");
        printListBooks();
        System.out.print("Введите номер книги: ");
        int numberBook = scanner.nextInt(); scanner.nextLine();
        
        System.out.println("Вывести список читателей: ");
        System.out.println("Список читателей");
        for (int i = 0; i < readers.size(); i++){
            if(readers.get(i) != null){
                System.out.println(i+1+". "+readers.get(i).toString());                   
            }         
        }
        System.out.println("Введите номер читателя: ");
        int numberReader = scanner.nextInt(); scanner.nextLine();
        history.setBook(books.get(numberBook-1));
        if(history.getBook().getCount() > 0){
            history.getBook().setCount(history.getBook().getCount()-1);
        }
        history.setReader(readers.get(numberReader-1));
        Calendar c = new GregorianCalendar();
        history.setGivenDate(c.getTime());
        
        return history;
    }

    private void printListBooks() {
        for (int i = 0; i < books.size(); i++){
            if(books.get(i) != null && books.get(i).getCount()> 0){
                System.out.println(books.get(i).toString());
            }
        }
    }
}
