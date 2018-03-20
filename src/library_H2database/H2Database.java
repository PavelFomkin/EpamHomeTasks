package library_H2database;

import java.sql.*;
import java.time.LocalDateTime;

public class H2Database {
    private static final int DIFFERENCE_IN_TIME = 1;
    private static final String PRINT_BOOK_IN_ORDER_BY_ISBN = "SELECT ISBN, title, name_author, date, remained_amount " +
            "FROM books LEFT JOIN authors WHERE author=id_author";
    private static final String PRINT_BOOK_IN_ORDER_BY_TITLE = PRINT_BOOK_IN_ORDER_BY_ISBN + " ORDER BY title";
    private static final String PRINT_BOOK_IN_ORDER_BY_AUTHOR = PRINT_BOOK_IN_ORDER_BY_ISBN + " ORDER BY author";
    private static final String PRINT_BOOK_IN_ORDER_BY_DATE = PRINT_BOOK_IN_ORDER_BY_ISBN + " ORDER BY date";
    private static final String PRINT_BOOK_IN_ORDER_BY_REMAINED_AMOUNT = PRINT_BOOK_IN_ORDER_BY_ISBN + " ORDER BY remained_amount";
    private String pathToBaseData = "jdbc:h2:./db/library";
    private String pathToBDForConnect = pathToBaseData +";IFEXISTS=TRUE";
    private String login = "";
    private String password = "";
    private Timestamp currentDate = Timestamp.valueOf(LocalDateTime.now().plusHours(DIFFERENCE_IN_TIME));
    private static Connection connection;
    private static Statement statement;
    private Boolean emptyBaseData = false;

    H2Database(){
        initialize();
    }

    H2Database(String pathToBD, String login, String password, Boolean emptyBaseData){
        pathToBaseData = pathToBD;
        this.login = login;
        this.password = password;
        this.emptyBaseData = emptyBaseData;
        initialize();
    }

