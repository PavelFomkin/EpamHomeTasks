package library_by_textfile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

class Library implements KeeperOfBooks{
    private final String NAME;
    private final Path PATH_TO_LIBRARY;
    private final Path PATH_TO_USERS;
    private final Path PATH_TO_DIRECTORY;
    private List<UserOfLibrary> users = new ArrayList<>();
    private List<Book> books = new ArrayList<>();
    Library(String name){
        NAME = name;
        PATH_TO_DIRECTORY = createDirectoryForLibrary(name);
        PATH_TO_LIBRARY = findOrCreateBase(NAME+"(books).txt");
        PATH_TO_USERS = findOrCreateBase(NAME+"(users).txt");
        loadBase(PATH_TO_USERS,users);
        loadBase(PATH_TO_LIBRARY,books);
    }

    @Override
    public String getUniqueValue() {
        return NAME;
    }

    public UserOfLibrary getUser(int id) {
        return defineKeeper(id);
    }

    public Book getBook(int bookNumber) {
        int index = bookNumber-1;
        if (index < 0 || index >= books.size()) {
            return null;
        }
        return books.get(index);
    }

    private Path createDirectoryForLibrary(String name){
        Path dirPath = Paths.get(name);
        if(!Files.exists(dirPath) && !Files.isDirectory(dirPath)){
            try {
                Files.createDirectory(dirPath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return dirPath;
    }

    private Path findOrCreateBase(String name){
        Path pathToLibrary = Paths.get(PATH_TO_DIRECTORY.toString(), name);
        if(!Files.exists(pathToLibrary) && !Files.isRegularFile(pathToLibrary)){
            try {
                Files.createFile(pathToLibrary);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return pathToLibrary;
    }

    private <T> void loadBase(Path path, List<T> list){
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(path.toFile()))){
            String metadata = bufferedReader.readLine();
            while(metadata != null){
                Object file = buildFile(metadata);
                if (file != null){
                    list.add((T)file);
                }
                metadata = bufferedReader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Object buildFile(String metadata){
        String[] infoArr = metadata.split("\t");
        if(Book.TYPE.equals(infoArr[0])){
            Book book = new Book(infoArr[1],infoArr[2],infoArr[3]);
            if(this.getUniqueValue().equals(infoArr[4])){
                book.setKeeper(this);
            } else {
                int id = Integer.parseInt(infoArr[4]);
                UserOfLibrary user = defineKeeper(id);
                if(user == null){
                    System.out.println("The book \""+book.getTitle()+"\" has no keeper.");
                    return null;
                }
                book.setKeeper(defineKeeper(id));
            }
            book.setDateOfCreation(LocalDateTime.parse(infoArr[5]));
            book.setDateOfLastChange(LocalDateTime.parse(infoArr[6]));
            return book;
        }
        else if (UserOfLibrary.TYPE.equals(infoArr[0])){
            UserOfLibrary user = new UserOfLibrary(Integer.parseInt(infoArr[1]),infoArr[2],Integer.parseInt(infoArr[3]));
            for(int i=4; i < infoArr.length; i++){
                user.takeBook(infoArr[i]);
            }
            return user;
        }
        else {
            return null;
        }
    }

    private UserOfLibrary defineKeeper(int id){
        for (UserOfLibrary user : users){
            if(user.getID()==id){
                return user;
            }
        }
        return null;
    }

    private Book defineKeeper(String bookISBN){
        for (Book book : books){
            if(book.getISBN().equals(bookISBN)){
                return book;
            }
        }
        return null;
    }

    private <T> void rewriteBase(Path path, List<T> list){
        try (FileWriter fileWriter = new FileWriter(path.toFile(),false)){
            for(T string : list){
                fileWriter.write(string.toString()+'\n');
                fileWriter.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void printBook(Book book){
        System.out.println("ISBN: "+book.getISBN());
        System.out.println("Title: "+book.getTitle());
        System.out.println("Author: "+book.getAuthor());
        System.out.println("Keeper: "+(book.getKeeper().equals(this) ? "" : "User, ID ")+book.getKeeper().getUniqueValue());
        System.out.println("Date of creation: "+book.getDateOfCreation().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
        System.out.println("Date of last change: "+book.getDateOfLastChange().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
    }

    private void printUser(UserOfLibrary user){
        System.out.println("User ID: "+user.getID());
        System.out.println("Name: "+user.getName());
        System.out.println("Age: "+user.getAge());
        user.listOfLibraryBooks();
    }

    public void addUser(UserOfLibrary user){
        if (users.contains(user)){
            System.out.println("User with ID"+user.getID()+" has already exist.");
            return;
        }
        try (FileWriter fileWriter = new FileWriter(PATH_TO_USERS.toFile(),true)){
            fileWriter.write(user.toString()+'\n');
            fileWriter.flush();
            users.add(user);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addBook(Book book){
        if(books.contains(book)){
            System.out.println("Book \""+book.getTitle()+"\" has already exist.");
            return;
        }
        book.setKeeper(this);
        try (FileWriter fileWriter = new FileWriter(PATH_TO_LIBRARY.toFile(),true)){
            book.setDateOfLastChange(LocalDateTime.now().plusHours(Book.DIFFERENCE_IN_TIME));
            fileWriter.write(book.toString()+'\n');
            fileWriter.flush();
            books.add(book);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void giveBookToUser(Book book, UserOfLibrary user){
        if(user == null || book == null || !users.contains(user) || !books.contains(book)){
            System.out.println("Invalid value. A user or a book doesn't exist.");
            return;
        }
        if(!book.getKeeper().equals(this)){
            System.out.println("The book was already given to the user with ID: "+book.getKeeper().getUniqueValue()+".");
            return;
        }
        user.takeBook(book.getTitle());
        book.setKeeper(user);
        book.setDateOfLastChange(LocalDateTime.now().plusHours(Book.DIFFERENCE_IN_TIME));
        rewriteBase(PATH_TO_USERS,users);
        rewriteBase(PATH_TO_LIBRARY,books);
    }

    public void returnBook(Book book){
        if(book == null || !books.contains(book)){
            System.out.println("This book doesn't exist in this Library.");
            return;
        }
        if(book.getKeeper().equals(this)){
            System.out.println("The book \""+book.getTitle()+"\" is in Library.");
            return;
        }
        UserOfLibrary user = defineKeeper(Integer.parseInt(book.getKeeper().getUniqueValue()));
        if (user != null){
            if(user.returnBook(book.getTitle())){
                book.setKeeper(this);
                book.setDateOfLastChange(LocalDateTime.now().plusHours(Book.DIFFERENCE_IN_TIME));
                rewriteBase(PATH_TO_USERS,users);
                rewriteBase(PATH_TO_LIBRARY,books);
            }
            else{
                System.out.println("The data about this book is pointed wrong.");
            }
        }
        else {
            System.out.println("The user pointed in library doesn't exist.");
        }
    }

    public void printAllBooks(){
        if (books.size()==0){
            System.out.println("The library has no books.");
            return;
        }
        System.out.println("Books:");
        for (int i=0; i<books.size(); i++){
            System.out.println("Book №: "+(i+1));
            System.out.println("ISBN: "+books.get(i).getISBN());
            System.out.println("Title: "+books.get(i).getTitle());
            System.out.println("Author: "+books.get(i).getAuthor());
            System.out.println("Keeper: "+(books.get(i).getKeeper().equals(this) ? "" : "User, ID ")+books.get(i).getKeeper().getUniqueValue());
            System.out.println("Date of creation: "+books.get(i).getDateOfCreation().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
            System.out.println("Date of last change: "+books.get(i).getDateOfLastChange().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
        }
    }

    public void printBooks(int numberOfBooks){
        if (books.size()==0){
            System.out.println("The library has no books.");
            return;
        }
        if(numberOfBooks < 1){
            System.out.println("Invalid value.");
            return;
        }
        if(numberOfBooks > books.size()){
            System.out.println("Number of books int the library: "+books.size());
            numberOfBooks = books.size();
        }
        if(numberOfBooks==1){
            printBook(books.get(0));
        } else {
            System.out.println("First "+numberOfBooks+" books:");
            for (int i=0; i<numberOfBooks; i++){
                System.out.println("Book №: "+(i+1));
                printBook(books.get(i));
            }
        }
    }

    public void printBook(int bookNumber){
        Book book = getBook(bookNumber);
        if(book == null){
            System.out.println("The library doesn't have this book.");
            return;
        }
        System.out.println("Book №: "+(bookNumber));
        printBook(book);
    }

    public void printBook(String userISBN){
        Book book = defineKeeper(userISBN);
        if (book==null){
            System.out.println("The library doesn't have this book.");
            return;
        }
        printBook(book);
    }

    public void printTakenBooks(){
        System.out.println("The taken books by users:");
        boolean moreThanOne = false;
        for (int i=0; i<books.size(); i++){
            if(!books.get(i).getKeeper().equals(this)){
                System.out.println("Book №: "+(i+1));
                printBook(books.get(i));
                moreThanOne = true;
            }
        }
        if(!moreThanOne){
            System.out.println("No such books.");
        }
    }

    public void printAllUsers(){
        if (users.size()==0){
            System.out.println("The library has no users.");
            return;
        }
        System.out.println("Users:");
        for (UserOfLibrary user : users){
            System.out.println("User ID: "+user.getID());
            System.out.println("Name: "+user.getName());
            System.out.println("Age: "+user.getAge());
            user.listOfLibraryBooks();
        }
    }

    public void printUsers(int numberOfUsers){
        if (users.size()==0){
            System.out.println("The library has no users.");
            return;
        }
        if(numberOfUsers < 1){
            System.out.println("Invalid value.");
            return;
        }
        if(numberOfUsers > users.size()){
            System.out.println("Number of users int the library: "+users.size());
            numberOfUsers = users.size();
        }
        if(numberOfUsers==1){
            printUser(users.get(0));
        } else {
            System.out.println("First "+numberOfUsers+" users:");
            for (int i=0; i<numberOfUsers; i++){
                printUser(users.get(i));
            }
        }
    }

    public void printUser(int userID){
        UserOfLibrary user = defineKeeper(userID);
        if (user==null){
            System.out.println("The library doesn't have this user.");
            return;
        }
        printUser(user);
    }

    public void printUsersWithLibraryBooks(){
        if (users.size()==0){
            System.out.println("The library has no users.");
            return;
        }
        System.out.println("Users with library books:");
        boolean moreThanOne = false;
        for (UserOfLibrary user : users){
            if (user.getCountOfBooks()>0){
                printUser(user);
                moreThanOne = true;
            }
        }
        if(!moreThanOne){
            System.out.println("No such users.");
        }
    }

    public void sortBooks(Comparator<Book> comparator){
        if (books.size()<2){
            return;
        }
        books.sort(comparator);
        rewriteBase(PATH_TO_LIBRARY,books);
    }

    public void sortBooks(int numberElementsForSort, Comparator<Book> comparator){
        if(numberElementsForSort < 2 || numberElementsForSort>books.size()){
            System.out.println("Argument was pointed wrong.");
            return;
        }
        List<Book> subList = books.subList(0,numberElementsForSort);
        subList.sort(comparator);
        rewriteBase(PATH_TO_LIBRARY,books);
    }

    public void sortUsers(Comparator<UserOfLibrary> comparator){
        if (users.size()<2){
            return;
        }
        users.sort(comparator);
        rewriteBase(PATH_TO_USERS,users);
    }

    public void sortUsers(int numberElementsForSort, Comparator<UserOfLibrary> comparator){
        if(numberElementsForSort < 2 || numberElementsForSort>users.size()){
            System.out.println("Argument was pointed wrong.");
            return;
        }
        List<UserOfLibrary> subList = users.subList(0,numberElementsForSort);
        subList.sort(comparator);
        rewriteBase(PATH_TO_USERS,users);
    }
}
