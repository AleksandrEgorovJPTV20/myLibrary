package mylibrary;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;
import entity.Author;
import entity.Book;
import entity.History;
import entity.Reader;
import facade.AuthorFacade;
import facade.BookFacade;
import facade.HistoryFacade;
import facade.ReaderFacade;
import interfaces.Keeping;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import tools.SaverToFile;
import tools.saverToBase;

public class App {
    private Scanner scanner = new Scanner(System.in);
    private AuthorFacade authorFacade = new AuthorFacade(Author.class);
    private BookFacade bookFacade = new BookFacade(Book.class);
    private ReaderFacade readerFacade = new ReaderFacade(Reader.class);
    private HistoryFacade historyFacade = new HistoryFacade(History.class);

    public App() {
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
            System.out.println("7: Возврат книги");
            System.out.println("8: Добавить автора");
            System.out.println("9: Список авторов");
            System.out.println("10: Выбор книг по автору");
            System.out.println("11: Выбор книг по символам/слову");
            System.out.println("12: Редактирование книги");
            System.out.println("13: Редактирование читателя");
            System.out.println("14: Редактирование автора");
            int task = getNumber();
            switch (task) {
                case 0:
                    repeat="q";
                    System.out.println("Пока! :)");
                    break;
                case 1:
                    System.out.println("----- Добавление книги -----");
                    addBook();
                    break;
                case 2:
                    System.out.println("----- Список книг -----");
                    printListBooks();
                    break;
                case 3:
                    System.out.println("----- Добавление читателей -----");
                    addReader();
                    break;
                case 4:
                    System.out.println("----- Список читателей -----");
                    printListReaders();
                    break;
                case 5:
                    System.out.println("----- Выдать книгу -----");
                    addHistory();
                    break;
                case 6:
                    System.out.println("----- Список выданных книг -----");
                    printGivenBooks();
                    break;
                case 7:
                    System.out.println("----- Возврат книги -----");
                    returnBook();
                    break;
                case 8:
                    System.out.println("----- Добавить автора -----");
                    addAuthor();
                    break;
                case 9:
                    System.out.println("----- Список авторов -----");
                    printListAuthors();
                    break;
                case 10:
                    System.out.println("----- Выбор книг по автору -----");
                    selectionOfBooksByAuthor();
                    break;
                case 11:
                    System.out.println("----- Выбор книг по символам -----");
                    selectionOfBooksByWord();
                    break;
                case 12:
                    System.out.println("----- Редактирование книги -----");
                    updateBook();
                    break;
                case 13:
                    System.out.println("----- Редактирование читателя -----");
                    updateReader();
                    break;
                case 14:
                    System.out.println("----- Редактирование автора -----");
                    updateAuthor();
                    break;
                default:
                    System.out.println("Введите номер из списка.");;
            }
            
        }while("r".equals(repeat));
    }
    private boolean quit(){
        System.out.println("Чтобы закончить операцию нажмите клавишу \"q\", для продолжения любой другой символ");
        String quit = scanner.nextLine();
        if("q".equals(quit)) return true;
      return false;
    }
    private void returnBook() {
        System.out.println("Вернуть книгу: ");
        if(quit()) return;
        Set<Integer> numbersGivenBooks = printGivenBooks();
        if(numbersGivenBooks.isEmpty()){
            return;
        }
        int historyNumber = insertNumber(numbersGivenBooks);
        History histories = historyFacade.find((long)historyNumber);
        Calendar c = new GregorianCalendar();
        histories.setReturnedDate(c.getTime());
        Book books = bookFacade.find(histories.getBook().getId());
        books.setCount(books.getCount()+1);
        bookFacade.edit(books);
        historyFacade.edit(histories);
    }
    
    private Set<Integer> printGivenBooks(){
        Set<Integer> setNumberGivenBooks = new HashSet<>();
        List<History> historyesWithGivenBooks = historyFacade.findWithGivenBooks();
        for (int i = 0; i < historyesWithGivenBooks.size(); i++) {
            System.out.printf("%d. Книгу: %s читает %s %s%n",
                    historyesWithGivenBooks.get(i).getId(),
                    historyesWithGivenBooks.get(i).getBook().getBookName(),
                    historyesWithGivenBooks.get(i).getReader().getFirstName(),
                    historyesWithGivenBooks.get(i).getReader().getSurname()
            );
            setNumberGivenBooks.add(historyesWithGivenBooks.get(i).getId().intValue());
        }
        if(setNumberGivenBooks.isEmpty()){
            System.out.println("Выданных книг нет");
        }
        return setNumberGivenBooks;
    }
    private void addBook(){
        Book book = new Book();
        Set<Integer> setNumbersAuthors = printListAuthors();
        if(setNumbersAuthors.isEmpty()){
            System.out.println("Введите автора.");
            return;
        }
        System.out.print("Если в списке есть авторы книги нажмите 1: ");
        if(getNumber() != 1){
            System.out.println("Введите автора.");
            return;
        }
        System.out.println();
        System.out.print("Введите количество авторов: ");
        int countAutors = getNumber();
        List<Author> authorsBook = new ArrayList<>();
        for (int i = 0; i < countAutors; i++) {
            System.out.println("Введите номер автора "+(i+1)+" из списка: ");
            int numberAuthor = insertNumber(setNumbersAuthors);
            authorsBook.add(authorFacade.find((long)+numberAuthor));
        }
        book.setAuthor(authorsBook);
        System.out.print("Введите название книги: ");
        book.setBookName(scanner.nextLine());
        System.out.print("Введите год публикации книги: ");
        book.setPublishedYear(getNumber());
        System.out.print("Введите количество экземпляров книги: ");
        book.setQuantity(getNumber());
        book.setCount(book.getQuantity());
        bookFacade.create(book);
    }
    
    private void addReader(){
        Reader reader = new Reader();
        System.out.print("Введите имя читателя: ");
        reader.setFirstName(scanner.nextLine());
        System.out.print("Введите фамилию читателя: ");
        reader.setSurname(scanner.nextLine());
        System.out.print("Введите телефон читателя: ");
        reader.setPhone(scanner.nextLine());
        readerFacade.create(reader);
        System.out.println("-----------------------");
    }

    private void addHistory() {
        History history = new History();
        System.out.println("Список книг: ");
        Set<Integer> setNumbersBooks = printListBooks();
        if(setNumbersBooks.isEmpty()){
            return;
        }
        System.out.print("Введите номер книги: ");
        int numberBook = insertNumber(setNumbersBooks);
        Book books = bookFacade.find((long)numberBook);
        Set<Integer> setNumbersReaders = printListReaders();
        if(setNumbersReaders.isEmpty()){
            return;
        }
        System.out.print("Введите номер читателя: ");
        int numberReader = insertNumber(setNumbersReaders);
        Reader readers = readerFacade.find((long)numberReader);
        history.setBook(books);
        if(books.getCount() > 0){
            books.setCount(books.getCount()-1);
        }
        history.setReader(readers);
        Calendar c = new GregorianCalendar();
        history.setGivenDate(c.getTime());
        bookFacade.edit(books);
        historyFacade.create(history);
    }

    private Set<Integer> printListBooks() {
        List<Book> books = bookFacade.findAll();
        //books = keeper.loadBooks();
        Set<Integer> setNumbersBooks = new HashSet<>();
        for (int i = 0; i < books.size(); i++) {
            StringBuilder cbAutors = new StringBuilder();
            for (int j = 0; j < books.get(i).getAuthor().size(); j++) {
                cbAutors.append(books.get(i).getAuthor().get(j).getFirstName())
                        .append(" ")
                        .append(books.get(i).getAuthor().get(j).getSurname())
                        .append(". ");
            }
            if(books.get(i) != null && books.get(i).getCount() > 0){
                System.out.printf("%d. Автор: %s. %s В наличии экземпляров: %d%n"
                        ,books.get(i).getId()
                        ,books.get(i).getBookName()
                        ,cbAutors.toString()
                        ,books.get(i).getCount()
                );
                setNumbersBooks.add(books.get(i).getId().intValue());
            }else if(books.get(i) != null && books.get(i).getCount() > 0){
                System.out.printf("%d. %s %s %d. Нет в наличии. Будет возвращена: %s%n"
                        ,books.get(i).getId()
                        ,books.get(i).getBookName()
                        ,cbAutors.toString()
                        ,books.get(i).getPublishedYear()
                        ,getReturnDate(books.get(i))
                );
            }
        }
        return setNumbersBooks;
    }
    private Set<Integer> printListAllBooks() {
        System.out.println("Список книг: ");
        List<Book> books = bookFacade.findAll();
        //books = keeper.loadBooks();
        Set<Integer> setNumbersBooks = new HashSet<>();
        for (int i = 0; i < books.size(); i++) {
            StringBuilder cbAutors = new StringBuilder();
            for (int j = 0; j < books.get(i).getAuthor().size(); j++) {
                cbAutors.append(books.get(i).getAuthor().get(j).getFirstName())
                        .append(" ")
                        .append(books.get(i).getAuthor().get(j).getSurname())
                        .append(". ");
            }
            if(books.get(i) != null && books.get(i).getCount() >= 0){
                System.out.printf("%d. %s %s %d. В наличии экземпляров: %d%n"
                        ,books.get(i).getId()
                        ,books.get(i).getBookName()
                        ,cbAutors.toString()
                        ,books.get(i).getPublishedYear()
                        ,books.get(i).getCount()
                );
                setNumbersBooks.add(books.get(i).getId().intValue());
            }else if(books.get(i) != null && books.get(i).getCount() > 0){
                System.out.printf("%d. %s %s %d. Нет в наличии. Будет возвращена: %s%n"
                        ,books.get(i).getId()
                        ,books.get(i).getBookName()
                        ,cbAutors.toString()
                        ,books.get(i).getPublishedYear()
                        ,getReturnDate(books.get(i))
                );
            }
        }
        return setNumbersBooks;
    }
    
    public LocalDate convertToLocalDateViaInstant(Date dateToConvert) {
        return dateToConvert.toInstant()
            .atZone(ZoneId.systemDefault())
            .toLocalDate();
    } 
   
    private int getNumber() {
        do{
            try {
                String strNumber = scanner.nextLine();
                return Integer.parseInt(strNumber);
            } catch (Exception e) {
                System.out.println("Попробуй еще раз: ");
            }
        }while(true);
    }

    private int insertNumber(Set<Integer> setNumbers) {
        do{
            int historyNumber = getNumber();
            if(setNumbers.contains(historyNumber)){
                return historyNumber;
            }
            System.out.println("Попробуй еще раз: ");
        }while(true);
    }
    private String getReturnDate(Book book){
        List<History> histories = historyFacade.findAll();
        for (int i = 0; i < histories.size(); i++) {
            if(book.getBookName().equals(histories.get(i).getBook().getBookName())){
                LocalDate localGivenDate = histories.get(i).getGivenDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                localGivenDate = localGivenDate.plusDays(14);
                return localGivenDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
            }
        }
        return "";
    }
    private Set<Integer> printListReaders() {
        List<Reader> readers = readerFacade.findAll();
        Set<Integer> setNumbersReaders = new HashSet<>();
        System.out.println("Список читателей: ");
        for (int i = 0; i < readers.size(); i++) {
            if(readers.get(i) != null){
                System.out.printf("%d. %s %s. Телефон: &s&n"
                        ,readers.get(i).getId()
                        ,readers.get(i).getFirstName()
                        ,readers.get(i).getSurname()
                        ,readers.get(i).getPhone()
                );
                setNumbersReaders.add(readers.get(i).getId().intValue());
            }
        }
        if(setNumbersReaders.isEmpty()){
            System.out.println("Добавьте читателей!");
        }
        return setNumbersReaders;
    }

    private Set<Integer> printListAuthors() {
        List<Author> authors = authorFacade.findAll();
        Set<Integer> setNumbersAuthors = new HashSet<>();
        System.out.println("Список книг: ");
        for (int i = 0; i < authors.size(); i++) {
            if(authors.get(i) != null){
                System.out.printf("%d. %s %s%n"
                        ,authors.get(i).getId()
                        ,authors.get(i).toString()
                        ,authors.get(i).getSurname()
                );
                setNumbersAuthors.add(authors.get(i).getId().intValue());
            }
        }
        return setNumbersAuthors;        
    }

    private void addAuthor() {
        Author author = new Author();
        System.out.print("Введите имя автора: ");
        author.setFirstName(scanner.nextLine());
        System.out.print("Введите фамилию автора: ");
        author.setSurname(scanner.nextLine());
        System.out.print("Введите год рождения автора: ");
        author.setBirthYear(getNumber());
        authorFacade.create(author);
        System.out.println("-----------------------");        
    }

    private void selectionOfBooksByAuthor() {
        List<Book> books = bookFacade.findAll();
        Set<Integer> setNumbersAuthors = printListAuthors();
        if(setNumbersAuthors.isEmpty()){
            System.out.println("Список авторов пуст. Добавьте автора!");
            return;
        }
        System.out.println("Выберите номер автора: ");
        Author author = authorFacade.find((long)insertNumber(setNumbersAuthors));
        for(int i = 0; i<books.size();i++){
            List<Author>authorsBook = books.get(i).getAuthor();
            for(int j = 0; j < authorsBook.size(); j++){
                Author authorBook = authorsBook.get(j);
                if(author.equals(authorBook)){
                    System.out.printf("%d. %s %d%n"
                        ,books.get(i).getId()
                        ,books.get(i).getBookName()
                        ,books.get(i).getPublishedYear()
                    );
                }
            }
        }
        System.out.println("---------------------------------------");
    }

    private void selectionOfBooksByWord() {
        List<Book> books = bookFacade.findAll();
        System.out.println("Введите часть слово книги: ");
        String word = scanner.nextLine();
        int n = 0;
        for(int i = 0; i < books.size();i++){
            if(books.get(i).getBookName().contains(word)){
                System.out.printf("Данная часть слова или символы содержаться в книге: %s%n"
                    ,books.get(i).getBookName());
                n++;
            }
            
        }
        if(n == 0){
            System.out.println("Книг содержащих данное слово или символы нет!");
        }
    }

    private void updateBook() {
        Set<Integer> setNumbersBooks = printListAllBooks();
        if(setNumbersBooks.isEmpty()){
            System.out.println("Нет книг в базе");
            return;
        }
        System.out.println("Выберите номер книги: ");
        int numBook = insertNumber(setNumbersBooks);
        Set<Integer> setNum = new HashSet<>();
        setNum.add(1);
        setNum.add(2);
        Book books = bookFacade.find((long)numBook);
        System.out.println("Название книги: "+books.getBookName());
        System.out.println("Хотите изменить нажмите 1, оставить без изменения 2");
        int change = insertNumber(setNum);
        if(1 == change){
            System.out.println("Введите новое название книги: ");
            books.setBookName(scanner.nextLine());
        }
        System.out.println("Год издания книги: "+books.getPublishedYear());
        System.out.println("Хотите изменить нажмите 1, оставить без изменения 2");
        change = insertNumber(setNum);
        if(1 == change){
            System.out.println("Введите новый год издания: ");
            books.setPublishedYear(getNumber());
        }
        System.out.println("Количество экземпляров книги: "+books.getQuantity());
        System.out.println("Хотите изменить нажмите 1, оставить без изменения 2");
        change = insertNumber(setNum);
        if(1 == change){
            System.out.println("Введите новое количество книг: ");
            int quantity = getNumber();
            int addCountBooks = quantity - books.getQuantity();
            books.setQuantity(quantity);
            books.setCount(books.getCount() + addCountBooks);
            int oldCount = books.getCount();
            int oldQuantity = books.getQuantity();
            int newQuantity;
            do {                
                newQuantity = getNumber();
                if(newQuantity >= 0 && newQuantity >= oldQuantity - oldCount){
                    break;
                }
                System.out.println("Попробуй еще (>"+(oldQuantity - oldCount)+"): ");
            } while (true);
            int newCount = oldCount + (newQuantity - oldQuantity);
            books.setQuantity(newQuantity);
            books.setCount(newCount);
        }
        System.out.println("Авторы книги: ");
        for (int i = 0; i < books.getAuthor().size(); i++) {
            System.out.printf("%d. %s %s. %d%n"
                    ,books.getAuthor().get(i).getId()
                    ,books.getAuthor().get(i).getFirstName()
                    ,books.getAuthor().get(i).getSurname()
                    ,books.getAuthor().get(i).getBirthYear()
            );
            
        }
        System.out.println("Хотите изменить нажмите 1, оставить без изменения 2");
        change = insertNumber(setNum);
        if(1 == change){
            books.getAuthor().clear();
            System.out.println("Введите количество авторов: ");
            int countAuthors = getNumber();
            Set<Integer> setNumbersAuthors = printListAuthors();
            if(!setNumbersAuthors.isEmpty()){
                for (int i = 0; i < countAuthors; i++) {
                    System.out.println("Введите номер автора "+(i+1)+": ");
                    books.getAuthor().add(authorFacade.find((long)insertNumber(setNumbersAuthors)));
                }
            }else{
                System.out.println("Список авторов пуст");
            }
        }
        bookFacade.edit(books);
    }

    private void updateReader() {
        Set<Integer> setNumbersReaders = printListReaders();
        if(setNumbersReaders.isEmpty()){
            System.out.println("Нет читаталей в базе данных\n");
            return;
        }
        System.out.println("Выберите номер читателя: ");
        int numReader = insertNumber(setNumbersReaders);
        Set<Integer> setNum = new HashSet<>();
        setNum.add(1);
        setNum.add(2);
        Reader readers = readerFacade.find((long)numReader);
        System.out.println("Имя читателя: "+readers.getFirstName());
        System.out.println("Хотите изменить нажмите 1, оставить без изменения 2");
        int change = insertNumber(setNum);
        if(1 == change){
            System.out.println("Введите новое имя читателя: ");
            readers.setFirstName(scanner.nextLine());
        }
        System.out.println("Фамилия читателя: "+readers.getSurname());
        System.out.println("Хотите изменить нажмите 1, оставить без изменения 2");
        change = insertNumber(setNum);
        if(1 == change){
            System.out.println("Введите новую фамилию читателя: ");
            readers.setSurname(scanner.nextLine());
        }
        System.out.println("Телефон читателя: "+readers.getPhone());
        System.out.println("Хотите изменить нажмите 1, оставить без изменения 2");
        change = insertNumber(setNum);
        if(1 == change){
            System.out.println("Введите новый телефон читателя: ");
            readers.setPhone(scanner.nextLine());
        }
        readerFacade.edit(readers);       
    }

    private void updateAuthor() {
        Set<Integer> setNumbersAuthors = printListAuthors();
        if(setNumbersAuthors.isEmpty()){
            System.out.println("Нет авторов в базе");
            return;
        }
        System.out.println("Выберите номер автора: ");
        int numAuthor = insertNumber(setNumbersAuthors);
        Set<Integer> setNum = new HashSet<>();
        setNum.add(1);
        setNum.add(2);
        Author authors = authorFacade.find((long)numAuthor);
        System.out.println("Имя автора: "+authors.getFirstName());
        System.out.println("Хотите изменить нажмите 1, оставить без изменения 2");
        int change = insertNumber(setNum);
        if(1 == change){
            System.out.println("Введите новое имя автора: ");
            authors.setFirstName(scanner.nextLine());
        }
        System.out.println("Фамилия автора: "+authors.getSurname());
        System.out.println("Хотите изменить нажмите 1, оставить без изменения 2");
        change = insertNumber(setNum);
        if(1 == change){
            System.out.println("Введите новую фамилию автору: ");
            authors.setSurname(scanner.nextLine());
        }
        System.out.println("Год рождения автора: "+authors.getBirthYear());
        System.out.println("Хотите изменить нажмите 1, оставить без изменения 2");
        change = insertNumber(setNum);
        if(1 == change){
            System.out.println("Введите новый год дня рождения автора: ");
            authors.setBirthYear(getNumber());
        }
        authorFacade.edit(authors);
    }
}