    public static void closeConnection(){
        try {
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void initialize(){
        connection = connect();
        try {
            if (connection != null){
                statement = connection.createStatement();
            }else{
                System.out.println("We can't connect to H2DB.");
                connection = createAndConnect();
                if(connection != null){
                    System.out.println("H2DB was created.");
                    statement = connection.createStatement();
                    createTableOfAuthors();
                    createTableOfBooks();
                }
                else{
                    System.out.println("You have a problem with connection to H2db.");
                    System.exit(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Connection connect() {
        try {
            Class.forName("org.h2.Driver").newInstance();
            return DriverManager.getConnection(pathToBDForConnect, login, password);
        } catch (InstantiationException | SQLException | IllegalAccessException | ClassNotFoundException e) {
            return null;
        }
    }

    private Connection createAndConnect(){
        try {
            return DriverManager.getConnection(pathToBaseData, login, password);
        } catch (SQLException e) {
            return null;
        }
    }

    private void createTableOfAuthors() throws SQLException {
        statement.execute("CREATE TABLE authors (" +
                "id_author INT(10) AUTO_INCREMENT," +
                "name_author VARCHAR(50) UNIQUE NOT NULL," +
                "number_books INT(10) DEFAULT 0," +
                "PRIMARY KEY (id_author));");
    }

    private void createTableOfBooks() throws SQLException {
        statement.execute("CREATE TABLE books (" +
                "ISBN INT(10) NOT NULL, " +
                "title VARCHAR(50) NOT NULL, " +
                "author INT(10) NOT NULL, " +
                "date DATETIME DEFAULT '" + currentDate + "', " +
                "remained_amount INT(10) DEFAULT '1', " +
                "PRIMARY KEY (ISBN), " +
                "FOREIGN KEY (author) REFERENCES authors(id_author));");
        if (!emptyBaseData) {
            addBook(100685487,"Suzanne Collins","The Hunger Games");
            addBook(526114571, "Dan Brown", "The Da Vinci Code");
            addBook(352648751, "Arthur Golden","Memoirs of a Geisha");
            addBook(522214671, "Lewis Carroll","Alice's Adventures in Wonderland");
            addBook(112445781, "Oscar Wilde","The Picture of Dorian Gray");
            addBook(235468579, "Joanne Rowling","Harry Potter and the philosopher's stone");
            addBook(255478579, "Joanne Rowling","Harry Potter and the Chamber of Secrets");
        }
    }

    private int determineAuthor(String author){
        try {
            ResultSet result = statement.executeQuery("SELECT id_author FROM authors WHERE name_author='"+author+"';");
            if(result.next()){
                return result.getInt("id_author");
            } else {
                statement.execute("INSERT INTO authors (name_author) VALUES (\'"+author+"\');");
                return determineAuthor(author);
            }
        } catch (SQLException e) {
            return 0;
        }
    }

    private boolean isUniqueISBN(int ISBN) throws SQLException {
        ResultSet result = statement.executeQuery("SELECT ISBN FROM books WHERE ISBN="+ISBN+";");
        return !result.next();
    }

    private void setNumberOfBooks(int authorId, int currentNumberOfBooks) throws SQLException {
        statement.execute("UPDATE authors SET number_books="+currentNumberOfBooks+" WHERE id_author="+authorId+";");
    }

    private int getNumberOfBooks(int id_author) throws SQLException{
        ResultSet result = statement.executeQuery("SELECT number_books FROM authors WHERE id_author='"+id_author+"';");
        result.next();
        return result.getInt(1);
    }

    private void printBook(String way){
        try {
            ResultSet result = statement.executeQuery(way);
            while (result.next()){
                System.out.print("Book: (ISBN: "+result.getString(1)+", ");
                System.out.print("Title: "+result.getString(2)+", ");
                System.out.print("Author: "+result.getString(3)+", ");
                System.out.print("Last change: "+result.getString(4)+", ");
                System.out.println("Remained amount: "+result.getString(5)+")");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addBook(Integer ISBN, String author, String title){
        try {
            if(ISBN==null || ISBN==0 || author==null || author.equals("") || title==null || title.equals("")){
                System.out.println("Invalid arguments.");
                return;
            }
            if(!isUniqueISBN(ISBN)){
                System.out.println("The book with this ISBN has already exist.");
                return;
            }
            if (author.contains("\'")){
                author = author.replace("\'","^");
            }
            if (title.contains("\'")){
                title = title.replace("\'","^");
            }
            int id_author = determineAuthor(author);
            int currentNumberOfBooks = getNumberOfBooks(id_author);
            setNumberOfBooks(id_author, ++currentNumberOfBooks);
            statement.execute("INSERT INTO books (ISBN, title, author) VALUES ("+ISBN+", '"+title+"', "+id_author+")");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setRemainedAmount(Integer ISBN, Integer amount) {
        if(ISBN==null || ISBN==0 || amount==null || amount<0){
            System.out.println("Invalid arguments.");
            return;
        }
        try {
            if(isUniqueISBN(ISBN)){
                System.out.println("The book with this ISBN doesn't exist.");
                return;
            }
            statement.execute("UPDATE books SET remained_amount="+amount+", date='"+currentDate+"' WHERE ISBN="+ISBN+";");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void printBooksByISBN(){
        printBook(PRINT_BOOK_IN_ORDER_BY_ISBN);
    }

    public void printBooksByISBN(Integer limit){
        if(limit == null || limit < 0){
            System.out.println("Invalid argument.");
            return;
        }
        printBook(PRINT_BOOK_IN_ORDER_BY_ISBN +" LIMIT "+limit);
    }

    public void printBooksByAuthor(){
        printBook(PRINT_BOOK_IN_ORDER_BY_AUTHOR);
    }

    public void printBooksByTitle(){
        printBook(PRINT_BOOK_IN_ORDER_BY_TITLE);
    }

    public void printBooksByDate(){
        printBook(PRINT_BOOK_IN_ORDER_BY_DATE);
    }

    public void printBooksByRemainedAmount(){
        printBook(PRINT_BOOK_IN_ORDER_BY_REMAINED_AMOUNT);
    }

    public void printBook(Integer ISBN){
        if(ISBN==null || ISBN==0){
            System.out.println("Invalid arguments.");
            return;
        }
        try {
            if(isUniqueISBN(ISBN)){
                System.out.println("The book with this ISBN doesn't exist.");
                return;
            }
            printBook(PRINT_BOOK_IN_ORDER_BY_ISBN + " AND ISBN="+ISBN);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void printAuthors(){
        try {
            ResultSet result = statement.executeQuery("SELECT * FROM authors;");
            while(result.next()){
                System.out.print("Author: (ID: "+result.getInt(1)+", ");
                System.out.print("Name: "+result.getString(2)+", ");
                System.out.println("Number of books: "+result.getString(3)+")");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
